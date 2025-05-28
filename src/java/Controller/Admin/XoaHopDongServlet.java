/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.XOA_HOP_DONG;
import Model.CuaHangDAO;
import Model.HopDongDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import QLBX.CuaHangQLBX;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import Token.TokenManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import Token.KiemTraRole;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "XoaHopDongServlet", urlPatterns = XOA_HOP_DONG)
public class XoaHopDongServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doDelete(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (!KiemTraRole.isAdmin(response, user)) {
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            if (jsonObject != null) {
                int maHopDong = jsonObject.get("maHopDong").getAsInt();

                boolean thanhCong = new HopDongDAO().xoaHopDong(maHopDong);
                if (thanhCong) {
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write("{\"message\": \"Xóa hợp đồng thành công.\"}");
                } else {
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Hợp đồng không tồn tại hoặc xóa thất bại.\"}");
                }
            } else {
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Thiếu dữ liệu\"}");
            }

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
