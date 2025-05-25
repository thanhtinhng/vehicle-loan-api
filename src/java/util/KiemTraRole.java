/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import Model.HopDong;
import Model.User;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Windows 10
 */
public class KiemTraRole {

    public static boolean kiemTraToken(HttpServletResponse response, User user) throws IOException {
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Token không hợp lệ hoặc đã hết hạn\"}");
            return false;
        }
        return true;
    }

    public static boolean isAdmin(HttpServletResponse response, User user) throws IOException {
        if (!kiemTraToken(response, user)) {
            return false;
        }

        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Chỉ có admin mới có thể thực hiện thao tác này!\"}");
            return false;
        }
        return true;
    }

    public static boolean isUser(HttpServletResponse response, User user) throws IOException {
        if (!kiemTraToken(response, user)) {
            return false;
        }

        if (!"USER".equalsIgnoreCase(user.getRole())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Chỉ có khách hàng thành viên mới có thể thực hiện thao tác này!\"}");
            return false;
        }
        return true;
    }

    public static boolean checkHopDongThuocUser(HttpServletResponse response, User user, HopDong hopDong) throws IOException {
        if (hopDong == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Hợp đồng không tồn tại\"}");
            return false;
        }

        if (user == null || hopDong.getUserId() != user.getUserId()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"mess\":\"Bạn chỉ có thể xem thông tin hợp đồng của bạn.\"}");
            return false;
        }

        return true;
    }
}
