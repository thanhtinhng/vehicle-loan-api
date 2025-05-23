/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

/**
 *
 * @author Windows 10
 */
public class XeCon extends XeQLBX {

    public XeCon(int maXe, int maLoaiXe, String loaiXe, String hangXe, String tenXe, double gia, String tinhTrang, int soLuong) {
        super(maXe, maLoaiXe, loaiXe, hangXe, tenXe, gia, tinhTrang, soLuong);
    }

    public XeCon() {
    }

    @Override
    public double tinhGiaThucTe() {
        if (tinhTrang.equals("CU")) {
            return gia * 1.1 * 0.8;
        }
        return gia * 1.1;
    }
    
    @Override
    public double getTyLeTraTruoc() {
        return 0.25;
    }

    @Override
    public double getLaiSuatThang(int kyHanThang) {
        if (kyHanThang <= 6) {
            return 0.009;
        } else if (kyHanThang <= 12) {
            return 0.01;
        } else if (kyHanThang <= 18) {
            return 0.011;
        } else {
            return 0.012;
        }
    }

}
