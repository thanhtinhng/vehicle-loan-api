/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Auth;

import static Controller.ApiRoutes.LOGIN;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import DAO.UserDAO;
import QLBX.TokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "LoginServlet", urlPatterns = LOGIN)
public class LoginServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            String email = jsonObject.get("email").getAsString();
            String matKhau = jsonObject.get("matKhau").getAsString();

            User user = new UserDAO().xacThucUser(email, matKhau);

            if (user != null) {
                
                String token = TokenManager.findUserToken(user.getEmail());
                
                if (token == null) {
                    token = TokenManager.saveToken(user);
                }

                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\", \"token\":\"" + token + "\"}\n");
                
            } else {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                response.getWriter().write("{\"error\":\"Sai email hoặc mật khẩu\"}\n");
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
