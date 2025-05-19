package config;

import DAO.CuaHangDAO;
import QLBX.CuaHang;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.ArrayList;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            CuaHangDAO cuaHangDAO = new CuaHangDAO();
            ArrayList<CuaHang> danhSachCuaHang = cuaHangDAO.getAll();

            // lưu vào context
            sce.getServletContext().setAttribute("dsCuaHang", danhSachCuaHang);

            // nếu chỉ có 1 cửa hàng:
            sce.getServletContext().setAttribute("cuaHang", danhSachCuaHang.get(0));

            System.out.println("Dữ liệu cửa hàng đã được load");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Xử lý khi dừng server nếu cần
    }
}
