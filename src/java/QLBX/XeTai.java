/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

/**
 *
 * @author Windows 10
 */
public class XeTai extends XeQLBX {

    public XeTai(int maXe, String loaiXe, String hangXe, String tenXe, double gia, String tinhTrang, int soLuong) {
        super(maXe, loaiXe, hangXe, tenXe, gia, tinhTrang, soLuong);
    }

    @Override
    public double tinhGiaThucTe() {
        if (tinhTrang.equals("CU")) {
            return gia * 1.08 * 0.8;
        }
        return gia * 1.08;
    }

}
