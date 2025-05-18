/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Auth;

import static Controller.ApiRoutes.LOGIN;
import static Controller.ApiRoutes.USER_DS;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import DAO.UserDAO;
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
@WebServlet(name = "LoginServlet", urlPatterns = LOGIN) //"/api/login"
public class LoginServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) throws IOException {
        
        try (BufferedReader reader = request.getReader()) {
            
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class); //lấy json truyền vào
            
            String email = jsonObject.get("email").getAsString();
            String matKhau = jsonObject.get("matKhau").getAsString();
            
            //gọi đến user DAO (viết thêm logic so sánh tài khoảng với data trong db để đăng nhập, trả về token và role)
            
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        
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
