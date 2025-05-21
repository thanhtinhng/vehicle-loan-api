/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ConnDB.DBConnection;
import QLBX.HopDong;
import Model.Kho;
import Model.User;
import QLBX.XeQLBX;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class KhoDAO {

    public XeQLBX createKho(ResultSet rs) throws Exception {
        XeQLBX kho = new XeQLBX(
                rs.getInt("MaXe"),
                rs.getString("TenLoaiXe"),
                rs.getString("TenHangXe"),
                rs.getString("TenXe"),
                rs.getDouble("Gia"),
                rs.getString("TinhTrang"),
                rs.getInt("SoLuong")
        );

        return kho;
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

            XeQLBX kho = createKho(rs);
            
            list.add(kho);
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
            xe = createKho(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return xe;
    }
}
