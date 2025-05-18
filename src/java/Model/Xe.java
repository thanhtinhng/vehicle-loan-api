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
    private String loaiXe;
    private String hangXe;
    private String tenXe;
    private double gia;
    private String tinhTrang; // MOI hoặc CU

    public Xe() {
    }

    public Xe(int maXe, String loaiXe, String hangXe, String tenXe, double gia, String tinhTrang) {
        this.maXe = maXe;
        this.loaiXe = loaiXe;
        this.hangXe = hangXe;
        this.tenXe = tenXe;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
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

    public String getHangXe() {
        return hangXe;
    }

    public String getLoaiXe() {
        return loaiXe;
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
