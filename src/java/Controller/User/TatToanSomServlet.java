/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.TAT_TOAN_SOM;
import Model.HopDong;
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
import util.TokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import util.KiemTraRole;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "TatToanSomServlet", urlPatterns = TAT_TOAN_SOM)
public class TatToanSomServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (!KiemTraRole.isUser(response, user)) {
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            int maHopDong = jsonObject.get("maHopDong").getAsInt();

            HopDong hopDongCheck = new HopDongDAO().getByMaHopDong(maHopDong);

            if (!KiemTraRole.checkHopDongThuocUser(response, user, hopDongCheck)) {
                return;
            }
            
            ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
            ThanhToanQLBX ttCanThanhToan = thanhToanDAO.getTTCanThanhToan(maHopDong);
            if (ttCanThanhToan == null) {
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Không thể thực hiện vi tất cả kỳ góp đã được thanh toán\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));
                return;
            }

            double soTienConNo = 0;

            ArrayList<ThanhToanQLBX> listThanhToan = new ArrayList<>();
            listThanhToan.addAll(new ThanhToanDAO().getByMaHopDong(maHopDong));
            HopDongQLBX hopDong = new HopDongQLBX();
            hopDong.setDanhSachThanhToan(listThanhToan);

            soTienConNo = hopDong.tinhSoTienConNo();

            if (!user.kiemTraTaiChinh(soTienConNo)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Bạn không đủ tiền để tất toán hợp đồng!\"}");
                return;
            }

            thanhToanDAO.tatToanHopDong(hopDong.getDanhSachThanhToan(), user.getUserId(), soTienConNo);

            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Tất toán thành công\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
