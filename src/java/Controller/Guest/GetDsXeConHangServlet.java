/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Guest;

import static Controller.ApiRoutes.DS_XE_CON_HANG;
import static Controller.ApiRoutes.XE_DS;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.XeDAO;
import Model.Kho;
import QLBX.CuaHang;
import QLBX.XeQLBX;
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
@WebServlet(name = "GetDsXeConHangServlet", urlPatterns = DS_XE_CON_HANG)
public class GetDsXeConHangServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
                CuaHang cuaHang = (CuaHang) getServletContext().getAttribute("cuaHang");
                
                ArrayList<XeQLBX> list = cuaHang.layDsXeConHang();
                
                response.getWriter().write(gson.toJson(list));
                System.out.println("Test: doGet: Done");
            
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }
}
