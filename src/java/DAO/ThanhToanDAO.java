/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnDB.DBConnection;
import Model.HopDong;
import Model.ThanhToan;
import Model.User;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class ThanhToanDAO {
    
    public ArrayList<ThanhToan> getByMaHopDong(int maHD) throws Exception {
        
        ArrayList<ThanhToan> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        
        String sql = "SELECT * FROM ThanhToan WHERE MaHopDong = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maHD);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new ThanhToan(
                    rs.getInt("MaThanhToan"),
                    rs.getInt("MaHopDong"),
                    rs.getDate("NgayThanhToan"),
                    rs.getDouble("SoTien"),
                    rs.getString("TrangThai")
            ));
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}
