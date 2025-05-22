/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.DS_XE_CON_HANG;
import static Controller.ApiRoutes.THONG_TIN_CUA_HANG;
import static Controller.ApiRoutes.XE_DS;
import Model.CuaHangDAO;
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
import QLBX.CuaHang;
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
@WebServlet(name = "GetCuaHangServlet", urlPatterns = THONG_TIN_CUA_HANG)
public class GetCuaHangServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            ArrayList<CuaHang> danhSachCuaHang = new CuaHangDAO().getAll();
            CuaHang cuaHang = danhSachCuaHang.get(0);

            response.getWriter().write(gson.toJson(cuaHang));
            System.out.println("Test: doGet: Done");

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }
}
