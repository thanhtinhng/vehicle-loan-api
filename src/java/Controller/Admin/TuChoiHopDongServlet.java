/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.TU_CHOI_HOP_DONG;
import Model.HopDongDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import Token.TokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import Token.KiemTraRole;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "TuChoiHopDongServlet", urlPatterns = TU_CHOI_HOP_DONG)
public class TuChoiHopDongServlet extends HttpServlet {

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

            new HopDongDAO().tuChoiHopDong(maHopDong);

            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Đã từ chối hợp đồng thành công!\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
