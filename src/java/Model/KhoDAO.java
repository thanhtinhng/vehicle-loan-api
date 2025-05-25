/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Windows 10
 */
import ConnDB.DBConnection;
import java.sql.*;

public class KhoDAO {
    public void giamSoLuongXe(int maCuaHang, int maXe) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE Kho SET SoLuong = SoLuong - 1 WHERE MaCuaHang = ? AND MaXe = ? AND SoLuong > 0";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, maCuaHang);
        ps.setInt(2, maXe);
        int rows = ps.executeUpdate();

        ps.close();
        conn.close();

        if (rows == 0) {
            throw new Exception("Không đủ xe trong kho để trừ");
        }
    }
    
    public void themSoLuongXe(int maXe, int soLuong) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "UPDATE Kho "
                + "SET SoLuong = SoLuong + ? "
                + "WHERE MaXe = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, soLuong);
        ps.setInt(2, maXe);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
    
    public void add(int maXe, int maCuaHang) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO Kho (MaCuaHang, MaXe, SoLuong) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, maCuaHang);
        ps.setInt(2, maXe);
        ps.setInt(3, 0);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
