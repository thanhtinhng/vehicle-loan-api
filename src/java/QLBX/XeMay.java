/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

/**
 *
 * @author Windows 10
 */
public class XeMay extends XeQLBX {

    public XeMay(int maXe, int maLoaiXe, String loaiXe, String hangXe, String tenXe, double gia, String tinhTrang, int soLuong) {
        super(maXe, maLoaiXe, loaiXe, hangXe, tenXe, gia, tinhTrang, soLuong);
    }

    public XeMay() {
    }

    @Override
    public double tinhGiaThucTe() {
        if (tinhTrang.equals("CU")) {
            return gia * 1.05 * 0.8;
        }
        return gia * 1.05;
    }

    @Override
    public double tinhTyLeTraTruoc() {
        return 0.2;
    }

    @Override
    public double tinhLaiSuatThang(int kyHanThang) {
        if (kyHanThang <= 6) {
            return 0.01;
        } else if (kyHanThang <= 12) {
            return 0.012;
        } else if (kyHanThang <= 18) {
            return 0.014;
        } else {
            return 0.015;
        }
    }

}
