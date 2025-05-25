/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.DUYET_HOP_DONG;
import Model.CuaHangDAO;
import Model.HopDong;
import Model.HopDongDAO;
import Model.KhoDAO;
import Model.XeDAO;
import Model.MaGiamGiaDAO;
import Model.ThanhToanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import QLBX.CuaHangQLBX;
import QLBX.HopDongQLBX;
import QLBX.MaGiamGiaQLBX;
import QLBX.XeQLBX;
import util.TokenManager;
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
@WebServlet(name = "DuyetHopDongServlet", urlPatterns = DUYET_HOP_DONG)
public class DuyetHopDongServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
                return;
            }

            if (!user.getRole().equalsIgnoreCase("ADMIN")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"mess\":\"Chỉ có admin mới có thể thực hiện thao tác này!\"}");
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            ArrayList<CuaHangQLBX> danhSachCuaHang = new CuaHangDAO().getAll();
            CuaHangQLBX cuaHang = danhSachCuaHang.get(0);

            int maHopDong = jsonObject.get("maHopDong").getAsInt();
            HopDong hopDong = new HopDongDAO().getByMaHopDong(maHopDong);

            XeQLBX xe;
            MaGiamGiaQLBX maGiamGia;

            xe = new XeDAO().getByMaXe(hopDong.getMaXe());
            maGiamGia = new MaGiamGiaDAO().getByIdMaGiamGia(hopDong.getIdMaGiamGia());

            HopDongQLBX hopDongQLBX = new HopDongQLBX(maHopDong, xe, maGiamGia, hopDong.getTraTruoc(), hopDong.getLaiXuat(), hopDong.getKyHanThang());

            hopDongQLBX.duyetHopDong();

            // trừ số lượng xe trong kho
            new KhoDAO().giamSoLuongXe(cuaHang.getMaCuaHang(), xe.getMaXe());

            //duyệt hợp đồng (update hợp đồng trong db)
            new HopDongDAO().duyetHopDong(hopDong.getUserId(), hopDongQLBX);

            //tạo thanh toán
            new ThanhToanDAO().taoThanhToanByHopDong(hopDongQLBX);

            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Duyệt hợp đồng thành công!\"}\n\n" + gson.toJson(new HopDongDAO().getByMaHopDong(maHopDong)));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
