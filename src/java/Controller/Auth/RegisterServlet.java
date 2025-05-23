/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Auth;

import static Controller.ApiRoutes.REGISTER;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "RegisterServlet", urlPatterns = REGISTER)
public class RegisterServlet extends HttpServlet {

    private final Gson gson = new Gson();
//    private final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) throws IOException {
        
        try (BufferedReader reader = request.getReader()) {
            
            User user = gson.fromJson(reader, User.class);
            new UserDAO().add(user);
            
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}\n\n" + gson.toJson(user));
        
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
