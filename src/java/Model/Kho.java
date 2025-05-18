/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Windows 10
 */
public class Kho {
    private int maCuaHang;
    private int maXe;
    private int soLuong;

    public Kho() {
    }

    public Kho(int maCuaHang, int maXe, int soLuong) {
        this.maCuaHang = maCuaHang;
        this.maXe = maXe;
        this.soLuong = soLuong;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public int getMaXe() {
        return maXe;
    }

    public int getSoLuong() {
        return soLuong;
    }
    
    
}
