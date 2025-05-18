/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Windows 10
 */
public class ThanhToan {
    private int maThanhToan;
    private int maHopDong;
    private Date ngayThanhToan;
    private double soTien;
    private String trangThai; // CHO, HOANTHANH, TRE

    public ThanhToan() {
    }

    public ThanhToan(int maThanhToan, int maHopDong, Date ngayThanhToan, double soTien, String trangThai) {
        this.maThanhToan = maThanhToan;
        this.maHopDong = maHopDong;
        this.ngayThanhToan = ngayThanhToan;
        this.soTien = soTien;
        this.trangThai = trangThai;
    }
    
}
