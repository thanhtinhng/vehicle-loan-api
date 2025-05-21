/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import QLBX.ThanhToan;
import ConnDB.DBConnection;
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

    public void taoHopDong(int userId, int maCuaHang, int maXe, String maGiamGia, int kyHan) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO HopDong (UserId, MaCuaHang, MaXe, IDMaGiamGia, TienPhat, TraTruoc, LaiXuat, KyHanThang, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        
        String sqlMaGiamGia = "SELECT IDMaGiamGia FROM MaGiamGia WHERE MaGiamGia = ?";
        PreparedStatement psMaGiamGia = conn.prepareStatement(sqlMaGiamGia);
        psMaGiamGia.setString(1, maGiamGia);

        ResultSet rsMaGiamGia = psMaGiamGia.executeQuery();
        Integer idMaGiamGia = null;
        while (rsMaGiamGia.next()) {
            idMaGiamGia = rsMaGiamGia.getInt("IDMaGiamGia");
        }
        
        String sqlXe = "SELECT Gia FROM Xe WHERE MaXe = ?";
        PreparedStatement psXe = conn.prepareStatement(sqlXe);
        psXe.setInt(1, maXe);

        ResultSet rsXe = psXe.executeQuery();
        double gia = 0;
        while (rsXe.next()) {
            gia = rsXe.getDouble("Gia");
        }


        ps.setInt(1, userId);
        ps.setInt(2, maCuaHang);
        ps.setInt(3, maXe);
        ps.setObject(4, idMaGiamGia, java.sql.Types.INTEGER);
        ps.setDouble(5, 0);
        ps.setDouble(6, gia * 0.2);
        ps.setDouble(7, 0.01);
        ps.setInt(8, kyHan);
        ps.setString(9, "CHODUYET");

        ps.executeUpdate();

        ps.close();
        rsMaGiamGia.close();
        rsXe.close();
        conn.close();
    }
}
