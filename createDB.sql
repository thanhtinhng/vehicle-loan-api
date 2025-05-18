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
    Role VARCHAR(20) CHECK (Role IN ('GUEST', 'USER', 'ADMIN'))
);

CREATE TABLE MaGiamGia (
    IDMaGiamGia INT PRIMARY KEY IDENTITY(1,1),
	MaGiamGia VARCHAR(20),
    TiLeGiam DECIMAL(5,2) CHECK (TiLeGiam >= 0 AND TiLeGiam <= 100),
	TrangThai INT CHECK (TrangThai IN (0, 1)) /* 0: không khả dụng; 1: khả dụng */
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
    TrangThai VARCHAR(20) CHECK (TrangThai IN ('CHODUYET', 'HOATDONG', 'HOANTHANH', 'VIPHAM')),
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
