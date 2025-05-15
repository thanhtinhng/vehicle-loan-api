/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Windows 10
 */
public class User {
    private int userId;
    private String username;
    private String matKhau;
    private String email;
    private Date ngaySinh;
    private String dienThoai;
    private String diaChi;
    private double taiChinh;
    private String role;

    public User() {
    }

    public User(int userId, String username, String matKhau, String email, Date ngaySinh, String dienThoai, String diaChi, double taiChinh, String role) {
        this.userId = userId;
        this.username = username;
        this.matKhau = matKhau;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.taiChinh = taiChinh;
        this.role = role;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getRole() {
        return role;
    }

    public double getTaiChinh() {
        return taiChinh;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTaiChinh(double taiChinh) {
        this.taiChinh = taiChinh;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
