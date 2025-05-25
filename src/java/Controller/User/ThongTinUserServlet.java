/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.User;

import static Controller.ApiRoutes.THONG_TIN_USER;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.User;
import Model.UserDAO;
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
@WebServlet(name = "XemThongTinUser", urlPatterns = THONG_TIN_USER)
public class ThongTinUserServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);

            if (!KiemTraRole.kiemTraToken(response, user)) return;

            User updatedUser = new UserDAO().getUserById(user.getUserId());
            TokenManager.updateUser(token, updatedUser);
            
            updatedUser.setMatKhau("******");

            response.getWriter().write(gson.toJson(updatedUser));
            System.out.println("Test: doGet: Done");

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            System.out.println("Test: doGet: Fail");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (BufferedReader reader = request.getReader()) {

            String token = request.getHeader("token");
            User user = TokenManager.getUser(token);

            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
                return;
            }

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            String userName = user.getUsername();
            if (jsonObject.has("username") && !jsonObject.get("username").isJsonNull()) {
                userName = jsonObject.get("username").getAsString();
            }

            String matKhau = user.getMatKhau();
            if (jsonObject.has("matKhau") && !jsonObject.get("matKhau").isJsonNull()) {
                matKhau = jsonObject.get("matKhau").getAsString();
            }

            String email = user.getEmail();
            if (jsonObject.has("email") && !jsonObject.get("email").isJsonNull()) {
                email = jsonObject.get("email").getAsString();
            }

            String ngaySinhStr = null;
            java.sql.Date ngaySinhDate = null;
            if (jsonObject.has("ngaySinh") && !jsonObject.get("ngaySinh").isJsonNull()) {
                ngaySinhStr = jsonObject.get("ngaySinh").getAsString();
                ngaySinhDate = java.sql.Date.valueOf(ngaySinhStr);
            } else {
                ngaySinhDate = new java.sql.Date(user.getNgaySinh().getTime());
            }

//            Nếu cần chuyển định dạng sang: dd/MM/yyyy
//            String ngaySinhStr = null;
//            java.sql.Date ngaySinhDate = null;
//            if (jsonObject.has("ngaySinh") && !jsonObject.get("ngaySinh").isJsonNull()) {
//                ngaySinhStr = jsonObject.get("ngaySinh").getAsString();
//                try {
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    sdf.setLenient(false);
//                    Date utilDate = sdf.parse(ngaySinhStr);
//                    ngaySinhDate = new java.sql.Date(utilDate.getTime());
//                } catch (Exception e) {
//                    throw new RuntimeException("Định dạng ngày sinh không hợp lệ, yêu cầu dd/MM/yyyy", e);
//                }
//            } else {
//                ngaySinhDate = new java.sql.Date(user.getNgaySinh().getTime());
//            }

            String diaChi = user.getDiaChi();
            if (jsonObject.has("diaChi") && !jsonObject.get("diaChi").isJsonNull()) {
                diaChi = jsonObject.get("diaChi").getAsString();
            }

            String dienThoai = user.getDienThoai();
            if (jsonObject.has("dienThoai") && !jsonObject.get("dienThoai").isJsonNull()) {
                dienThoai = jsonObject.get("dienThoai").getAsString();
            }
            
            new UserDAO().capNhatThongTin(user.getUserId(), userName, matKhau, email, ngaySinhDate, diaChi, dienThoai);

            //cập nhật user trong token manager
            User updatedUser = new UserDAO().getUserById(user.getUserId());
            TokenManager.updateUser(token, updatedUser);
            
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}\n\n" + gson.toJson(updatedUser));

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

}
