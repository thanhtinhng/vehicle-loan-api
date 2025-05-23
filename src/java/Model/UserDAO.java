/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ConnDB.DBConnection;
import Model.User;
import java.sql.*;
import java.util.*;

public class UserDAO {

    public User createUser(ResultSet rs) throws Exception {
        User user = new User(
                rs.getInt("UserId"),
                rs.getString("Username"),
                rs.getString("MatKhau"),
                rs.getString("Email"),
                rs.getDate("NgaySinh"),
                rs.getString("DienThoai"),
                rs.getString("DiaChi"),
                rs.getDouble("TaiChinh"),
                rs.getString("Role")
        );

        int userId = rs.getInt("UserId");

        ArrayList<HopDong> dsHopDong = new HopDongDAO().getByUserId(userId);
        user.setDanhSachHopDong(dsHopDong);

        return user;
    }

    public ArrayList<User> getAll() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Users";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            User user = createUser(rs);

            list.add(user);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }

    public User getUserById(int userId) throws Exception {
        User user = null;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Users WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            user = createUser(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return user;
    }

    public void add(User u) throws Exception {
        Connection conn = DBConnection.getConnection();

//        CSDL đang đặt ID tự động nên không insert ID vào
        String sql = "INSERT INTO Users (Username, MatKhau, Email, NgaySinh, DienThoai, DiaChi, TaiChinh, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, u.getUsername());
        ps.setString(2, u.getMatKhau());
        ps.setString(3, u.getEmail());

        java.util.Date ngaySinh = u.getNgaySinh(); // từ đối tượng User
        java.sql.Date sqlNgaySinh = new java.sql.Date(ngaySinh.getTime());
        ps.setDate(4, sqlNgaySinh);

        ps.setString(5, u.getDienThoai());
        ps.setString(6, u.getDiaChi());
        ps.setDouble(7, u.getTaiChinh());
        ps.setString(8, u.getRole());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User xacThucUser(String email, String matKhau) throws Exception {
        User user = null;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM Users WHERE Email = ? AND MatKhau = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, matKhau);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user = createUser(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return user;
    }

    public void napTien(int userId, double tien) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Users SET TaiChinh = ? WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, tien);
        ps.setInt(2, userId);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void truTien(int userId, double traTruoc) throws Exception {
        Connection conn = DBConnection.getConnection();
        // 1. Kiểm tra số dư
        String sqlCheck = "SELECT TaiChinh FROM Users WHERE UserId = ?";
        PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
        psCheck.setInt(1, userId);
        ResultSet rs = psCheck.executeQuery();

        double soDu = 0;
        if (rs.next()) {
            soDu = rs.getDouble("TaiChinh");
        }

        rs.close();
        psCheck.close();

        if (soDu < traTruoc) {
            conn.close();
            throw new Exception("Tài khoản không đủ để thanh toán");
        }

        // 2. Trừ tiền nếu đủ
        String sql = "UPDATE Users SET TaiChinh = TaiChinh - ? WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, traTruoc);
        ps.setInt(2, userId);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void capNhatThongTin(int userId, String userName, String matKhau, String email, java.sql.Date ngaySinh, String diaChi, String dienThoai) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Users "
                + "SET Username = ?, MatKhau = ?, Email = ?, NgaySinh = ?, DiaChi = ?, DienThoai = ? "
                + "WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, userName);
        ps.setString(2, matKhau);
        ps.setString(3, email);
        ps.setDate(4, ngaySinh);
        ps.setString(5, diaChi);
        ps.setString(6, dienThoai);
        ps.setInt(7, userId);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public double getTaiChinh(int userId) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT TaiChinh FROM Users WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        double taiChinh = 0;
        while (rs.next()) {
            taiChinh = rs.getDouble("TaiChinh");
        }

        rs.close();
        ps.close();
        conn.close();
        return taiChinh;
    }
}
