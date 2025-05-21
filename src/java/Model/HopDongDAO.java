/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ConnDB.DBConnection;
import QLBX.HopDong;
import Model.ThanhToan;
import Model.User;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class HopDongDAO {

    public HopDong createHopDong(ResultSet rs) throws Exception {
        HopDong hd = new HopDong(
                rs.getInt("MaHopDong"),
                rs.getInt("UserId"),
                rs.getInt("MaCuaHang"),
                rs.getInt("MaXe"),
                rs.getInt("IDMaGiamGia"),
                rs.getDouble("TongTien"),
                rs.getDouble("TienPhat"),
                rs.getDouble("TraTruoc"),
                rs.getDouble("TienVay"),
                rs.getDouble("LaiXuat"),
                rs.getInt("KyHanThang"),
                rs.getDouble("KhoanTraMoiThang"),
                rs.getDate("NgayHopDong"),
                rs.getString("TrangThai")
        );

        int maHD = rs.getInt("MaHopDong");

        ArrayList<ThanhToan> dsThanhToan = new ThanhToanDAO().getByMaHopDong(maHD);
        hd.setDanhSachThanhToan(dsThanhToan);
        
        return hd;
    }

    public ArrayList<HopDong> getByUserId(int userId) throws Exception {
        ArrayList<HopDong> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM HopDong WHERE UserId = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(createHopDong(rs));
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}
