/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnDB.DBConnection;
import Model.HopDong;
import Model.Kho;
import Model.User;
import QLBX.KhoXeDTO;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Windows 10
 */
public class KhoDAO {

    public KhoXeDTO createKho(ResultSet rs) throws Exception {
        KhoXeDTO kho = new KhoXeDTO(
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
    
    public ArrayList<KhoXeDTO> getAll() throws Exception {
        ArrayList<KhoXeDTO> list = new ArrayList<>();
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

            KhoXeDTO kho = createKho(rs);
            
            list.add(kho);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}
