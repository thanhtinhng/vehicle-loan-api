/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Windows 10
 */
public class LoaiXe {

    private int maLoaiXe;
    private String tenLoaiXe;

    public LoaiXe() {
    }

    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }

    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public LoaiXe(int maLoaiXe, String tenLoaiXe) {
        this.maLoaiXe = maLoaiXe;
        this.tenLoaiXe = tenLoaiXe;
    }
}
