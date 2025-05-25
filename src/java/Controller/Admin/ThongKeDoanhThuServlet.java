/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import static Controller.ApiRoutes.THONG_KE_DOANH_THU;
import Model.CuaHangDAO;
import Model.User;
import QLBX.CuaHangQLBX;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import Token.KiemTraRole;
import Token.TokenManager;
/**
 *
 * @author Windows 10
 */
@WebServlet(name = "ThongKeDoanhThuServlet", urlPatterns = THONG_KE_DOANH_THU)
public class ThongKeDoanhThuServlet extends HttpServlet{
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);
            ArrayList<CuaHangQLBX> danhSachCuaHang = new CuaHangDAO().getAll();
            CuaHangQLBX cuaHang = danhSachCuaHang.get(0);

            if (!KiemTraRole.isAdmin(response, user)) {
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            int nam = jsonObject.get("nam").getAsInt();
            HashMap<Integer, Double> hashMapDoanhThu = cuaHang.thongKeDoanhThuTheoNam(nam);
            
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(hashMapDoanhThu));
            System.out.println("Test: doGet: Done");

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }
}
