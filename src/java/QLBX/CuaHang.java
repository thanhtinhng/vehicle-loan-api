/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

import Model.HopDong;
import Model.Kho;
import Model.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author Windows 10
 */
public class CuaHang {

    private int maCuaHang;
    private String tenCuaHang;
    private String email;
    private String dienThoai;
    private String diaChi;
    private ArrayList<User> dsUser = new ArrayList<>();
    private ArrayList<XeQLBX> dsXe = new ArrayList<>();

    public CuaHang() {
    }

    public CuaHang(int maCuaHang, String tenCuaHang, String email, String dienThoai, String diaChi) {
        this.maCuaHang = maCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.email = email;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }

    public double tinhDoanhThuTheoThang(int nam, int thang) {
        double tongDoanhThu = 0;

        for (User user : dsUser) {
            for (HopDong hopDong : user.getDanhSachHopDong()) {

                if (hopDong.getNgayHopDong() != null) {
                    // lay thang, nam cua hop dong
                    Calendar calHopDong = Calendar.getInstance();
                    calHopDong.setTime(hopDong.getNgayHopDong());

                    int namHD = calHopDong.get(Calendar.YEAR);
                    int thangHD = calHopDong.get(Calendar.MONTH) + 1; // vi month bat dau tu 0

                    // cong tien tra truoc cua hop dong neu hop dong duoc ky trong thang do
                    if (namHD == nam && thangHD == thang) {
                        tongDoanhThu = tongDoanhThu + hopDong.getTraTruoc();
                    }

                    // kiem tra cac thanh toan cua hop dong
                    for (ThanhToanQLBX thanhToan : hopDong.getDanhSachThanhToan()) {
                        if (thanhToan.getTrangThai().equals("HOANTHANH") && thanhToan.getNgayThanhToan() != null) {
                            Calendar calTT = Calendar.getInstance();
                            calTT.setTime(thanhToan.getNgayThanhToan());

                            int namTT = calTT.get(Calendar.YEAR);
                            int thangTT = calTT.get(Calendar.MONTH) + 1;

                            // cong tien neu thanh toan duoc tra trong thang do
                            if (namTT == nam && thangTT == thang) {
                                tongDoanhThu += thanhToan.getSoTien();
                            }
                        }
                    }
                }

            }
        }

        return tongDoanhThu;
    }

    public HashMap<Integer, Double> thongKeDoanhThuTheoNam(int nam) {
        HashMap<Integer, Double> doanhThuTheoThang = new HashMap<>();

        for (int thang = 1; thang <= 12; thang++) {
            doanhThuTheoThang.put(thang, tinhDoanhThuTheoThang(nam, thang));
        }

        return doanhThuTheoThang;
    }

    public ArrayList<XeQLBX> layDsXeConHang() {
        ArrayList<XeQLBX> list = new ArrayList<>();
        for (XeQLBX kho : dsXe) {
            if (kho.getSoLuong() > 0) {
                list.add(kho);
            }
        }
        return list;
    }

    public ArrayList<XeQLBX> sapXepTheoGiaTang() {
        ArrayList<XeQLBX> list = new ArrayList<>();
        list.addAll(dsXe);
        list.sort(Comparator.comparingDouble(XeQLBX::getGia));
        return list;
    }

    public ArrayList<XeQLBX> locXe(
            Integer maXe,
            String loaiXe,
            String hangXe,
            String tinhTrang) {

        Stream<XeQLBX> filteredList = this.dsXe.stream();

        if (maXe != null) {
            return new ArrayList<>(filteredList
                    .filter(xe -> xe.getMaXe() == maXe)
                    .toList());
        }

        return new ArrayList<>(filteredList
                //                .filter(xe -> (maXe == null || xe.getMaXe() == maXe))
                .filter(xe -> (loaiXe == null || xe.getLoaiXe().equalsIgnoreCase(loaiXe)))
                .filter(xe -> (hangXe == null || xe.getHangXe().equalsIgnoreCase(hangXe)))
                .filter(xe -> (tinhTrang == null || xe.getTinhTrang().equalsIgnoreCase(tinhTrang)))
                .toList());
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public ArrayList<User> getDsUser() {
        return dsUser;
    }

    public ArrayList<XeQLBX> getDsXe() {
        return dsXe;
    }

    public String getEmail() {
        return email;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public void setDsUser(ArrayList<User> dsUser) {
        this.dsUser = dsUser;
    }

    public void setDsXe(ArrayList<XeQLBX> dsXe) {
        this.dsXe = dsXe;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

}
