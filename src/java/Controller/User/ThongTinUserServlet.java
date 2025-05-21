/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.DS_XE_CON_HANG;
import static Controller.ApiRoutes.THONG_TIN_CUA_HANG;
import static Controller.ApiRoutes.THONG_TIN_USER;
import static Controller.ApiRoutes.XE_DS;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.Xe;
import Model.XeDAO;
import Model.Kho;
import Model.User;
import QLBX.CuaHang;
import config.TokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "XemThongTinUser", urlPatterns = THONG_TIN_USER)
public class ThongTinUserServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);

            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
                return;
            }

            response.getWriter().write(gson.toJson(user));
            System.out.println("Test: doGet: Done");

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {
            
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            
            Integer id = jsonObject.get("id").getAsInt();
            String matKhau = jsonObject.get("matKhau").getAsString();
            
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
