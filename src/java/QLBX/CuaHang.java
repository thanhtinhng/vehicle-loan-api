/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

import Model.Kho;
import Model.User;
import java.util.ArrayList;

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
