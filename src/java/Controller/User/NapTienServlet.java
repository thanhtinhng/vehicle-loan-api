/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.NAP_TIEN;
import static Controller.ApiRoutes.REGISTER;
import static Controller.ApiRoutes.TAO_HOP_DONG;
import static Controller.ApiRoutes.USER_DS;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import Model.UserDAO;
import util.TokenManager;
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
@WebServlet(name = "NapTienServlet", urlPatterns = NAP_TIEN)
public class NapTienServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            Double tien = jsonObject.get("tien").getAsDouble();
            
            user.napTien(tien);

            new UserDAO().napTien(user.getUserId(), user.getTaiChinh());

            User updatedUser = new UserDAO().getUserById(user.getUserId());
            TokenManager.updateUser(token, updatedUser);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}\n\n" + gson.toJson(updatedUser));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
