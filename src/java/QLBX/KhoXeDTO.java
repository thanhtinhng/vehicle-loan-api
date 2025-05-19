/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public class KhoXeDTO {

    private int maXe;
    private String loaiXe;
    private String hangXe;
    private String tenXe;
    private double gia;
    private String tinhTrang; // MOI hoặc CU
    private int soLuong;

    public KhoXeDTO() {
    }

    public KhoXeDTO(int maXe, String loaiXe, String hangXe, String tenXe, double gia, String tinhTrang, int soLuong) {

        this.maXe = maXe;
        this.loaiXe = loaiXe;
        this.hangXe = hangXe;
        this.tenXe = tenXe;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }

    public static void sapXepTheoGiaTang(List<KhoXeDTO> danhSach) {
        danhSach.sort(Comparator.comparingDouble(KhoXeDTO::getGia));
    }

    public static List<KhoXeDTO> locXe(List<KhoXeDTO> danhSach,
            String loaiXe,
            String hangXe,
            String tinhTrang) {
        return danhSach.stream()
                .filter(xe -> (loaiXe == null || xe.getLoaiXe().equalsIgnoreCase(loaiXe)))
                .filter(xe -> (hangXe == null || xe.getHangXe().equalsIgnoreCase(hangXe)))
                .filter(xe -> (tinhTrang == null || xe.getTinhTrang().equalsIgnoreCase(tinhTrang)))
                .toList();
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getMaXe() {
        return maXe;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public String getHangXe() {
        return hangXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public double getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

}
