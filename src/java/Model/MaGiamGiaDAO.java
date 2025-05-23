/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import ConnDB.DBConnection;
import QLBX.MaGiamGiaQLBX;
import QLBX.ThanhToan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Windows 10
 */
public class MaGiamGiaDAO {

    public MaGiamGiaQLBX createMaGiamGia(ResultSet rs) throws Exception {
        MaGiamGiaQLBX maGiamGia = new MaGiamGiaQLBX(
                rs.getInt("IdMaGiamGia"),
                rs.getString("MaGiamGia"),
                rs.getDouble("TiLeGiam"),
                rs.getInt("TrangThai")
        );

        return maGiamGia;
    }

    public MaGiamGiaQLBX getByIdMaGiamGia(int idMaGiamGia) throws Exception {
        MaGiamGiaQLBX maGiamGia = null;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM MaGiamGia WHERE IdMaGiamGia = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idMaGiamGia);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            maGiamGia = createMaGiamGia(rs);
        }

        rs.close();
        ps.close();
        conn.close();
        return maGiamGia;
    }

    public Integer getIdMaGiamGia(String maGiamGia) throws Exception {
        Integer idMaGiamGia = null;
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT IDMaGiamGia FROM MaGiamGia WHERE MaGiamGia = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maGiamGia);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            idMaGiamGia = rs.getInt("IDMaGiamGia");
        }

        rs.close();
        ps.close();
        conn.close();
        return idMaGiamGia;
    }

    public void disableMaGiamGia(String maGiamGia) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE MaGiamGia "
                + "SET TrangThai = ? "
                + "WHERE MaGiamGia = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 0);
        ps.setString(2, maGiamGia);
        
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
