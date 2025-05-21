/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Windows 10
 */
public class Xe {
    private int maXe;
    private int maLoaiXe;
    private int maHangXe;
    private String tenXe;
    private double gia;
    private String tinhTrang; // MOI hoặc CU

    public Xe() {
    }

    public Xe(int maXe, int maLoaiXe, int maHangXe, String tenXe, double gia, String tinhTrang) {
        this.maXe = maXe;
        this.maLoaiXe = maLoaiXe;
        this.maHangXe = maHangXe;
        this.tenXe = tenXe;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public void setMaHangXe(int maHangXe) {
        this.maHangXe = maHangXe;
    }

    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    

    

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getMaXe() {
        return maXe;
    }

    public int getMaHangXe() {
        return maHangXe;
    }

    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    
    

    public String getTenXe() {
        return tenXe;
    }

    public double getGia() {
        return gia;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }
    
    
}
