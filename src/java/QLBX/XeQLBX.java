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
public class XeQLBX extends AbsXe{

    protected int maXe;
    protected String loaiXe;
    protected int maLoaiXe;
    protected String hangXe;
    protected String tenXe;
    protected double gia;
    protected String tinhTrang; // MOI hoặc CU
    protected int soLuong;
    
    

    public XeQLBX() {
    }
    
     @Override
    public double tinhGiaThucTe() {
        return gia;
    }
    
    @Override
    public double getTyLeTraTruoc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getLaiSuatThang(int kyHanThang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public XeQLBX(int maXe, int maLoaiXe, String loaiXe, String hangXe, String tenXe, double gia, String tinhTrang, int soLuong) {
        this.maXe = maXe;
        this.loaiXe = loaiXe;
        this.maLoaiXe = maLoaiXe;
        this.hangXe = hangXe;
        this.tenXe = tenXe;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
        this.soLuong = soLuong;
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

    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    
    
    

}
