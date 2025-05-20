/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnDB.DBConnection;
import Model.HopDong;
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
            
            int userId = rs.getInt("UserId");
            
            ArrayList<HopDong> dsHopDong = new HopDongDAO().getByUserId(userId);
            user.setDanhSachHopDong(dsHopDong);
            
            list.add(user);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
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
}
