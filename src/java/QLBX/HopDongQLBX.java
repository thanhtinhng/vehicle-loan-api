/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

import Model.MaGiamGia;
import Model.Xe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Windows 10
 */
public class HopDongQLBX {
    private int maHopDong;
    private XeQLBX xe;
    private MaGiamGiaQLBX maGiamGia;
    private double traTruoc;
    private double tienVay;
    private double laiXuat;
    private int kyHan; // tháng
    private double khoanTraHangThang;
    private double tienPhat;
    private Date ngayHopDong;
    private String trangThai; // CHODUYET, HOATDONG, HOANTHANH, VIPHAM
    private ArrayList<ThanhToan> danhSachThanhToan = new ArrayList<>();
    
    public void duyetHopDong() {
        this.trangThai = "HOATDONG";
        this.ngayHopDong = new Date();

        double giaXe = xe.tinhGiaThucTe();
        if (maGiamGia != null) {
            giaXe = maGiamGia.apDung(giaXe);
        }

        this.tienVay = giaXe - traTruoc;
        double laiThang = laiXuat / 100.0 / 12;
        this.khoanTraHangThang = (tienVay * laiThang) / (1 - Math.pow(1 + laiThang, -kyHan));

        // tạo các kỳ thanh toán
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayHopDong);
        for (int i = 0; i < kyHan; i++) {
            cal.add(Calendar.MONTH, 1); // cộng 1 tháng
            Date hanChot = cal.getTime();
            ThanhToan tt = new ThanhToan(maHopDong, hanChot, khoanTraHangThang, "BINHTHUONG");
            danhSachThanhToan.add(tt);
        }
    }
//
//    public void dongTien(int kyIndex) {
//        ThanhToan tt = danhSachThanhToan.get(kyIndex);
//        tt.dongTien();
//        this.tienPhat += tt.getTienPhatNeuTre();
//    }

    public HopDongQLBX() {
    }

    public HopDongQLBX(int maHopDong, XeQLBX xe, MaGiamGiaQLBX maGiamGia, double traTruoc, double tienVay, double laiXuat, int kyHan, double khoanTraHangThang, double tienPhat, Date ngayHopDong, String trangThai) {
        this.maHopDong = maHopDong;
        this.xe = xe;
        this.maGiamGia = maGiamGia;
        this.traTruoc = traTruoc;
        this.tienVay = tienVay;
        this.laiXuat = laiXuat;
        this.kyHan = kyHan;
        this.khoanTraHangThang = khoanTraHangThang;
        this.tienPhat = tienPhat;
        this.ngayHopDong = ngayHopDong;
        this.trangThai = trangThai;
    }
    
    

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public void setXe(XeQLBX xe) {
        this.xe = xe;
    }

    public void setMaGiamGia(MaGiamGiaQLBX maGiamGia) {
        this.maGiamGia = maGiamGia;
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

    public void setKyHan(int kyHan) {
        this.kyHan = kyHan;
    }

    public void setKhoanTraHangThang(double khoanTraHangThang) {
        this.khoanTraHangThang = khoanTraHangThang;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }

    public void setNgayHopDong(Date ngayHopDong) {
        this.ngayHopDong = ngayHopDong;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setDanhSachThanhToan(ArrayList<ThanhToan> danhSachThanhToan) {
        this.danhSachThanhToan = danhSachThanhToan;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public XeQLBX getXe() {
        return xe;
    }

    public MaGiamGiaQLBX getMaGiamGia() {
        return maGiamGia;
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

    public int getKyHan() {
        return kyHan;
    }

    public double getKhoanTraHangThang() {
        return khoanTraHangThang;
    }

    public double getTienPhat() {
        return tienPhat;
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
}
