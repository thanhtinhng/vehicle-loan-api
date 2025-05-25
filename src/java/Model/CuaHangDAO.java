package Model;

import ConnDB.DBConnection;
import QLBX.CuaHangQLBX;
import java.sql.*;
import java.util.*;

public class CuaHangDAO {

    public ArrayList<CuaHangQLBX> getAll() throws Exception {
        ArrayList<CuaHangQLBX> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM CuaHang";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            CuaHangQLBX ch = new CuaHangQLBX(
                    rs.getInt("MaCuaHang"),
                    rs.getString("TenCuaHang"),
                    rs.getString("Email"),
                    rs.getString("DienThoai"),
                    rs.getString("DiaChi")
            );
            
            
            ch.setDsUser(new UserDAO().getAll());
            
            ch.setDsXe(new XeDAO().getAll());
            
            list.add(ch);
        }

        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}