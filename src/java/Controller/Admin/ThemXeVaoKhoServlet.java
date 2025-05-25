/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.THEM_XE;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import Model.Xe;
import Model.XeDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import util.KiemTraRole;
import util.TokenManager;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "ThemXeVaoKhoServlet", urlPatterns = THEM_XE)
public class ThemXeVaoKhoServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);

            if (!KiemTraRole.isAdmin(response, user)) {
                return;
            }
            
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            int maLoaiXe = jsonObject.get("maLoaiXe").getAsInt();
            
            int maHangXe = jsonObject.get("maHangXe").getAsInt();

            String tenXe = jsonObject.get("tenXe").getAsString();
            
            double gia = jsonObject.get("gia").getAsDouble();
            
            String tinhTrang = jsonObject.get("tinhTrang").getAsString();
            
            int soLuong = jsonObject.get("soLuong").getAsInt();

            if (maLoaiXe < 1 || maLoaiXe > 3) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Mã loại xe không hợp lệ. Giá trị phải nằm trong khoảng [1, 3].\"}");
                return;
            }

            if (maHangXe < 1 ||maHangXe > 3) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Mã hãng xe không hợp lệ. Giá trị phải nằm trong khoảng [1, 3].\"}");
                return;
            }
            
            Xe xe = new Xe(0, maLoaiXe, maHangXe, tenXe, gia, tinhTrang);
            new XeDAO().add(xe, soLuong);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"Thêm xe thành công\"}\n\n" + gson.toJson(xe) + "\n" + gson.toJson(soLuong));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
