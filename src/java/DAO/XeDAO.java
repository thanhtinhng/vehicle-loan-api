/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnDB.DBConnection;
import Model.Xe;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class XeDAO {

    public ArrayList<Xe> getAll() throws Exception {
        ArrayList<Xe> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = """
                        SELECT 
                            Xe.*,
                            HangXe.TenHangXe,
                            LoaiXe.TenLoaiXe
                        FROM Xe
                        JOIN LoaiXe ON Xe.MaLoaiXe = LoaiXe.MaLoaiXe
                        JOIN HangXe ON Xe.MaHangXe = HangXe.MaHangXe
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Xe xe = new Xe(
                    rs.getInt("MaXe"),
                    rs.getString("TenLoaiXe"),
                    rs.getString("TenHangXe"),
                    rs.getString("TenXe"),
                    rs.getDouble("Gia"),
                    rs.getString("TinhTrang")
            );

            list.add(xe);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }

    public Xe getByMaXe(int maXe) throws Exception {
        Xe xe = null;
        Connection conn = DBConnection.getConnection();
        String sql = """
                        SELECT 
                            Xe.*,
                            HangXe.TenHangXe,
                            LoaiXe.TenLoaiXe
                        FROM Xe
                        JOIN LoaiXe ON Xe.MaLoaiXe = LoaiXe.MaLoaiXe
                        JOIN HangXe ON Xe.MaHangXe = HangXe.MaHangXe
                        WHERE MaXe = ?
                    """;
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, maXe);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            xe = new Xe(
                    rs.getInt("MaXe"),
                    rs.getString("TenLoaiXe"),
                    rs.getString("TenHangXe"),
                    rs.getString("TenXe"),
                    rs.getDouble("Gia"),
                    rs.getString("TinhTrang")
            );
        }

        rs.close();
        ps.close();
        conn.close();
        return xe;
    }
    
//    public ArrayList<Xe> getXeConHang(int maXe) throws Exception {
//        Xe xe = null;
//        Connection conn = DBConnection.getConnection();
//        String sql = """
//                        SELECT 
//                            Xe.*,
//                            HangXe.TenHangXe,
//                            LoaiXe.TenLoaiXe
//                        FROM Xe
//                        JOIN LoaiXe ON Xe.MaLoaiXe = LoaiXe.MaLoaiXe
//                        JOIN HangXe ON Xe.MaHangXe = HangXe.MaHangXe
//                        WHERE MaXe = ?
//                    """;
//        PreparedStatement ps = conn.prepareStatement(sql);
//
//        ps.setInt(1, maXe);
//
//        ResultSet rs = ps.executeQuery();
//
//        if (rs.next()) {
//            xe = new Xe(
//                    rs.getInt("MaXe"),
//                    rs.getString("TenLoaiXe"),
//                    rs.getString("TenHangXe"),
//                    rs.getString("TenXe"),
//                    rs.getDouble("Gia"),
//                    rs.getString("TinhTrang")
//            );
//        }
//
//        rs.close();
//        ps.close();
//        conn.close();
//        return xe;
//    }
}
