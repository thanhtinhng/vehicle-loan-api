/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
    private ArrayList<Kho> dsXe = new ArrayList<>();

    public CuaHang() {
    }

    public CuaHang(int maCuaHang, String tenCuaHang, String email, String dienThoai, String diaChi) {
        this.maCuaHang = maCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.email = email;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }
    
}
