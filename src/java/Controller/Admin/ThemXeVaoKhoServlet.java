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
import Model.UserDAO;
import Model.Xe;
import Model.XeDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
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
            
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
                return;
            }

            if (!user.getRole().equalsIgnoreCase("ADMIN")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Chỉ có admin mới có thể thực hiện thao tác này!\"}");
                return;
            }

            Xe xe = gson.fromJson(reader, Xe.class);
            
            if (xe.getMaLoaiXe() < 1 || xe.getMaLoaiXe() > 3) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Mã loại xe không hợp lệ. Giá trị phải nằm trong khoảng [1, 3].\"}");
                return;
            }

            if (xe.getMaHangXe() < 1 || xe.getMaHangXe() > 3) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Mã hãng xe không hợp lệ. Giá trị phải nằm trong khoảng [1, 3].\"}");
                return;
            }
            
            new XeDAO().add(xe);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"Thêm xe thành công\"}\n\n" + gson.toJson(xe));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
