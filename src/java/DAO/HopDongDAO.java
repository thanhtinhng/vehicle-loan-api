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

/**
 *
 * @author Windows 10
 */
public class HopDongDAO {

    public static ArrayList<HopDong> getByUserId(int userId) throws Exception {
        ArrayList<HopDong> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM HopDong WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new HopDong(
                    rs.getInt("MaHopDong"),
                    rs.getInt("UserId"),
                    rs.getInt("MaCuaHang"),
                    rs.getInt("MaXe"),
                    rs.getDouble("TongTien"),
                    rs.getDouble("TraTruoc"),
                    rs.getDouble("TienVay"),
                    rs.getDouble("LaiXuat"),
                    rs.getInt("KyHanThang"),
                    rs.getDouble("KhoanTraMoiThang"),
                    rs.getDate("NgayHopDong"),
                    rs.getString("TrangThai")
            ));
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}
