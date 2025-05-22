/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Guest;

import static Controller.ApiRoutes.FILTER_DS_XE;
import static Controller.ApiRoutes.SORT_PRICE;
import static Controller.ApiRoutes.XE_DS;
import Model.XeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import QLBX.CuaHang;
import QLBX.XeQLBX;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "SortPriceServlet", urlPatterns = SORT_PRICE)
public class SortPriceServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
                CuaHang cuaHang = (CuaHang) getServletContext().getAttribute("cuaHang");
                ArrayList<XeQLBX> list = new ArrayList<>();
                list.addAll(cuaHang.sapXepTheoGiaTang());
                response.getWriter().write(gson.toJson(list));
                System.out.println("Test: doGet: Done");
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }
}
