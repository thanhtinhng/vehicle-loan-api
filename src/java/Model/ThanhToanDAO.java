/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import QLBX.ThanhToan;
import ConnDB.DBConnection;
import Model.User;
import QLBX.HopDongQLBX;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class ThanhToanDAO {

    private void ganGiaTriThanhToan(PreparedStatement ps, int maHopDong, ThanhToan thanhToan) throws SQLException {
        
        ps.setInt(1, maHopDong);
        
        java.util.Date hanChot = thanhToan.getHanChot();
        java.sql.Date sqlHanChot = new java.sql.Date(hanChot.getTime());
        ps.setDate(2, sqlHanChot);

        ps.setDouble(3, thanhToan.getSoTien());
        ps.setString(4, thanhToan.getLoai());
        ps.setString(5, thanhToan.getTrangThai());
    }

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
                    rs.getDate("HanChot"),
                    rs.getDate("NgayThanhToan"),
                    rs.getDouble("SoTien"),
                    rs.getString("Loai"),
                    rs.getString("TrangThai")
            ));
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }

    public void taoThanhToanByHopDong(HopDongQLBX hopDongQLBX) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO ThanhToan (MaHopDong, HanChot, SoTien, Loai, TrangThai) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        for (ThanhToan thanhToan : hopDongQLBX.getDanhSachThanhToan()) {
            ganGiaTriThanhToan(ps, hopDongQLBX.getMaHopDong(), thanhToan);
            ps.addBatch(); // gom các insert để thực thi cùng lúc
        }

        ps.executeBatch(); // thực thi tất cả

        ps.close();
        conn.close();
    }
}
