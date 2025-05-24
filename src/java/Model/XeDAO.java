/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ConnDB.DBConnection;
import Model.Kho;
import Model.User;
import QLBX.XeCon;
import QLBX.XeMay;
import QLBX.XeQLBX;
import QLBX.XeTai;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class XeDAO {

    public XeQLBX createXe(ResultSet rs) throws Exception {

        int maXe = rs.getInt("MaXe");
        int maLoaiXe = rs.getInt("MaLoaiXe");
        String tenLoaiXe = rs.getString("TenLoaiXe");
        String tenHangXe = rs.getString("TenHangXe");
        String tenXe = rs.getString("TenXe");
        double gia = rs.getDouble("Gia");
        String tinhTrang = rs.getString("TinhTrang");
        int soLuong = rs.getInt("SoLuong");

        switch (maLoaiXe) {
            case 1:
                return new XeCon(maXe, maLoaiXe, tenLoaiXe, tenHangXe, tenXe, gia, tinhTrang, soLuong);
            case 2:
                return new XeMay(maXe, maLoaiXe, tenLoaiXe, tenHangXe, tenXe, gia, tinhTrang, soLuong);
            case 3:
                return new XeTai(maXe, maLoaiXe, tenLoaiXe, tenHangXe, tenXe, gia, tinhTrang, soLuong);
            default:
                throw new SQLException("Loại xe không hợp lệ: " + maLoaiXe);
        }
    }

    public ArrayList<XeQLBX> getAll() throws Exception {
        ArrayList<XeQLBX> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = """
                        SELECT Xe.*, Kho.SoLuong, LoaiXe.TenLoaiXe, HangXe.TenHangXe
                        FROM Kho 
                        JOIN Xe ON Xe.maXe = Kho.maXe
                        JOIN LoaiXe ON Xe.maLoaiXe = LoaiXe.maLoaiXe
                        JOIN HangXe ON Xe.maHangXe = HangXe.maHangXe
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            XeQLBX xe = createXe(rs);

            list.add(xe);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }

    public XeQLBX getByMaXe(int maXe) throws Exception {
        XeQLBX xe = null;
        Connection conn = DBConnection.getConnection();
        String sql = """
                        SELECT Xe.*, Kho.SoLuong, LoaiXe.TenLoaiXe, HangXe.TenHangXe
                        FROM Kho 
                        JOIN Xe ON Xe.maXe = Kho.maXe
                        JOIN LoaiXe ON Xe.maLoaiXe = LoaiXe.maLoaiXe
                        JOIN HangXe ON Xe.maHangXe = HangXe.maHangXe
                        WHERE Xe.MaXe = ?
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, maXe);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            xe = createXe(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return xe;
    }

    public double getGia(int maXe) throws Exception {
        double gia = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT Gia FROM Xe WHERE MaXe = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, maXe);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            gia = rs.getDouble("Gia");
        }

        rs.close();
        ps.close();
        conn.close();
        return gia;
    }

    public int getMaLoaiXe(int maXe) throws Exception {
        int maLoaiXe = 0;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT MaLoaiXe FROM Xe WHERE MaXe = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, maXe);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            maLoaiXe = rs.getInt("MaLoaiXe");
        }

        rs.close();
        ps.close();
        conn.close();
        return maLoaiXe;
    }

    public void add(Xe xe, int soLuong) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO Xe (MaLoaiXe, MaHangXe, TenXe, Gia, TinhTrang) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, xe.getMaLoaiXe());
        ps.setInt(2, xe.getMaHangXe());
        ps.setString(3, xe.getTenXe());
        ps.setDouble(4, xe.getGia());
        ps.setString(5, xe.getTinhTrang());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int maXeMoi = -1;
        if (rs.next()) {
            maXeMoi = rs.getInt(1);
        }

        KhoDAO khoDAO = new KhoDAO();
        khoDAO.add(maXeMoi, 1);  // 1 cua hang nen ma cua hang mac dinh la 1
        khoDAO.themSoLuongXe(maXeMoi, soLuong);

        ps.close();
        conn.close();
    }
}
