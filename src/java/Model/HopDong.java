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
    private double tongTien;
    private double traTruoc;
    private double tienVay;
    private double laiXuat;
    private int kyHanThang;
    private double khoanTraMoiThang;
    private Date ngayHopDong;
    private String trangThai; // HOATDONG, HOANTHANH, VIPHAM
    private ArrayList<ThanhToan> danhSachThanhToan = new ArrayList<>();

    public HopDong() {
    }

    public HopDong(int maHopDong, int userId, int maCuaHang, int maXe, double tongTien, double traTruoc, double tienVay, double laiXuat, int kyHanThang, double khoanTraMoiThang, Date ngayHopDong, String trangThai) {
        this.maHopDong = maHopDong;
        this.userId = userId;
        this.maCuaHang = maCuaHang;
        this.maXe = maXe;
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
