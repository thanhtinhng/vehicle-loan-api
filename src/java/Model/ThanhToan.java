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
    private Date hanChot;
    private Date ngayThanhToan;
    private double soTien;
    private String loai;
    private String trangThai; // CHO, HOANTHANH, TRE

    public ThanhToan() {
    }

    public ThanhToan(int maThanhToan, int maHopDong, Date hanChot, Date ngayThanhToan, double soTien, String loai, String trangThai) {
        this.maThanhToan = maThanhToan;
        this.maHopDong = maHopDong;
        this.hanChot = hanChot;
        this.ngayThanhToan = ngayThanhToan;
        this.soTien = soTien;
        this.loai = loai;
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

    public Date getHanChot() {
        return hanChot;
    }

    public void setHanChot(Date hanChot) {
        this.hanChot = hanChot;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
    
    
    
}
