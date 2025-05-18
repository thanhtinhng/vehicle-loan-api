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
    
    
}
