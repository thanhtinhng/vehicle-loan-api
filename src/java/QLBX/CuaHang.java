/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

import Model.Kho;
import Model.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    private ArrayList<KhoXeDTO> dsXe = new ArrayList<>();

    public CuaHang() {
    }

    public CuaHang(int maCuaHang, String tenCuaHang, String email, String dienThoai, String diaChi) {
        this.maCuaHang = maCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.email = email;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }

    public ArrayList<KhoXeDTO> layDsXeConHang() {
        ArrayList<KhoXeDTO> list = new ArrayList<>();
        for (KhoXeDTO kho : dsXe) {
            if (kho.getSoLuong() > 0) {
                list.add(kho);
            }
        }
        return list;
    }

    public ArrayList<KhoXeDTO> sapXepTheoGiaTang() {
        ArrayList<KhoXeDTO> list = new ArrayList<>();
        list.addAll(dsXe);
        list.sort(Comparator.comparingDouble(KhoXeDTO::getGia));
        return list;
    }

    public ArrayList<KhoXeDTO> locXe(
            Integer maXe,
            String loaiXe,
            String hangXe,
            String tinhTrang) {

        Stream<KhoXeDTO> filteredList = this.dsXe.stream();

        if (maXe != null) {
            return new ArrayList<>(filteredList
                    .filter(xe -> xe.getMaXe() == maXe)
                    .toList());
        }

        return new ArrayList<>(filteredList
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

    public ArrayList<KhoXeDTO> getDsXe() {
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

    public void setDsXe(ArrayList<KhoXeDTO> dsXe) {
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
