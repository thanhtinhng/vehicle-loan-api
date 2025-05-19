/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.THONG_TIN_USER;
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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "XemThongTinUserServlet", urlPatterns = THONG_TIN_USER) 
public class XemThongTinUserServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
//            Cần đăng nhập, khi nào xong đăng nhập mới làm
            
//            ArrayList<User> list = new UserDAO().getAll();
//            response.getWriter().write(gson.toJson(list));
//            System.out.println("Test: doGet: Done");
        
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }
    
}
