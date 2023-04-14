use master
use QLLinhKien
drop database QLLinhKien
create database QLLinhKien
ON  PRIMARY 
( NAME = N'QLLinhKien', FILENAME = N'E:\NAH\HK5\4. LapTrinhHSK\BT\HSK_QuanLyLinhKien\data\Database\QLLinhKien_data.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLLinhKien', FILENAME = N'E:\NAH\HK5\4. LapTrinhHSK\BT\HSK_QuanLyLinhKien\data\Database\QLLinhKien__log.ldf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )


create table TaiKhoan (
	maTK nvarchar(50)  not null  primary key,
	matKhau nvarchar(50) not null,
)

go
create table NhanVien(
	maNhaVien nvarchar(50) not null primary key,
	maTK nvarchar(50) references TaiKhoan(maTK),
	tenNhanVien  nvarchar(50),
	chucVu  nvarchar(50),
	gioiTinh  nvarchar(50),
	ngaySinh  DATE,
	diaChi  nvarchar(50),
	soDienThoai  int,
	soCanCuoc  int,
	luong  float
)
go
insert TaiKhoan values(N'NV001', N'NV001')
insert TaiKhoan values(N'N1', N'N2')
insert TaiKhoan values(N'N2', N'N2')
insert TaiKhoan values(N'NV002', N'NV002')
insert TaiKhoan values(N'NV003', N'NV003')

insert NhanVien values(N'NV001', N'NV001', N'Lê Văn Hoàng', N'Thu ngân', N'Nam', CAST(N'2003-07-10' AS Date), N'123 Long Thành, Đồng Nai', N'0123456789', N'210932483', 128000000)
insert NhanVien values(N'NV002', N'NV002', N'Vũ Như Cẩn', N'Thu ngân', N'Nữ', CAST(N'1999-07-13' AS Date), N'123 Q.Gò Vấp, Tp.HCM', N'097185564', N'98347832', 28000000)
insert NhanVien values(N'NV003', N'NV003', N'Nguyễn Thu Phương', N'Quản Lý', N'Nữ', CAST(N'2002-07-10' AS Date), N'123 Bến Tre, Đồng Tháp', N'093921349', N'21321847384', 50000000)
select *from TaiKhoan
select *from NhanVien
--maNhaVien nvarchar(50) not null primary key,
--	maTK nvarchar(50) references TaiKhoan(maTK),
--	tenNhanVien  nvarchar(50),
--	chucVu  nvarchar(50),
--	gioiTinh  nvarchar(50),
--	ngaySinh  DATE,
--	diaChi  nvarchar(50),
--	soDienThoai  int,
--	soCanCuoc  int,
--	luong  float
--)