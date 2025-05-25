/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.TAO_HOP_DONG;
import Model.CuaHangDAO;
import Model.HopDongDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import QLBX.CuaHangQLBX;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import util.TokenManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import util.KiemTraRole;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "TaoHopDongServlet", urlPatterns = TAO_HOP_DONG)
public class TaoHopDongServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (!KiemTraRole.isUser(response, user)) return;
            ArrayList<CuaHangQLBX> danhSachCuaHang = new CuaHangDAO().getAll();
            CuaHangQLBX cuaHang = danhSachCuaHang.get(0);

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            if (jsonObject != null) {
                String maGiamGia = null;
                if (jsonObject.has("maGiamGia") && !jsonObject.get("maGiamGia").isJsonNull()) {
                    maGiamGia = jsonObject.get("maGiamGia").getAsString();
                }

                int maHopDong = new HopDongDAO().taoHopDong(
                        user.getUserId(),
                        cuaHang.getMaCuaHang(),
                        jsonObject.get("maXe").getAsInt(),
                        maGiamGia,
                        jsonObject.get("kyHan").getAsInt()
                );
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Tạo hợp đồng thành công. Cần chờ nhân viên duyệt.\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));
            } else {
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Thiếu dữ liệu\"}");
            }

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
