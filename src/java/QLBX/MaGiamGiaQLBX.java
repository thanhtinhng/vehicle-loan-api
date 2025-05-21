/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLBX;

/**
 *
 * @author Windows 10
 */
public class MaGiamGiaQLBX {

    private int idMaGiamGia;
    private int maGiamGia;
    private double tiLeGiam;
    private int trangThai;

    public MaGiamGiaQLBX() {
    }

    public MaGiamGiaQLBX(int idMaGiamGia, int maGiamGia, double tiLeGiam, int trangThai) {
        this.idMaGiamGia = idMaGiamGia;
        this.maGiamGia = maGiamGia;
        this.tiLeGiam = tiLeGiam;
        this.trangThai = trangThai;
    }

    public double apDung(double tongTien) {
        this.trangThai = 0;
        return tongTien * (1 - (tiLeGiam / 100.0));
    }

    public int getIdMaGiamGia() {
        return idMaGiamGia;
    }

    public int getMaGiamGia() {
        return maGiamGia;
    }

    public double getTiLeGiam() {
        return tiLeGiam;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setIdMaGiamGia(int idMaGiamGia) {
        this.idMaGiamGia = idMaGiamGia;
    }

    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setTiLeGiam(double tiLeGiam) {
        this.tiLeGiam = tiLeGiam;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
