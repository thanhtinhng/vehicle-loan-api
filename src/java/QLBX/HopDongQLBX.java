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
    private double tongTien; // traTruoc + tienVay + tongLai
    private double traTruoc; // 20% giá xe
    private double tienVay; // giaXe - traTruoc
    private double laiXuat; // tháng
    private int kyHanThang; // tháng
    private double khoanTraMoiThang; //Mỗi tháng trả 1 số tiền giống nhau (chia đều)
    private double tienPhat; // 5% giá trị thanh toán nếu mỗi lần thanh toán trễ (cộng dồn)
    private Date ngayHopDong; // khi nào admin duyệt mới set
    private String trangThai; // CHODUYET, HOATDONG, HOANTHANH, VIPHAM
    private ArrayList<ThanhToanQLBX> danhSachThanhToan = new ArrayList<>();

    public void duyetHopDong() {
        this.trangThai = "HOATDONG";
        this.ngayHopDong = new Date();

        double giaXe = xe.tinhGiaThucTe();

        if (maGiamGia != null) {
            giaXe = maGiamGia.apDung(giaXe);
        }

        this.tienVay = giaXe - traTruoc;
        double tongLai = tienVay * laiXuat * kyHanThang;
        this.tongTien = traTruoc + tienVay + tongLai;

        double laiHangThang = tienVay * laiXuat;
        double gocHangThang = tienVay / kyHanThang;
        this.khoanTraMoiThang = laiHangThang + gocHangThang;

        // tạo các kỳ thanh toán
        danhSachThanhToan.clear();
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayHopDong);
        for (int i = 0; i < kyHanThang; i++) {
            cal.add(Calendar.MONTH, 1); // cộng 1 tháng
            Date hanChot = cal.getTime();
            ThanhToanQLBX tt = new ThanhToanQLBX(maHopDong, hanChot, khoanTraMoiThang, "BINHTHUONG");
            danhSachThanhToan.add(tt);
        }
    }

    public double tinhSoTienConNo() {
        double tong = 0;
        for (ThanhToanQLBX tt : danhSachThanhToan) {
            if ("CHO".equals(tt.getTrangThai())) {
                tong += tt.getSoTien();
            }
        }
        return tong;
    }

    public boolean kiemTraHoanThanh() {
        for (ThanhToanQLBX tt : danhSachThanhToan) {
            if (tt.getTrangThai().equals("CHO")) {
                return false;
            }
        }
        return true;
    }

    public HopDongQLBX() {
    }

    public HopDongQLBX(int maHopDong, XeQLBX xe, MaGiamGiaQLBX maGiamGia, double tongTien, double traTruoc, double tienVay, double laiXuat, int kyHanThang, double khoanTraMoiThang, double tienPhat, Date ngayHopDong, String trangThai) {
        this.maHopDong = maHopDong;
        this.xe = xe;
        this.maGiamGia = maGiamGia;
        this.tongTien = tongTien;
        this.traTruoc = traTruoc;
        this.tienVay = tienVay;
        this.laiXuat = laiXuat;
        this.kyHanThang = kyHanThang;
        this.khoanTraMoiThang = khoanTraMoiThang;
        this.tienPhat = tienPhat;
        this.ngayHopDong = ngayHopDong;
        this.trangThai = trangThai;
    }

    public HopDongQLBX(int maHopDong, XeQLBX xe, MaGiamGiaQLBX maGiamGia, double traTruoc, double laiXuat, int kyHanThang) {
        this.xe = xe;
        this.maGiamGia = maGiamGia;
        this.traTruoc = traTruoc;
        this.laiXuat = laiXuat;
        this.kyHanThang = kyHanThang;
        this.maHopDong = maHopDong;
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

    public void setKhoanTraMoiThang(double khoanTraMoiThang) {
        this.khoanTraMoiThang = khoanTraMoiThang;
    }

    public void setKyHanThang(int kyHanThang) {
        this.kyHanThang = kyHanThang;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
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

    public void setDanhSachThanhToan(ArrayList<ThanhToanQLBX> danhSachThanhToan) {
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

    public double getKhoanTraMoiThang() {
        return khoanTraMoiThang;
    }

    public int getKyHanThang() {
        return kyHanThang;
    }

    public double getTongTien() {
        return tongTien;
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

    public ArrayList<ThanhToanQLBX> getDanhSachThanhToan() {
        return danhSachThanhToan;
    }
}
