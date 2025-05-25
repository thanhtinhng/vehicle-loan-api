/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.THEM_SO_LUONG_XE;
import Model.KhoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import Model.XeDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import Token.KiemTraRole;
import Token.TokenManager;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "ThemSoLuongXeServlet", urlPatterns = THEM_SO_LUONG_XE)
public class ThemSoLuongXeServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);

            if (!KiemTraRole.isAdmin(response, user)) {
                return;
            }
            
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            int maXe = jsonObject.get("maXe").getAsInt();

            int soLuong = jsonObject.get("soLuong").getAsInt();

            new KhoDAO().themSoLuongXe(maXe, soLuong);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"Thêm số lượng xe thành công\"}\n\n" + gson.toJson(new XeDAO().getByMaXe(maXe)));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
