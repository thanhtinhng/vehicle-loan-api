/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import QLBX.ThanhToanQLBX;
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

    private ThanhToanQLBX createThanhToanQLBX(ResultSet rs) throws Exception {
        ThanhToanQLBX thanhToan = new ThanhToanQLBX(
                rs.getInt("MaThanhToan"),
                rs.getInt("MaHopDong"),
                rs.getDate("HanChot"),
                rs.getDate("NgayThanhToan"),
                rs.getDouble("SoTien"),
                rs.getString("Loai"),
                rs.getString("TrangThai"));

        return thanhToan;
    }

    private void ganGiaTriThanhToan(PreparedStatement ps, int maHopDong, ThanhToanQLBX thanhToan) throws SQLException {

        ps.setInt(1, maHopDong);

        java.util.Date hanChot = thanhToan.getHanChot();
        java.sql.Date sqlHanChot = new java.sql.Date(hanChot.getTime());
        ps.setDate(2, sqlHanChot);

        ps.setDouble(3, thanhToan.getSoTien());
        ps.setString(4, thanhToan.getLoai());
        ps.setString(5, thanhToan.getTrangThai());
    }

    public ArrayList<ThanhToanQLBX> getByMaHopDong(int maHD) throws Exception {

        ArrayList<ThanhToanQLBX> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM ThanhToan WHERE MaHopDong = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maHD);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(createThanhToanQLBX(rs));
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

        for (ThanhToanQLBX thanhToan : hopDongQLBX.getDanhSachThanhToan()) {
            ganGiaTriThanhToan(ps, hopDongQLBX.getMaHopDong(), thanhToan);
            ps.addBatch(); // gom các insert để thực thi cùng lúc
        }

        ps.executeBatch(); // thực thi tất cả

        ps.close();
        conn.close();
    }

    public ThanhToanQLBX getTTCanThanhToan(int maHopDong) throws Exception {
        ThanhToanQLBX thanhToan = null;
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT TOP 1 * FROM ThanhToan WHERE MaHopDong = ? AND TrangThai = ? ORDER BY HanChot ASC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maHopDong);
        ps.setString(2, "CHO");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            thanhToan = createThanhToanQLBX(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return thanhToan;
    }

    public java.sql.Date getHanChotCuoiCung(int maHopDong) throws Exception {
        java.sql.Date hanChotCuoi = null;
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT MAX(HanChot) AS HanChotCuoi FROM ThanhToan WHERE MaHopDong = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maHopDong);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            hanChotCuoi = rs.getDate("HanChotCuoi");
        }

        rs.close();
        ps.close();
        conn.close();

        return hanChotCuoi;
    }

    public void taoThanhToanTre(ThanhToanQLBX thanhToan) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO ThanhToan (MaHopDong, HanChot, SoTien, Loai, TrangThai) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, thanhToan.getMaHopDong());

        java.sql.Date hanChotCuoi = getHanChotCuoiCung(thanhToan.getMaHopDong());
        Calendar cal = Calendar.getInstance();
        cal.setTime(hanChotCuoi);
        cal.add(Calendar.MONTH, 1);
        java.sql.Date hanChotMoi = new java.sql.Date(cal.getTimeInMillis());

        ps.setDate(2, hanChotMoi);
        ps.setDouble(3, thanhToan.getTienPhatNeuTre());
        ps.setString(4, "PHAT");
        ps.setString(5, "CHO");

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void thanhToan(ThanhToanQLBX thanhToan, User user) throws Exception {

        new UserDAO().truTien(user.getUserId(), thanhToan.getSoTien());

        Connection conn = DBConnection.getConnection();

        String sql = "UPDATE ThanhToan "
                + "SET TrangThai = ?, NgayThanhToan = ? "
                + "WHERE MaThanhToan = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, thanhToan.getTrangThai());
        java.util.Date ngayThanhToan = thanhToan.getNgayThanhToan();
        java.sql.Date sqlNgayThanhToan = new java.sql.Date(ngayThanhToan.getTime());
        ps.setDate(2, sqlNgayThanhToan);
        ps.setInt(3, thanhToan.getMaThanhToan());

        if (thanhToan.isTre()) {
            new ThanhToanDAO().taoThanhToanTre(thanhToan);
            new HopDongDAO().setTienPhat(thanhToan.getMaHopDong(), thanhToan.getTienPhatNeuTre());
        }

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void tatToanHopDong(ArrayList<ThanhToanQLBX> list, int userId, double tongTien) throws Exception {
        if (list == null || list.isEmpty()) {
            return;
        }

        Connection conn = DBConnection.getConnection();

        String sql = "UPDATE ThanhToan SET NgayThanhToan = ?, TrangThai = ? WHERE MaThanhToan = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

        for (ThanhToanQLBX tt : list) {
            ps.setDate(1, today);
            ps.setString(2, "HOANTHANH");
            ps.setInt(3, tt.getMaThanhToan());
            ps.addBatch();
        }
        
        new UserDAO().truTien(userId, tongTien);

        ps.executeBatch();
        ps.close();
        conn.close();
    }
    
    public void xoaTheoMaHopDong(int maHopDong) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM ThanhToan WHERE MaHopDong = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maHopDong);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
