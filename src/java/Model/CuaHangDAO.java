package Model;

import ConnDB.DBConnection;
import QLBX.HopDong;
import Model.Kho;
import Model.User;
import QLBX.CuaHang;
import java.sql.*;
import java.util.*;

public class CuaHangDAO {

    public ArrayList<CuaHang> getAll() throws Exception {
        ArrayList<CuaHang> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM CuaHang";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            CuaHang ch = new CuaHang(
                    rs.getInt("MaCuaHang"),
                    rs.getString("TenCuaHang"),
                    rs.getString("Email"),
                    rs.getString("DienThoai"),
                    rs.getString("DiaChi")
            );
            
            
            ch.setDsUser(new UserDAO().getAll());
            
            ch.setDsXe(new KhoDAO().getAll());
            
            list.add(ch);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}