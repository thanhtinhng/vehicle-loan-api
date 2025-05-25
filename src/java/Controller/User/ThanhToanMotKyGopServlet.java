/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.THANH_TOAN_1_KY_GOP;
import Model.HopDong;
import Model.HopDongDAO;
import Model.ThanhToanDAO;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import Model.UserDAO;
import QLBX.ThanhToanQLBX;
import util.TokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import util.KiemTraRole;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "ThanhToanMotKyGopServlet", urlPatterns = THANH_TOAN_1_KY_GOP)
public class ThanhToanMotKyGopServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (!KiemTraRole.isUser(response, user)) {
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            int maHopDong = jsonObject.get("maHopDong").getAsInt();

            HopDong hopDong = new HopDongDAO().getByMaHopDong(maHopDong);

            if (!KiemTraRole.checkHopDongThuocUser(response, user, hopDong)) {
                return;
            }

            String ngayThanhToanStr = null;
            java.sql.Date ngayThanhToanDate = null;
            ngayThanhToanStr = jsonObject.get("ngayThanhToan").getAsString();
            ngayThanhToanDate = java.sql.Date.valueOf(ngayThanhToanStr);

            ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
            ThanhToanQLBX ttCanThanhToan = thanhToanDAO.getTTCanThanhToan(maHopDong);
            if (ttCanThanhToan == null) {
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Tất cả kỳ góp đã được thanh toán\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));
                return;
            }

            if (!user.kiemTraTaiChinh(ttCanThanhToan.getSoTien())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Bạn không đủ tiền để thanh toán hợp đồng!\"}");
                return;
            }

            ttCanThanhToan.dongTien(ngayThanhToanDate);

            thanhToanDAO.thanhToan(ttCanThanhToan, user);

            User updatedUser = new UserDAO().getUserById(user.getUserId());
            TokenManager.updateUser(token, updatedUser);

            if (ttCanThanhToan.isTre()) {
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Thanh toán trễ\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));
            } else {
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Thanh toán thành công\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));
            }

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}

//            chọn hợp đồng cần thanh toán 
//                    -> vào db lấy thanh toán cần thanh toán gần nhất để thực hiện 
//                            -> set trạng thái thanh toán và trừ tiền user
//                                -> thông báo thành công hoặc trễ -> nếu trễ thì thêm tiền phạt vào hợp đồng
