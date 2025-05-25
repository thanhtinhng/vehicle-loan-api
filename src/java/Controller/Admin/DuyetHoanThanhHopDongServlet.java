/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.HOAN_THANH_HOP_DONG;
import Model.HopDongDAO;
import Model.ThanhToanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import QLBX.HopDongQLBX;
import QLBX.ThanhToanQLBX;
import Token.TokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import Token.KiemTraRole;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "DuyetHoanThanhHopDongServlet", urlPatterns = HOAN_THANH_HOP_DONG)
public class DuyetHoanThanhHopDongServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (!KiemTraRole.isAdmin(response, user)) {
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            int maHopDong = jsonObject.get("maHopDong").getAsInt();
            ArrayList<ThanhToanQLBX> listTT = new ThanhToanDAO().getByMaHopDong(maHopDong);
            HopDongQLBX hopDongQLBX = new HopDongQLBX();
            hopDongQLBX.setDanhSachThanhToan(listTT);
            
            if (!hopDongQLBX.kiemTraHoanThanh()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Hợp đồng này được được hoàn thành thanh toán!\"}");
                return;
            }
            
            HopDongDAO hopDongDAO = new HopDongDAO();
            hopDongDAO.setHoanThanh(maHopDong);
            

            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Duyệt hoàn thành hợp đồng thành công!\"}\n\n" + gson.toJson(hopDongDAO.getByMaHopDong(maHopDong)));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
