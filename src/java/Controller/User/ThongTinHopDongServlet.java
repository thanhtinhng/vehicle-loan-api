/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.THONG_TIN_HOP_DONG;
import Model.HopDong;
import Model.HopDongDAO;
import Model.User;
import Model.XeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import util.TokenManager;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "ThongTinHopDongServlet", urlPatterns = THONG_TIN_HOP_DONG)
public class ThongTinHopDongServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);

            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            if (jsonObject != null) {
                int maHopDong = jsonObject.get("maHopDong").getAsInt();

                HopDong hopDong = new HopDongDAO().getByMaHopDong(maHopDong);

                if (hopDong == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Hợp đồng không tồn tại\"}");
                    return;
                }

                if (hopDong.getUserId() != user.getUserId()) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("{\"mess\":\"Bạn chỉ có thể xem thông tin hợp đồng của bạn.\"}");
                    return;
                }

                response.getWriter().write(gson.toJson(hopDong));
                System.out.println("Test: doGet: Done");
            } else {
                ArrayList<HopDong> list = new HopDongDAO().getByUserId(user.getUserId());
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
