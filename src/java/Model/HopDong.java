/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Windows 10
 */
public class HopDong {

    private int maHopDong;
    private int userId;
    private int maCuaHang;
    private int maXe;
    private int idMaGiamGia;
    private double tongTien;
    private double traTruoc;
    private double tienVay;
    private double laiXuat;
    private int kyHanThang;
    private double khoanTraMoiThang;
    private Date ngayHopDong;
    private String trangThai; // HOATDONG, HOANTHANH, VIPHAM
    private ArrayList<ThanhToan> danhSachThanhToan = new ArrayList<>();

    public int getMaHopDong() {
        return maHopDong;
    }

    public int getIdMaGiamGia() {
        return idMaGiamGia;
    }
    

    public int getUserId() {
        return userId;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setTraTruoc(double traTruoc) {
        this.traTruoc = traTruoc;
    }

    public void setTienVay(double tienVay) {
        this.tienVay = tienVay;
    }

    public void setLaiXuat(double laiXuat) {
        this.laiXuat = laiXuat;
    }

    public void setKyHanThang(int kyHanThang) {
        this.kyHanThang = kyHanThang;
    }

    public void setIdMaGiamGia(int idMaGiamGia) {
        this.idMaGiamGia = idMaGiamGia;
    }

    
    
    public void setKhoanTraMoiThang(double khoanTraMoiThang) {
        this.khoanTraMoiThang = khoanTraMoiThang;
    }

    public void setNgayHopDong(Date ngayHopDong) {
        this.ngayHopDong = ngayHopDong;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaXe() {
        return maXe;
    }

    public double getTongTien() {
        return tongTien;
    }

    public double getTraTruoc() {
        return traTruoc;
    }

    public double getTienVay() {
        return tienVay;
    }

    public double getLaiXuat() {
        return laiXuat;
    }

    public int getKyHanThang() {
        return kyHanThang;
    }

    public double getKhoanTraMoiThang() {
        return khoanTraMoiThang;
    }

    public Date getNgayHopDong() {
        return ngayHopDong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public ArrayList<ThanhToan> getDanhSachThanhToan() {
        return danhSachThanhToan;
    }

    public HopDong() {
    }

    public HopDong(int maHopDong, int userId, int maCuaHang, int maXe, int idMaGiamGia, double tongTien, double traTruoc, double tienVay, double laiXuat, int kyHanThang, double khoanTraMoiThang, Date ngayHopDong, String trangThai) {
        this.maHopDong = maHopDong;
        this.userId = userId;
        this.maCuaHang = maCuaHang;
        this.maXe = maXe;
        this.idMaGiamGia = idMaGiamGia;
        this.tongTien = tongTien;
        this.traTruoc = traTruoc;
        this.tienVay = tienVay;
        this.laiXuat = laiXuat;
        this.kyHanThang = kyHanThang;
        this.khoanTraMoiThang = khoanTraMoiThang;
        this.ngayHopDong = ngayHopDong;
        this.trangThai = trangThai;
    }

    public void setDanhSachThanhToan(ArrayList<ThanhToan> danhSachThanhToan) {
        this.danhSachThanhToan = danhSachThanhToan;
    }
    
}
