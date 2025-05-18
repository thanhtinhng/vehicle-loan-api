/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Windows 10
 */
public class ThanhToan {
    private int maThanhToan;
    private int maHopDong;
    private Date ngayThanhToan;
    private double soTien;
    private String trangThai; // CHO, HOANTHANH, TRE

    public ThanhToan() {
    }

    public ThanhToan(int maThanhToan, int maHopDong, Date ngayThanhToan, double soTien, String trangThai) {
        this.maThanhToan = maThanhToan;
        this.maHopDong = maHopDong;
        this.ngayThanhToan = ngayThanhToan;
        this.soTien = soTien;
        this.trangThai = trangThai;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public double getSoTien() {
        return soTien;
    }

    public String getTrangThai() {
        return trangThai;
    }
    
}
