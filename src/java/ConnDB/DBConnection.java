package ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=QLBX;"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        String user = "sa";
        String pass = "sa";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Test: ConnDB");
        return DriverManager.getConnection(url, user, pass);
    }
}

/*

CREATE DATABASE QLBX;

USE QLBX;

CREATE TABLE LoaiXe (
    MaLoaiXe INT PRIMARY KEY IDENTITY(1,1),
    TenLoaiXe NVARCHAR(100)
);

CREATE TABLE HangXe (
    MaHangXe INT PRIMARY KEY IDENTITY(1,1),
    TenHangXe NVARCHAR(100)
);

CREATE TABLE Xe (
    MaXe INT PRIMARY KEY IDENTITY(1,1),
    MaLoaiXe INT,
    MaHangXe INT,
    TenXe NVARCHAR(100),
    Gia DECIMAL(15,2),
    TinhTrang VARCHAR(20) CHECK (TinhTrang IN ('MOI', 'CU')),
    FOREIGN KEY (MaLoaiXe) REFERENCES LoaiXe(MaLoaiXe),
    FOREIGN KEY (MaHangXe) REFERENCES HangXe(MaHangXe)
);

CREATE TABLE CuaHang (
    MaCuaHang INT PRIMARY KEY IDENTITY(1,1),
    TenCuaHang NVARCHAR(100),
    Email VARCHAR(255),
    DienThoai VARCHAR(20),
    DiaChi NVARCHAR(255)
);

CREATE TABLE Kho (
    MaCuaHang INT,
    MaXe INT,
    SoLuong INT,
    PRIMARY KEY (MaCuaHang, MaXe),
    FOREIGN KEY (MaCuaHang) REFERENCES CuaHang(MaCuaHang),
    FOREIGN KEY (MaXe) REFERENCES Xe(MaXe)
);

CREATE TABLE Users (
    UserId INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(100),
    MatKhau VARCHAR(255),
    Email VARCHAR(255),
	NgaySinh DATE,
    DienThoai VARCHAR(20),
    DiaChi NVARCHAR(255),
    TaiChinh DECIMAL(15,2),
    Role VARCHAR(20) CHECK (Role IN ('USER', 'ADMIN'))
);

CREATE TABLE MaGiamGia (
    IDMaGiamGia INT PRIMARY KEY IDENTITY(1,1),
	MaGiamGia VARCHAR(20),
    TiLeGiam DECIMAL(5,2) CHECK (TiLeGiam >= 0 AND TiLeGiam <= 100),
	TrangThai INT CHECK (TrangThai IN (0, 1)) -- 0: không khả dụng; 1: khả dụng
);

CREATE TABLE HopDong (
    MaHopDong INT PRIMARY KEY IDENTITY(1,1),
    UserId INT,
    MaCuaHang INT,
    MaXe INT,
	IDMaGiamGia INT,
    TongTien DECIMAL(15,2),
    TraTruoc DECIMAL(15,2),
    TienVay DECIMAL(15,2),
    LaiXuat FLOAT,
    KyHanThang INT,
    KhoanTraMoiThang DECIMAL(15,2),
    NgayHopDong DATE,
    TrangThai VARCHAR(20) CHECK (TrangThai IN ('HOATDONG', 'HOANTHANH', 'VIPHAM')),
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (MaCuaHang) REFERENCES CuaHang(MaCuaHang),
    FOREIGN KEY (MaXe) REFERENCES Xe(MaXe),
	FOREIGN KEY (IDMaGiamGia) REFERENCES MaGiamGia(IDMaGiamGia)
);

CREATE TABLE ThanhToan (
    MaThanhToan INT PRIMARY KEY IDENTITY(1,1),
    MaHopDong INT,
    NgayThanhToan DATE,
    SoTien DECIMAL(15,2),
    TrangThai VARCHAR(20) CHECK (TrangThai IN ('CHO', 'HOANTHANH', 'TRE')),
    FOREIGN KEY (MaHopDong) REFERENCES HopDong(MaHopDong)
);


*/

/*
USE QLBX;

-- 1. Insert categories
INSERT INTO LoaiXe (TenLoaiXe) VALUES
(N'Ô tô con'),
(N'Xe máy'),
(N'Xe tải');

-- 2. Insert brand
INSERT INTO HangXe (TenHangXe) VALUES
(N'Toyota'),
(N'Honda'),
(N'Ford');

-- 3. Insert vehicles
INSERT INTO Xe (MaLoaiXe, MaHangXe, TenXe, Gia, TinhTrang) VALUES
(1, 1, N'Toyota Vios', 500000000, 'MOI'),
(1, 3, N'Ford Ranger', 800000000, 'MOI'),
(2, 2, N'Honda SH 150i', 120000000, 'MOI'),
(3, 3, N'Ford Transit', 900000000, 'CU');

-- 4. Insert store
INSERT INTO CuaHang (TenCuaHang, Email, DienThoai, DiaChi) VALUES
(N'Đại lý xe ABC', 'abcstore@example.com', '0909123456', N'Hà Nội');

-- 5. Insert stock (kho)
INSERT INTO Kho (MaCuaHang, MaXe, SoLuong) VALUES
(1, 1, 10),
(1, 2, 5),
(1, 3, 15),
(1, 4, 2);

-- 6. Insert users (khách hàng)
INSERT INTO Users (Username, MatKhau, Email, NgaySinh, DienThoai, DiaChi, TaiChinh, Role) VALUES
(N'Nguyễn Văn A', '123456', 'vana@example.com', '2005-07-01', '0911222333', N'Hồ Chí Minh', 1000000000, 'USER'),
(N'Trần Thị B', '123456', 'thib@example.com', '2004-05-29', '0922333444', N'Đà Nẵng', 500000000, 'USER'),
(N'Admin', 'admin123', 'admin@example.com', '2003-05-01', '0933444555', N'Hà Nội', 0, 'ADMIN');

-- 7. Insert contracts (hợp đồng mua xe)
INSERT INTO HopDong (UserId, MaCuaHang, MaXe, TongTien, TraTruoc, TienVay, LaiXuat, KyHanThang, KhoanTraMoiThang, NgayHopDong, TrangThai) VALUES
(1, 1, 1, 500000000, 100000000, 400000000, 5.5, 24, 17600, '2025-05-01', 'HOATDONG'),
(2, 1, 3, 120000000, 20000000, 100000000, 6.0, 12, 8833, '2025-04-15', 'HOATDONG');

-- 8. Insert payments (lịch sử thanh toán từng tháng)
INSERT INTO ThanhToan (MaHopDong, NgayThanhToan, SoTien, TrangThai) VALUES
(1, '2025-06-01', 17600, 'HOANTHANH'),
(1, '2025-07-01', 17600, 'CHO'),
(2, '2025-05-01', 8833, 'HOANTHANH'),
(2, '2025-06-01', 8833, 'CHO');

INSERT INTO MaGiamGia (MaGiamGia, TiLeGiam) VALUES 
('UIT10', 10.00),
('UIT15', 15.00),
('UIT20', 20.00),
('UIT25', 25.00),
('UIT30', 30.00),
('UIT40', 40.00),
('UIT50', 50.00),
('UIT5A', 5.00),
('UIT7B', 7.50),
('UIT12C', 12.00),
('UIT18X', 18.00),
('UIT22Z', 22.00),
('UIT35T', 35.00),
('UIT45K', 45.00),
('UIT60VIP', 60.00);

*/
