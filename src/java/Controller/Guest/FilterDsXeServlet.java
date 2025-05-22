/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Guest;

import static Controller.ApiRoutes.FILTER_DS_XE;
import static Controller.ApiRoutes.XE_DS;
import Model.CuaHangDAO;
import Model.XeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.XeDAO;
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
@WebServlet(name = "FilterDsXeServlet", urlPatterns = FILTER_DS_XE)
public class FilterDsXeServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (BufferedReader reader = request.getReader()) {

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            if (jsonObject != null) {

                Integer maXe = null;
                if (jsonObject.has("maXe") && !jsonObject.get("maXe").isJsonNull()) {
                    maXe = jsonObject.get("maXe").getAsInt();
                }

                String loaiXe = null;
                if (jsonObject.has("loaiXe") && !jsonObject.get("loaiXe").isJsonNull()) {
                    loaiXe = jsonObject.get("loaiXe").getAsString();
                }

                String hangXe = null;
                if (jsonObject.has("hangXe") && !jsonObject.get("hangXe").isJsonNull()) {
                    hangXe = jsonObject.get("hangXe").getAsString();
                }

                String tinhTrang = null;
                if (jsonObject.has("tinhTrang") && !jsonObject.get("tinhTrang").isJsonNull()) {
                    tinhTrang = jsonObject.get("tinhTrang").getAsString();
                }

                String sapXep = null;
                if (jsonObject.has("sapXep") && !jsonObject.get("sapXep").isJsonNull()) {
                    sapXep = jsonObject.get("sapXep").getAsString();
                }

                ArrayList<CuaHang> danhSachCuaHang = new CuaHangDAO().getAll();
                CuaHang cuaHang = danhSachCuaHang.get(0);

                ArrayList<XeQLBX> list = new ArrayList<>();

                list.addAll(cuaHang.locXe(maXe, loaiXe, hangXe, tinhTrang));

                if ("tangDan".equalsIgnoreCase(sapXep)) {
                    list.sort(Comparator.comparingDouble(XeQLBX::getGia));
                }
                response.getWriter().write(gson.toJson(list));
                System.out.println("Test: doGet: Done");
            } else {
                ArrayList<XeQLBX> list = new XeDAO().getAll();
                response.getWriter().write(gson.toJson(list));
                System.out.println("Test: doGet: Done");
            }
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }
}
