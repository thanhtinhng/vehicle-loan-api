/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import QLBX.ThanhToan;
import ConnDB.DBConnection;
import QLBX.HopDongQLBX;
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

    public int taoHopDong(int userId, int maCuaHang, int maXe, String maGiamGia, int kyHan) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO HopDong (UserId, MaCuaHang, MaXe, IDMaGiamGia, TienPhat, TraTruoc, LaiXuat, KyHanThang, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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

        ResultSet rs = ps.getGeneratedKeys();
        int maHopDongMoi = -1;
        if (rs.next()) {
            maHopDongMoi = rs.getInt(1);
        }

        ps.close();
        rsMaGiamGia.close();
        rsXe.close();
        rs.close();
        conn.close();

        return maHopDongMoi;
    }

    public HopDong getByMaHopDong(int maHopDong) throws Exception {
        HopDong hopDong = null;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM HopDong WHERE MaHopDong = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maHopDong);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            hopDong = createHopDong(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return hopDong;
    }

    public void duyetHopDong(int userId, HopDongQLBX hopDongQLBX) throws Exception {
        Connection conn = DBConnection.getConnection();

        //kiểm tra tài chính
        String sqlTaiChinh = "SELECT TaiChinh FROM Users WHERE UserId = ?";
        PreparedStatement psTaiChinh = conn.prepareStatement(sqlTaiChinh);
        psTaiChinh.setInt(1, userId);

        ResultSet rsTaiChinh = psTaiChinh.executeQuery();
        double taiChinh = 0;
        if (rsTaiChinh.next()) {
            taiChinh = rsTaiChinh.getDouble("TaiChinh");
        }

        if (taiChinh < hopDongQLBX.getTraTruoc()) {
            psTaiChinh.close();
            rsTaiChinh.close();
            conn.close();
            throw new Exception("Khách hàng không đủ điều kiện để ký hợp đồng (tài chính).");
        }
        psTaiChinh.close();
        rsTaiChinh.close();

        //duyệt hợp đồng
        String sql = "UPDATE HopDong "
                + "SET TongTien = ?, TienVay = ?, KhoanTraMoiThang = ?, NgayHopDong = ?, TrangThai = ? "
                + "WHERE MaHopDong = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, hopDongQLBX.getTongTien());
        ps.setDouble(2, hopDongQLBX.getTienVay());
        ps.setDouble(3, hopDongQLBX.getKhoanTraMoiThang());

        java.util.Date ngayHopDong = hopDongQLBX.getNgayHopDong();
        java.sql.Date sqlNgayHopDong = new java.sql.Date(ngayHopDong.getTime());
        ps.setDate(4, sqlNgayHopDong);

        ps.setString(5, hopDongQLBX.getTrangThai());
        ps.setInt(6, hopDongQLBX.getMaHopDong());

        //update mã giảm giá đã sử dụng
        if (hopDongQLBX.getMaGiamGia() != null) {
            String sqlMaGiamGia = "UPDATE MaGiamGia "
                    + "SET TrangThai = ? "
                    + "WHERE MaGiamGia = ?";
            PreparedStatement psMaGiamGia = conn.prepareStatement(sqlMaGiamGia);

            psMaGiamGia.setInt(1, 0);
            psMaGiamGia.setString(2, hopDongQLBX.getMaGiamGia().getMaGiamGia());
            
            psMaGiamGia.executeUpdate();
            psMaGiamGia.close();
        }

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
    
    public ArrayList<HopDong> getAll() throws Exception {
        ArrayList<HopDong> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM HopDong";
        PreparedStatement ps = conn.prepareStatement(sql);

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
