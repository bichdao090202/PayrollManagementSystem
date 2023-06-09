USE [master]
GO
/****** Object:  Database [PayrollManagementSystem]    Script Date: 12/17/2022 12:10:16 AM ******/
CREATE DATABASE [PayrollManagementSystem]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PayrollManagementSystem_Data2', FILENAME = N'D:\DataSQL\PayrollManagementSystem2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PayrollManagementSystem_Log2', FILENAME = N'D:\DataSQL\PayrollManagementSystem2.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PayrollManagementSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PayrollManagementSystem] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET ARITHABORT OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PayrollManagementSystem] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PayrollManagementSystem] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PayrollManagementSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PayrollManagementSystem] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PayrollManagementSystem] SET  MULTI_USER 
GO
ALTER DATABASE [PayrollManagementSystem] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PayrollManagementSystem] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PayrollManagementSystem] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PayrollManagementSystem] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
USE [PayrollManagementSystem]
GO
/****** Object:  UserDefinedFunction [dbo].[GENERATE_EMPLOYEE_OFFICE_ID]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[GENERATE_EMPLOYEE_OFFICE_ID]()
	RETURNS CHAR(9)
AS BEGIN
	DECLARE @ID CHAR(9), @ID_INT INT
	SET @ID_INT = (SELECT (CAST(MAX(RIGHT(MANHANVIEN, 5)) AS INT) + 1) FROM NhanVienHanhChinh)
	IF @ID_INT < 10
		SET @ID = 'NVHC0000' + CONVERT(CHAR(1), @ID_INT)
	ELSE IF @ID_INT < 100
		SET @ID = 'NVHC000' + CONVERT(CHAR(2), @ID_INT)
	ELSE IF @ID_INT < 1000
		SET @ID = 'NVHC00' + CONVERT(CHAR(3), @ID_INT)
	ELSE IF @ID_INT < 10000
		SET @ID = 'NVHC0' + CONVERT(CHAR(4), @ID_INT)
	ELSE
		SET @ID = 'NVHC' + CONVERT(CHAR(5), @ID_INT)
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[GENERATE_WORKER_ID]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[GENERATE_WORKER_ID]()
	RETURNS CHAR(9)
AS BEGIN
	DECLARE @ID CHAR(9), @ID_INT INT
	SET @ID_INT = (SELECT (CAST(MAX(RIGHT(MANHANVIEN, 5)) AS INT) + 1) FROM NhanVienSanXuat)
	IF @ID_INT < 10
		SET @ID = 'NVSX0000' + CONVERT(CHAR(1), @ID_INT)
	ELSE IF @ID_INT < 100
		SET @ID = 'NVSX000' + CONVERT(CHAR(2), @ID_INT)
	ELSE IF @ID_INT < 1000
		SET @ID = 'NVSX00' + CONVERT(CHAR(3), @ID_INT)
	ELSE IF @ID_INT < 10000
		SET @ID = 'NVSX0' + CONVERT(CHAR(4), @ID_INT)
	ELSE
		SET @ID = 'NVSX' + CONVERT(CHAR(5), @ID_INT)
	RETURN @ID
END
GO
/****** Object:  Table [dbo].[ChamCongHanhChinh]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongHanhChinh](
	[MaNhanVien] [char](9) NOT NULL,
	[NgayChamCong] [date] NOT NULL,
	[CheckInSang] [time](0) NULL,
	[CheckOutSang] [time](0) NULL,
	[CheckInChieu] [time](0) NULL,
	[CheckOutChieu] [time](0) NULL,
 CONSTRAINT [PK_ChamCongHanhChinh_1] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC,
	[NgayChamCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChamCongSanXuat]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongSanXuat](
	[MaChamCong] [int] IDENTITY(1,1) NOT NULL,
	[NgayChamCong] [date] NOT NULL,
	[SoLuongThanhPham] [int] NOT NULL,
	[MaPhanCong] [int] NOT NULL,
 CONSTRAINT [PK_ChamCongSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaChamCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HopDongSanXuat]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HopDongSanXuat](
	[MaHopDong] [int] IDENTITY(1,1) NOT NULL,
	[SoLuongSanXuat] [int] NOT NULL,
	[SoLuongHoanThanh] [int] NOT NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[MaSanPham] [char](5) NOT NULL,
	[ThoiGian] [date] NOT NULL,
 CONSTRAINT [PK_ChiTietSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaHopDong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVienHanhChinh]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVienHanhChinh](
	[MaNhanVien] [char](9) NOT NULL,
	[TenNhanVien] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[SDT] [char](10) NOT NULL,
	[TenNganHang] [varchar](50) NOT NULL,
	[SoTaiKhoan] [char](14) NOT NULL,
	[TenNguoiThuHuong] [nchar](50) NOT NULL,
	[LuongTheoChucDanh] [float] NOT NULL,
	[ChucVu] [nvarchar](50) NOT NULL,
	[MaPhongBan] [char](4) NOT NULL,
 CONSTRAINT [PK_NhanVienHanhChinh] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVienSanXuat]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVienSanXuat](
	[MaNhanVien] [char](9) NOT NULL,
	[TenNhanVien] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[SDT] [char](10) NOT NULL,
	[TenNganHang] [char](10) NOT NULL,
	[SoTaiKhoan] [char](14) NOT NULL,
	[TenNguoiThuHuong] [nchar](50) NOT NULL,
	[ChuyenMon] [nvarchar](50) NOT NULL,
	[MaTo] [char](6) NOT NULL,
	[ChucVu] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_NhanVienSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhanCong]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanCong](
	[MaPhanCong] [int] IDENTITY(1,1) NOT NULL,
	[MaQuyTrinh] [char](7) NOT NULL,
	[MaNhanVien] [char](9) NOT NULL,
	[NgayThamGia] [date] NOT NULL,
	[MaHopDong] [int] NOT NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[MaPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhanXuong]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanXuong](
	[MaPhanXuong] [char](4) NOT NULL,
	[TenPhanXuong] [nvarchar](50) NOT NULL,
	[MaQuanDoc] [char](9) NULL,
 CONSTRAINT [PK_PhanXuong] PRIMARY KEY CLUSTERED 
(
	[MaPhanXuong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongBan](
	[MaPhongBan] [char](4) NOT NULL,
	[TenPhongBan] [nvarchar](50) NOT NULL,
	[MaTruongPhong] [char](9) NULL,
 CONSTRAINT [PK_PhongBan] PRIMARY KEY CLUSTERED 
(
	[MaPhongBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuyTrinh]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuyTrinh](
	[MaQuyTrinh] [char](7) NOT NULL,
	[TenQuyTrinh] [nvarchar](50) NOT NULL,
	[GiaQuyTrinh] [float] NOT NULL,
	[MaSanPham] [char](5) NOT NULL,
	[ThuTuSanXuat] [int] NOT NULL,
 CONSTRAINT [PK_QuyTrinh] PRIMARY KEY CLUSTERED 
(
	[MaQuyTrinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSanPham] [char](5) NOT NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TenDangNhap] [char](9) NOT NULL,
	[MatKhau] [char](74) NOT NULL,
	[MaNVSX] [char](9) NULL,
	[MaNVHC] [char](9) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[TenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ToSanXuat]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ToSanXuat](
	[MaTo] [char](6) NOT NULL,
	[TenTo] [nvarchar](50) NOT NULL,
	[MaToTruong] [char](9) NULL,
	[MaPhanXuong] [char](4) NULL,
 CONSTRAINT [PK_ToSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaTo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[VIEW_CCSX]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIEW_CCSX] AS
SELECT        QuyTrinh.MaQuyTrinh, SUM(ChamCongSanXuat.SoLuongThanhPham) AS SoLuong, HopDongSanXuat.MaHopDong
FROM            ChamCongSanXuat INNER JOIN
                         PhanCong ON ChamCongSanXuat.MaPhanCong = PhanCong.MaPhanCong INNER JOIN
                         QuyTrinh ON PhanCong.MaQuyTrinh = QuyTrinh.MaQuyTrinh INNER JOIN
                         HopDongSanXuat ON PhanCong.MaHopDong = HopDongSanXuat.MaHopDong
GROUP BY QuyTrinh.MaQuyTrinh, HopDongSanXuat.MaHopDong
GO
/****** Object:  View [dbo].[VIEW_CHAMCONGHANHCHINH]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIEW_CHAMCONGHANHCHINH] AS
SELECT NhanVien = CCHC.MaNhanVien + ' - ' + NVHC.TenNhanVien
      ,NgayChamCong
      ,CheckInSang
      ,CheckOutSang
      ,CheckInChieu
      ,CheckOutChieu
FROM ChamCongHanhChinh AS CCHC INNER JOIN NhanVienHanhChinh AS NVHC ON CCHC.MaNhanVien = NVHC.MaNhanVien
GO
/****** Object:  View [dbo].[VIEW_CHAMCONGSX]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIEW_CHAMCONGSX] AS
SELECT        PC.MaNhanVien + ' - ' + NVSX.TenNhanVien AS NhanVien, SP.MaSanPham + ' - ' + SP.TenSanPham AS SanPham, PC.MaQuyTrinh + ' - ' + QT.TenQuyTrinh AS QuyTrinh, PC.NgayThamGia, CCSX.NgayChamCong, 
                         CCSX.SoLuongThanhPham, CCSX.MaChamCong, NVSX.MaTo
FROM            PhanCong AS PC INNER JOIN
                         NhanVienSanXuat AS NVSX ON PC.MaNhanVien = NVSX.MaNhanVien INNER JOIN
                         QuyTrinh AS QT ON PC.MaQuyTrinh = QT.MaQuyTrinh INNER JOIN
                         SanPham AS SP ON QT.MaSanPham = SP.MaSanPham INNER JOIN
                         ChamCongSanXuat AS CCSX ON PC.MaPhanCong = CCSX.MaPhanCong
WHERE YEAR(CCSX.NgayChamCong) = YEAR(GETDATE()) AND MONTH(CCSX.NgayChamCong) = MONTH(GETDATE())
GO
/****** Object:  View [dbo].[VIEW_LUONGNHANVIENSANXUAT]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIEW_LUONGNHANVIENSANXUAT] AS
SELECT PC.MaNhanVien,
       MONTH(CCSX.NgayChamCong) AS THANG,
       YEAR(CCSX.NgayChamCong) AS NAM,
       SUM(QT.GiaQuyTrinh * CCSX.SoLuongThanhPham) AS TongTien
FROM ChamCongSanXuat AS CCSX
     INNER JOIN PhanCong AS PC ON CCSX.MaPhanCong = PC.MaPhanCong
     INNER JOIN QuyTrinh AS QT ON PC.MaQuyTrinh = QT.MaQuyTrinh
GROUP BY YEAR(CCSX.NgayChamCong),
         MONTH(CCSX.NgayChamCong),
         PC.MaNhanVien
GO
/****** Object:  View [dbo].[VIEW_PHANCONG]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[VIEW_PHANCONG] AS
SELECT PC.MaPhanCong
     , PC.MaNhanVien + ' - ' + NVSX.TenNhanVien AS NhanVien
     , SP.MaSanPham + ' - ' + SP.TenSanPham     AS SanPham
     , PC.MaQuyTrinh + ' - ' + QT.TenQuyTrinh   AS QuyTrinh
     , PC.NgayThamGia
     , PC.MaHopDong
     , HopDongSanXuat.SoLuongSanXuat
     , HopDongSanXuat.SoLuongSanXuat -
       (
           SELECT SUM(ChamCongSanXuat.SoLuongThanhPham)
           FROM ChamCongSanXuat
               INNER JOIN PhanCong
                   ON ChamCongSanXuat.MaPhanCong = PhanCong.MaPhanCong
           WHERE PhanCong.MaQuyTrinh = PC.MaQuyTrinh
                 AND PhanCong.MAHOPDONG = PC.MaHOPDONG
       )                                        AS SoLuongCanHoanThanh,
	   NVSX.MaTo
FROM PhanCong                  AS PC
    INNER JOIN NhanVienSanXuat AS NVSX
        ON PC.MaNhanVien = NVSX.MaNhanVien
    INNER JOIN QuyTrinh        AS QT
        ON PC.MaQuyTrinh = QT.MaQuyTrinh
    INNER JOIN SanPham         AS SP
        ON QT.MaSanPham = SP.MaSanPham
    INNER JOIN HopDongSanXuat
        ON PC.MaHopDong = HopDongSanXuat.MaHopDong
           AND SP.MaSanPham = HopDongSanXuat.MaSanPham
WHERE (PC.NgayThamGia =
      (
          SELECT MAX(NgayThamGia) AS Expr1
          FROM PhanCong
          WHERE (PC.MaNhanVien = MaNhanVien)
      )
      )
GO
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00081', CAST(N'2022-10-30' AS Date), CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), CAST(N'13:30:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00081', CAST(N'2022-11-29' AS Date), CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), CAST(N'13:30:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00081', CAST(N'2022-11-30' AS Date), CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), CAST(N'13:30:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00081', CAST(N'2022-12-15' AS Date), NULL, NULL, CAST(N'13:30:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00081', CAST(N'2022-12-16' AS Date), CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), CAST(N'13:30:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00082', CAST(N'2022-12-16' AS Date), CAST(N'08:00:00' AS Time), CAST(N'12:00:00' AS Time), CAST(N'13:30:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00083', CAST(N'2022-08-15' AS Date), CAST(N'08:15:00' AS Time), CAST(N'11:10:00' AS Time), CAST(N'13:00:00' AS Time), CAST(N'17:31:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00084', CAST(N'2022-07-23' AS Date), CAST(N'08:00:00' AS Time), CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time), CAST(N'17:30:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00089', CAST(N'2022-08-02' AS Date), CAST(N'08:15:00' AS Time), CAST(N'11:10:00' AS Time), CAST(N'13:00:00' AS Time), CAST(N'17:31:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00089', CAST(N'2022-08-18' AS Date), CAST(N'08:05:00' AS Time), CAST(N'11:06:00' AS Time), CAST(N'13:00:00' AS Time), CAST(N'17:34:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00089', CAST(N'2022-08-28' AS Date), CAST(N'08:05:00' AS Time), CAST(N'11:06:00' AS Time), CAST(N'13:00:00' AS Time), CAST(N'17:34:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00089', CAST(N'2022-08-31' AS Date), NULL, NULL, CAST(N'13:00:00' AS Time), CAST(N'17:34:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00089', CAST(N'2022-12-11' AS Date), CAST(N'08:05:00' AS Time), CAST(N'11:06:00' AS Time), CAST(N'13:00:00' AS Time), CAST(N'17:34:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00093', CAST(N'2022-09-24' AS Date), CAST(N'13:14:00' AS Time), CAST(N'11:03:00' AS Time), NULL, NULL)
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00094', CAST(N'2022-09-10' AS Date), CAST(N'08:09:00' AS Time), CAST(N'12:35:00' AS Time), CAST(N'13:01:00' AS Time), CAST(N'17:36:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00094', CAST(N'2022-09-11' AS Date), CAST(N'08:09:00' AS Time), CAST(N'12:00:00' AS Time), CAST(N'13:01:00' AS Time), CAST(N'17:36:00' AS Time))
INSERT [dbo].[ChamCongHanhChinh] ([MaNhanVien], [NgayChamCong], [CheckInSang], [CheckOutSang], [CheckInChieu], [CheckOutChieu]) VALUES (N'NVHC00097', CAST(N'2022-09-24' AS Date), CAST(N'09:00:00' AS Time), CAST(N'11:03:00' AS Time), CAST(N'13:14:00' AS Time), CAST(N'17:23:00' AS Time))
GO
SET IDENTITY_INSERT [dbo].[ChamCongSanXuat] ON 

INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (4, CAST(N'2022-12-03' AS Date), 100, 3)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (5, CAST(N'2022-12-03' AS Date), 100, 4)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (6, CAST(N'2022-12-03' AS Date), 100, 5)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (11, CAST(N'2022-12-03' AS Date), 90, 8)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (12, CAST(N'2022-12-03' AS Date), 90, 9)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (14, CAST(N'2022-12-03' AS Date), 90, 12)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (15, CAST(N'2022-12-02' AS Date), 100, 12)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (16, CAST(N'2022-12-04' AS Date), 5, 11)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (27, CAST(N'2022-12-16' AS Date), 995, 11)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (29, CAST(N'2022-12-16' AS Date), 100, 8)
INSERT [dbo].[ChamCongSanXuat] ([MaChamCong], [NgayChamCong], [SoLuongThanhPham], [MaPhanCong]) VALUES (30, CAST(N'2022-12-16' AS Date), 100, 9)
SET IDENTITY_INSERT [dbo].[ChamCongSanXuat] OFF
GO
SET IDENTITY_INSERT [dbo].[HopDongSanXuat] ON 

INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (1, 1000, 0, N'Sản Xuất', N'NH140', CAST(N'2022-11-20' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (2, 3000, 0, N'Ngưng Sản Xuất', N'IZ753', CAST(N'2022-01-01' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (3, 100000, 0, N'Sản Xuất', N'NH140', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (4, 1000, 0, N'Sản Xuất', N'BF327', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (5, 500, 0, N'Sản Xuất', N'BF327', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (6, 700, 0, N'Ngưng Sản Xuất', N'BF327', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (7, 1000, 0, N'Sản Xuất', N'BL588', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (8, 1000, 0, N'Ngưng Sản Xuất', N'BL588', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (9, 1000, 0, N'Sản Xuất', N'BL588', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (10, 1000, 0, N'Sản Xuất', N'BR044', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (11, 1000, 0, N'Sản Xuất', N'BR044', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (12, 800, 0, N'Ngưng Sản Xuất', N'BR044', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (13, 1000, 0, N'Sản Xuất', N'CB127', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (14, 500, 0, N'Ngưng Sản Xuất', N'CB127', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (15, 500, 0, N'Ngưng Sản Xuất', N'CB127', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (16, 1000, 0, N'Sản Xuất', N'NH140', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (17, 800, 0, N'Ngưng Sản Xuất', N'NH140', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (18, 700, 0, N'Ngưng Sản Xuất', N'NH140', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (19, 100, 0, N'Sản Xuất', N'NH140', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (20, 9999, 0, N'Sản Xuất', N'WF376', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (21, 233, 0, N'Sản Xuất', N'WF376', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (23, 456, 0, N'Sản Xuất', N'BR044', CAST(N'2022-12-16' AS Date))
INSERT [dbo].[HopDongSanXuat] ([MaHopDong], [SoLuongSanXuat], [SoLuongHoanThanh], [TinhTrang], [MaSanPham], [ThoiGian]) VALUES (24, 1000, 0, N'Sản Xuất', N'JQ539', CAST(N'2022-12-16' AS Date))
SET IDENTITY_INSERT [dbo].[HopDongSanXuat] OFF
GO
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00000', N'Admin', 1, CAST(N'2002-10-10' AS Date), N'Máy tính', N'0123456789', N'BIDV', N'01234567891234', N'Admin                                             ', 0, N'Quản Trị', N'PB04')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00081', N'Lê Văn Lượng', 1, CAST(N'1998-04-12' AS Date), N'Phường Phú Thuận, Quận 7, Hồ Chí Minh', N'0365896542', N'BIDV      ', N'56019317676478', N'Lê Văn Lượng                                      ', 30000000, N'Nhân Viên', N'PB01')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00082', N'Phạm Thái Sư', 1, CAST(N'1994-06-26' AS Date), N'Phường An Lạc, Quận Bình Tân, Hồ Chí Minh', N'0354897260', N'BIDV      ', N'75084323970613', N'Phạm Thái Sư                                      ', 12000000, N'Nhân Viên', N'PB02')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00083', N'Trần Văn', 1, CAST(N'1998-05-12' AS Date), N'Phường 04, Quận Gò Vấp, Hồ Chí Minh', N'0354897257', N'BIDV      ', N'56101945058830', N'Trần Văn Công                                     ', 12000000, N'Nhân Viên', N'PB01')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00084', N'Lý Công Uẩng', 1, CAST(N'1992-04-24' AS Date), N'Xã Tân Kiên, Huyện Bình Chánh, HCM', N'0354897259', N'BIDV      ', N'52849097727978', N'Lý Công Uẩng                                      ', 13000000, N'Nhân Viên', N'PB01')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00085', N'Lê Thanh Chung', 1, CAST(N'1993-05-25' AS Date), N'Phường 07, Quận Phú Nhuận, Hồ Chí Minh', N'0354897259', N'BIDV      ', N'62376591778800', N'Lê Thanh Chung                                    ', 20000000, N'Trưởng Phòng', N'PB02')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00086', N'Phạm Thái Sư', 1, CAST(N'1994-06-26' AS Date), N'Phường An Lạc, Quận Bình Tân, Hồ Chí Minh', N'0354897260', N'BIDV      ', N'75084323970613', N'Phạm Thái Sư                                      ', 12000000, N'Nhân Viên', N'PB02')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00087', N'Nguyễn Thị Kim Liên', 1, CAST(N'1995-03-27' AS Date), N'Phường 01, Quận 11, Hồ Chí Minh', N'0354897261', N'BIDV      ', N'53116753678945', N'Nguyễn Thị Kim Liên                               ', 12000000, N'Nhân Viên', N'PB02')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00088', N'Tống Gia Hào', 1, CAST(N'1994-04-28' AS Date), N'Phường Tân Phú, Quận 7, Hồ Chí Minh', N'0354897262', N'BIDV      ', N'84312215196165', N'Tống Gia Hào                                      ', 12000000, N'Nhân Viên', N'PB02')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00089', N'Nguyễn Thanh Liên', 0, CAST(N'1993-07-29' AS Date), N'Phường 02, Quận 11, Hồ Chí Minh', N'0354897263', N'BIDV      ', N'66756497026500', N'Nguyễn Thanh Liên                                 ', 20000000, N'Trưởng Phòng', N'PB03')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00090', N'Phạm Thúy Thẩm', 0, CAST(N'1999-03-25' AS Date), N'Phường 11, Quận 10, Hồ Chí Minh', N'0354897264', N'BIDV      ', N'38712251862634', N'Phạm Thúy Thẩm                                    ', 12000000, N'Nhân Viên', N'PB03')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00091', N'Trịnh Thị Thanh Tuyền', 0, CAST(N'1999-06-10' AS Date), N'Phường An Lạc A, Quận Bình Tân, Hồ Chí Minh', N'0354897265', N'BIDV      ', N'72418486862975', N'Trịnh Thị Thanh Tuyền                             ', 12000000, N'Nhân Viên', N'PB03')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00092', N'Võ Tấn Đạt', 1, CAST(N'1997-04-10' AS Date), N'Phường 04, Quận 6, Hồ Chí Minh', N'0354897266', N'BIDV      ', N'61105017279024', N'Võ Tấn Đạt                                        ', 12000000, N'Nhân Viên', N'PB03')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00093', N'Phạm Thái Sư', 1, CAST(N'1994-06-26' AS Date), N'Phường An Lạc, Quận Bình Tân, Hồ Chí Minh', N'0354897260', N'BIDV      ', N'75084323970612', N'Phạm Thái Sư                                      ', 12000000, N'Nhân Viên', N'PB02')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00094', N'Công Văn Luyện', 1, CAST(N'1995-04-23' AS Date), N'Xã Long Hòa, Huyện Cần Giờ, Hồ Chí Minh', N'0354897268', N'BIDV      ', N'36938531908563', N'Công Văn Luyện                                    ', 12000000, N'Nhân Viên', N'PB04')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00095', N'Phạm Thái Tuấn', 1, CAST(N'1995-04-10' AS Date), N'Phường 03, Quận Gò Vấp, Hồ Chí Minh', N'0354897269', N'BIDV      ', N'26783183571654', N'Phạm Thái Tuấn                                    ', 12000000, N'Nhân Viên', N'PB04')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00096', N'Lý Công Duyệt', 1, CAST(N'1994-04-06' AS Date), N'Phường 09, Quận 6, Hồ Chí Minh', N'0354897271', N'BIDV      ', N'55941784962447', N'Lý Công Duyệt                                     ', 20000000, N'Nhân Viên', N'PB05')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00097', N'Lý Công Duyệt', 1, CAST(N'1994-04-06' AS Date), N'Phường 09, Quận 6, Hồ Chí Minh', N'0354897271', N'BIDV      ', N'55941784962447', N'Lý Công Duyệt                                     ', 20000000, N'Trưởng Phòng', N'PB05')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00098', N'Nguyễn Thị Hồng', 0, CAST(N'1999-04-07' AS Date), N'Phường 15, Quận 11, Hồ Chí Minh', N'0354897272', N'BIDV      ', N'10548475074322', N'Nguyễn Thị Hồng                                   ', 12000000, N'Nhân Viên', N'PB05')
INSERT [dbo].[NhanVienHanhChinh] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (N'NVHC00099', N'Nguyễn Thị Kim Liên', 0, CAST(N'1995-03-27' AS Date), N'Phường 01, Quận 11, Hồ Chí Minh', N'0354897261', N'BIDV      ', N'53116753678945', N'Nguyễn Thị Kim Liên                               ', 12000000, N'Nhân Viên', N'PB02')
GO
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00001', N'Bùi Công Minh', 1, CAST(N'1994-03-10' AS Date), N'Phường 10, Quận Phú Nhuận, Hồ Chí Minh', N'0924608193', N'BIDV      ', N'68356963500294', N'Bùi Công Minh                                     ', N'May cúc áo thun', N'PX0101', N'Quản Đốc')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00002', N'Nguyễn Thanh Tâm', 1, CAST(N'1995-10-10' AS Date), N'Phường 04, Quận 4, Hồ Chí Minh', N'0962342550', N'BIDV      ', N'44922475691771', N'Nguyễn Thanh Tâm                                  ', N'May cúc áo thun', N'PX0101', N'Tổ Trưởng')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00003', N'Trần Thanh Công', 1, CAST(N'1999-10-06' AS Date), N'Phường Tam Bình, Quận Thủ Đức, Hồ Chí Minh', N'0834082001', N'BIDV      ', N'86524286628365', N'Trần Thanh Công                                   ', N'May cúc áo thun', N'PX0101', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00004', N'Trần Thanh Công', 1, CAST(N'1999-10-06' AS Date), N'Thị trấn Nhà Bè, Huyện Nhà Bè, Hồ Chí Minh', N'0834082001', N'BIDV      ', N'79505283338222', N'Trần Thị Thanh Nhã                                ', N'May cúc áo thun', N'PX0102', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00005', N'Nguyễn Diệp Anh', 0, CAST(N'2001-12-08' AS Date), N'Phường Cát Lái, Quận 2, Hồ Chí Minh', N'0984476509', N'BIDV      ', N'31130532685543', N'Nguyễn Diệp Anh                                   ', N'May cổ áo thun', N'PX0102', N'Tổ Trưởng')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00006', N'Trần Nam Anh', 1, CAST(N'1999-12-06' AS Date), N'Phường 01, Quận 6, Hồ Chí Minh', N'0973266558', N'BIDV      ', N'10144289403145', N'Trần Nam Anh                                      ', N'May cổ áo thun', N'PX0102', N'Quản Đốc')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00007', N'Hoàng Ngọc Bách', 1, CAST(N'1998-12-05' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924655434', N'BIDV      ', N'11016348263197', N'Hoàng Ngọc Bách                                   ', N'May cổ áo thun', N'PX0102', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00008', N'Nguyễn Thị Kim Dung', 0, CAST(N'2000-12-02' AS Date), N'Xã Tân Thạnh Tây, Huyện Củ Chi, Hồ Chí Minh', N'0941688538', N'BIDV      ', N'19686940773915', N'Nguyễn Thị Kim Dung                               ', N'May cổ áo thun', N'PX0102', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00009', N'Phạm Hồng Đăng', 1, CAST(N'1990-11-04' AS Date), N'Xã Thái Mỹ, Huyện Củ Chi, Hồ Chí Minh', N'0362765429', N'BIDV      ', N'87096198225463', N'Phạm Hồng Đăng                                    ', N'May tay áo thun', N'PX0103', N'Tổ Trưởng')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00010', N'Vũ Việt Hà', 1, CAST(N'1997-09-11' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924655435', N'BIDV      ', N'39852250844587', N'Vũ Việt Hà                                        ', N'May tay áo thun', N'PX0103', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00011', N'Trần Ngọc Hà', 0, CAST(N'2000-10-06' AS Date), N'Thị trấn Nhà Bè, Huyện Nhà Bè, Hồ Chí Minh', N'0924655437', N'BIDV      ', N'41797326088406', N'Trần Ngọc Hà                                      ', N'May tay áo thun', N'PX0103', N'Quản Đốc')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00012', N'Đào Minh Hạnh', 0, CAST(N'2000-11-07' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924655441', N'BIDV      ', N'84711746467585', N'Đào Minh Hạnh                                     ', N'May tay áo thun', N'PX0103', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00013', N'Đỗ Quốc Hưng', 1, CAST(N'1988-04-08' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924655440', N'BIDV      ', N'82095324397297', N'Đỗ Quốc Hưng                                      ', N'May thân áo thun', N'PX0104', N'Tổ Trưởng')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00014', N'Lê Phương Liên', 0, CAST(N'1997-06-07' AS Date), N'Phường 10, Quận Phú Nhuận, Hồ Chí Minh', N'0924655436', N'BIDV      ', N'26094071003093', N'Lê Phương Liên                                    ', N'May thân áo thun', N'PX0104', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00015', N'Nguyễn Anh Mai', 0, CAST(N'1988-02-08' AS Date), N'Thị trấn Nhà Bè, Huyện Nhà Bè, Hồ Chí Minh', N'0924655443', N'BIDV      ', N'54060191575533', N'Nguyễn Anh Mai                                    ', N'May thân áo thun', N'PX0104', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00016', N'Nguyễn Hoàng Nam', 1, CAST(N'1988-02-08' AS Date), N'Phường 04, Quận 4, Hồ Chí Minh', N'0924681193', N'BIDV      ', N'83793802787550', N'Nguyễn Hoàng Nam                                  ', N'May thân áo thun', N'PX0104', N'Quản Đốc')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00017', N'Trịnh Hà Phương', 0, CAST(N'1989-08-12' AS Date), N'Phường Cát Lái, Quận 2, Hồ Chí Minh', N'0924655438', N'BIDV      ', N'40146587822681', N'Trịnh Hà Phương                                   ', N'May cúc áo khoác', N'PX0201', N'Tổ Trưởng')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00018', N'Trần Lê Nguyên', 1, CAST(N'1996-08-03' AS Date), N'Thị trấn Nhà Bè, Huyện Nhà Bè, Hồ Chí Minh', N'0924655433', N'BIDV      ', N'34915409111582', N'Trần Lê Nguyên                                    ', N'May cúc áo khoác', N'PX0201', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00019', N'Lê Minh Tâm', 1, CAST(N'1999-04-08' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924655439', N'BIDV      ', N'12939394799082', N'Lê Minh Tâm                                       ', N'May cúc áo khoác', N'PX0201', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00020', N'Trần Diệu Thúy', 0, CAST(N'1988-09-08' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924655442', N'BIDV      ', N'35574343234783', N'Trần Diệu Thúy                                    ', N'May cúc áo khoác', N'PX0201', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00021', N'Trịnh Minh Thư', 0, CAST(N'1999-09-06' AS Date), N'Phường 10, Quận Phú Nhuận, Hồ Chí Minh', N'0366638592', N'BIDV      ', N'53247386566557', N'Trịnh Minh Thư                                    ', N'May cổ áo khoác', N'PX0202', N'Quản Đốc')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00022', N'Lê Minh Trí', 1, CAST(N'1988-08-08' AS Date), N'Thị trấn Nhà Bè, Huyện Nhà Bè, Hồ Chí Minh', N'0354298675', N'BIDV      ', N'74135021391029', N'Lê Minh Trí                                       ', N'May cổ áo khoác', N'PX0202', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00023', N'Định Quốc Trung', 1, CAST(N'2000-07-06' AS Date), N'Phường 01, Quận 6, Hồ Chí Minh', N'0986354872', N'BIDV      ', N'55331013415020', N'Định Quốc Trung                                   ', N'May cổ áo khoác', N'PX0202', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00024', N'Vũ Quang Vinh', 1, CAST(N'2000-11-07' AS Date), N'Thị trấn Nhà Bè, Huyện Nhà Bè, Hồ Chí Minh', N'0365928521', N'BIDV      ', N'16595179432684', N'Vũ Quang Vinh                                     ', N'May cổ áo khoác', N'PX0202', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00025', N'Mai Chiến Thắng', 1, CAST(N'1988-04-08' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0369852475', N'BIDV      ', N'89076730009204', N'Mai Chiến Thắng                                   ', N'May tay áo khoác', N'PX0203', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00026', N'Bùi Công Hải', 1, CAST(N'1995-10-10' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924681101', N'BIDV      ', N'57529282882355', N'Bùi Công Hải                                      ', N'May tay áo khoác', N'PX0203', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00027', N'Nguyễn Thanh Tín', 1, CAST(N'1999-10-06' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924681102', N'BIDV      ', N'19647155928646', N'Nguyễn Thanh Tín                                  ', N'May tay áo khoác', N'PX0203', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00028', N'Trần Thanh Thẳng', 0, CAST(N'1989-11-09' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924681102', N'BIDV      ', N'18482622067149', N'Trần Thanh Thẳng                                  ', N'May tay áo khoác', N'PX0203', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00029', N'Trần Thị Thanh Phi', 0, CAST(N'2001-12-08' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924681104', N'BIDV      ', N'13453562458530', N'Trần Thị Thanh Phi                                ', N'May thân áo khoác', N'PX0204', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00030', N'Nguyễn Diệp Tú', 1, CAST(N'1999-04-06' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924681105', N'BIDV      ', N'63614550316628', N'Nguyễn Diệp Tú                                    ', N'May thân áo khoác', N'PX0204', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00031', N'Trần Nam Anh Bắc', 1, CAST(N'1998-03-05' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924681106', N'BIDV      ', N'46324326549102', N'Trần Nam Anh Bắc                                  ', N'May thân áo khoác', N'PX0204', N'Quản Đốc')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00032', N'Hoàng Ngọc Thu', 0, CAST(N'2000-06-02' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924681107', N'BIDV      ', N'21508504273152', N'Hoàng Ngọc Thu                                    ', N'May thân áo khoác', N'PX0204', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00033', N'Nguyễn Thị Kim Tuyền', 1, CAST(N'1990-06-04' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924681108', N'BIDV      ', N'54687959117505', N'Nguyễn Thị Kim Tuyền                              ', N'May cúc áo sơ mi', N'PX0301', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00034', N'Phạm Hồng Hồ', 1, CAST(N'1997-05-11' AS Date), N'Xã Xuân Thới Thượng, Huyện Hóc Môn, Hồ Chí Minh', N'0924681109', N'BIDV      ', N'51124039835874', N'Phạm Hồng Hồ                                      ', N'May cúc áo sơ mi', N'PX0301', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00035', N'Vũ Việt Thu', 0, CAST(N'2000-07-06' AS Date), N'Xã Xuân Thới Thượng, Huyện Hóc Môn, Hồ Chí Minh', N'0924681110', N'BIDV      ', N'70654081633748', N'Vũ Việt Thu                                       ', N'May cúc áo sơ mi', N'PX0301', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00036', N'Trần Ngọc Bích', 0, CAST(N'2000-11-07' AS Date), N'Xã Xuân Thới Thượng, Huyện Hóc Môn, Hồ Chí Minh', N'0924681111', N'BIDV      ', N'41062091414064', N'Trần Ngọc Bích                                    ', N'May cúc áo sơ mi', N'PX0301', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00037', N'Đào Minh Hồ', 1, CAST(N'1988-04-08' AS Date), N'Xã Xuân Thới Thượng, Huyện Hóc Môn, Hồ Chí Minh', N'0924681112', N'BIDV      ', N'49845419111868', N'Đào Minh Hồ                                       ', N'May cổ áo sơ mi', N'PX0302', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00038', N'Đỗ Quốc Thái', 0, CAST(N'1997-06-07' AS Date), N'Phường 07, Quận 6, Hồ Chí Minh', N'0924681113', N'BIDV      ', N'46008446009133', N'Đỗ Quốc Thái                                      ', N'May cổ áo sơ mi', N'PX0302', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00039', N'Lê Phương Linh', 0, CAST(N'1988-06-08' AS Date), N'Phường 07, Quận 6, Hồ Chí Minh', N'0924681114', N'BIDV      ', N'11665968960082', N'Lê Phương Linh                                    ', N'May cổ áo sơ mi', N'PX0302', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00040', N'Nguyễn Anh Thư', 1, CAST(N'1988-02-08' AS Date), N'Phường 07, Quận 6, Hồ Chí Minh', N'0924681115', N'BIDV      ', N'20222375188519', N'Nguyễn Anh Thư                                    ', N'May cổ áo sơ mi', N'PX0302', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00041', N'Nguyễn Hoàng Đông', 0, CAST(N'1989-08-12' AS Date), N'Phường 07, Quận 6, Hồ Chí Minh', N'0924681116', N'BIDV      ', N'74861539778509', N'Nguyễn Hoàng Đông                                 ', N'May tay áo sơ mi', N'PX0303', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00042', N'Trịnh Hà Phùng', 1, CAST(N'1996-08-03' AS Date), N'Phường 15, Quận 10, Hồ Chí Minh', N'0924681117', N'BIDV      ', N'22136467702196', N'Trịnh Hà Phùng                                    ', N'May tay áo sơ mi', N'PX0303', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00043', N'Trần Lê Sơn', 1, CAST(N'1999-04-08' AS Date), N'Phường 15, Quận 10, Hồ Chí Minh', N'0924681118', N'BIDV      ', N'49629213083313', N'Trần Lê Sơn                                       ', N'May tay áo sơ mi', N'PX0303', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00044', N'Lê Minh Tình', 0, CAST(N'1988-09-08' AS Date), N'Phường 15, Quận 10, Hồ Chí Minh', N'0924681119', N'BIDV      ', N'51896515101467', N'Lê Minh Tình                                      ', N'May tay áo sơ mi', N'PX0303', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00045', N'Trần Diệu Thi', 0, CAST(N'1999-09-06' AS Date), N'Phường 15, Quận 10, Hồ Chí Minh', N'0924681120', N'BIDV      ', N'39600814043832', N'Trần Diệu Thi                                     ', N'May thân áo sơ mi', N'PX0304', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00046', N'Trịnh Minh Phong', 1, CAST(N'1988-08-08' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681121', N'BIDV      ', N'16965513627033', N'Trịnh Minh Phong                                  ', N'May thân áo sơ mi', N'PX0304', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00047', N'Lê Minh Duẩn', 1, CAST(N'2000-07-06' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681122', N'BIDV      ', N'28000597764884', N'Lê Minh Duẩn                                      ', N'May thân áo sơ mi', N'PX0304', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00048', N'Định Quốc Bắc', 1, CAST(N'2000-11-07' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681123', N'BIDV      ', N'89192121633367', N'Định Quốc Bắc                                     ', N'May thân áo sơ mi', N'PX0304', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00049', N'Vũ Quang Minh', 1, CAST(N'1988-04-08' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681124', N'BIDV      ', N'41342824115010', N'Vũ Quang Minh                                     ', N'May đai quần tây', N'PX0401', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00050', N'Đông Thất Bại', 1, CAST(N'1994-03-10' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681125', N'BIDV      ', N'72622651594168', N'Đông Thất Bại                                     ', N'May đai quần tây', N'PX0401', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00051', N'Bùi Công Thu', 1, CAST(N'1994-03-10' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681126', N'BIDV      ', N'44482257997693', N'Bùi Công Thu                                      ', N'May đai quần tây', N'PX0401', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00052', N'Nguyễn Thanh Tú', 1, CAST(N'1995-10-10' AS Date), N'Phường 05, Quận Gò Vấp, Hồ Chí Minh', N'0924681127', N'BIDV      ', N'57776121621486', N'Nguyễn Thanh Tú                                   ', N'May đai quần tây', N'PX0401', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00053', N'Trần Thanh Tèo', 1, CAST(N'1999-10-06' AS Date), N'Xã Phú Mỹ Hưng, Huyện Củ Chi, Hồ Chí Minh', N'0924681128', N'BIDV      ', N'16139708402010', N'Trần Thanh Tèo                                    ', N'May khóa quần tây', N'PX0402', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00054', N'Trần Thị Thanh Nhí', 0, CAST(N'1989-11-09' AS Date), N'Xã Phú Mỹ Hưng, Huyện Củ Chi, Hồ Chí Minh', N'0924681129', N'BIDV      ', N'59845550890073', N'Trần Thị Thanh Nhí                                ', N'May khóa quần tây', N'PX0402', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00055', N'Nguyễn Diệp Em', 0, CAST(N'2001-12-08' AS Date), N'Xã Phú Mỹ Hưng, Huyện Củ Chi, Hồ Chí Minh', N'0924681130', N'BIDV      ', N'82318711557875', N'Nguyễn Diệp Em                                    ', N'May khóa quần tây', N'PX0402', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00056', N'Trần Nam Em', 1, CAST(N'1999-04-06' AS Date), N'Xã Phú Mỹ Hưng, Huyện Củ Chi, Hồ Chí Minh', N'0924681131', N'BIDV      ', N'31322273631726', N'Trần Nam Em                                       ', N'May khóa quần tây', N'PX0402', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00057', N'Hoàng Ngọc Thi', 1, CAST(N'1998-03-05' AS Date), N'Xã Phú Mỹ Hưng, Huyện Củ Chi, Hồ Chí Minh', N'0924681132', N'BIDV      ', N'84688493855207', N'Hoàng Ngọc Thi                                    ', N'May túi quần tây', N'PX0403', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00058', N'Nguyễn Thị Kim Nhung', 0, CAST(N'2000-06-02' AS Date), N'Xã Phú Mỹ Hưng, Huyện Củ Chi, Hồ Chí Minh', N'0924681133', N'BIDV      ', N'68653840768768', N'Nguyễn Thị Kim Nhung                              ', N'May túi quần tây', N'PX0403', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00059', N'Phạm Hồng Quang', 1, CAST(N'1990-06-04' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681134', N'BIDV      ', N'76118106961101', N'Phạm Hồng Quang                                   ', N'May túi quần tây', N'PX0403', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00060', N'Vũ Việt Hồ', 1, CAST(N'1997-05-11' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681135', N'BIDV      ', N'44502991356759', N'Vũ Việt Hồ                                        ', N'May túi quần tây', N'PX0403', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00061', N'Trần Ngọc Hồ', 0, CAST(N'2000-07-06' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681136', N'BIDV      ', N'50062172103777', N'Trần Ngọc Hồ                                      ', N'May ống quần tây', N'PX0404', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00062', N'Đào Minh Vân', 0, CAST(N'2000-11-07' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681137', N'BIDV      ', N'78094301850019', N'Đào Minh Vân                                      ', N'May ống quần tây', N'PX0404', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00063', N'Đỗ Quốc Hiền', 1, CAST(N'1988-04-08' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681138', N'BIDV      ', N'63829696671777', N'Đỗ Quốc Hiền                                      ', N'May ống quần tây', N'PX0404', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00064', N'Lê Phương Bích', 0, CAST(N'1997-06-07' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681139', N'BIDV      ', N'27874707315564', N'Lê Phương Bích                                    ', N'May ống quần tây', N'PX0404', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00065', N'Nguyễn Anh Mãi', 0, CAST(N'1988-06-08' AS Date), N'Phường 05, Quận 5, Hồ Chí Minh', N'0924681140', N'BIDV      ', N'29761412867182', N'Nguyễn Anh Mãi                                    ', N'May đai quần jean', N'PX0501', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00066', N'Nguyễn Hoàng Bắc', 1, CAST(N'1988-02-08' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681141', N'BIDV      ', N'74205159672800', N'Nguyễn Hoàng Bắc                                  ', N'May đai quần jean', N'PX0501', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00067', N'Trịnh Hà Pháp', 0, CAST(N'1989-08-12' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681142', N'BIDV      ', N'69286606810210', N'Trịnh Hà Pháp                                     ', N'May đai quần jean', N'PX0501', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00068', N'Trần Lê Dúng', 1, CAST(N'1996-08-03' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681143', N'BIDV      ', N'83697032067925', N'Trần Lê Dúng                                      ', N'May đai quần jean', N'PX0501', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00069', N'Lê Minh Tú', 1, CAST(N'1999-04-08' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681144', N'BIDV      ', N'72101515896366', N'Lê Minh Tú                                        ', N'May khóa quần jean', N'PX0502', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00070', N'Trần Diệu Nga', 0, CAST(N'1988-09-08' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681145', N'BIDV      ', N'31747553231082', N'Trần Diệu Nga                                     ', N'May khóa quần jean', N'PX0502', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00071', N'Trịnh Minh My', 0, CAST(N'1999-09-06' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681146', N'BIDV      ', N'12784167442118', N'Trịnh Minh My                                     ', N'May khóa quần jean', N'PX0502', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00072', N'Lê Minh Tuệ', 1, CAST(N'1988-08-08' AS Date), N'Phường Bình Hưng Hòa, Quận Bình Tân, Hồ Chí Minh', N'0924681147', N'BIDV      ', N'18618281388051', N'Lê Minh Tuệ                                       ', N'May khóa quần jean', N'PX0502', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00073', N'Định Quốc Tây', 1, CAST(N'2000-07-06' AS Date), N'Phường Bình Hưng Hòa, Quận Bình Tân, Hồ Chí Minh', N'0924681148', N'BIDV      ', N'56597497498479', N'Định Quốc Tây                                     ', N'May túi quần jean', N'PX0503', N'Tổ Trưởng')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00074', N'Vũ Quang Hệ', 1, CAST(N'2000-01-07' AS Date), N'Phường Bình Hưng Hòa, Quận Bình Tân, Hồ Chí Minh', N'0924681149', N'BIDV      ', N'70104850588964', N'Vũ Quang Hệ                                       ', N'May túi quần jean', N'PX0503', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00075', N'Mai Chiến Đấu', 1, CAST(N'1988-04-08' AS Date), N'Phường Bình Hưng Hòa, Quận Bình Tân, Hồ Chí Minh', N'0924681150', N'BIDV      ', N'27620429430577', N'Mai Chiến Đấu                                     ', N'May túi quần jean', N'PX0503', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00076', N'Bùi Công Minh', 1, CAST(N'1995-10-10' AS Date), N'Phường Bình Hưng Hòa, Quận Bình Tân, Hồ Chí Minh', N'0924681151', N'BIDV      ', N'37030713528454', N'Bùi Công Minh                                     ', N'May túi quần jean', N'PX0503', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00077', N'Nguyễn Thanh Tính', 1, CAST(N'1999-10-06' AS Date), N'Phường Bình Hưng Hòa, Quận Bình Tân, Hồ Chí Minh', N'0924681152', N'BIDV      ', N'13727007629938', N'Nguyễn Thanh Tính                                 ', N'May ống quần jean', N'PX0504', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00078', N'Trần Thanh Hà', 0, CAST(N'1989-11-09' AS Date), N'Thị trấn Cần Thạnh, Huyện Cần Giờ, Hồ Chí Minh', N'0924681153', N'BIDV      ', N'14539965793972', N'Trần Thanh Hà                                     ', N'May ống quần jean', N'PX0504', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00079', N'Trần Thị Thanh Vũ', 0, CAST(N'2001-12-08' AS Date), N'Thị trấn Cần Thạnh, Huyện Cần Giờ, Hồ Chí Minh', N'0924681154', N'BIDV      ', N'21367023248955', N'Trần Thị Thanh Vũ                                 ', N'May ống quần jean', N'PX0504', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00080', N'Nguyễn Diệp Tâm', 1, CAST(N'1999-01-06' AS Date), N'Thị trấn Cần Thạnh, Huyện Cần Giờ, Hồ Chí Minh', N'0924681155', N'BIDV      ', N'25636623919454', N'Nguyễn Diệp Tâm                                   ', N'May ống quần jean', N'PX0504', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00081', N'Nguyễn Thanh Tâm', 1, CAST(N'1995-10-10' AS Date), N'Thị trấn Cần Thạnh, Huyện Cần Giờ, Hồ Chí Minh', N'0962342550', N'BIDV      ', N'64753006736921', N'Nguyễn Thanh Tâm                                  ', N'May cúc áo thun', N'PX0101', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00082', N'Đỗ Quốc Hưng', 1, CAST(N'1988-04-08' AS Date), N'Phường Trường Thạnh, Quận 9, Hồ Chí Minh', N'0924655440', N'BIDV      ', N'82095324397297', N'Đỗ Quốc Hưng                                      ', N'May thân áo thun', N'PX0104', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00083', N'Trần Nam Anh', 1, CAST(N'1999-12-06' AS Date), N'Phường 01, Quận 6, Hồ Chí Minh', N'0973266558', N'BIDV      ', N'10144289403145', N'Trần Nam Anh                                      ', N'May cổ áo thun', N'PX0102', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00084', N'Trịnh Hà Pháp', 0, CAST(N'1989-08-12' AS Date), N'Phường Tăng Nhơn Phú B, Quận 9, Hồ Chí Minh', N'0924681142', N'BIDV      ', N'69286606810210', N'Trịnh Hà Pháp                                     ', N'May đai quần jean', N'PX0501', N'Công Nhân')
INSERT [dbo].[NhanVienSanXuat] ([MaNhanVien], [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [ChuyenMon], [MaTo], [ChucVu]) VALUES (N'NVSX00085', N'Đào Minh Hạnh', 0, CAST(N'2000-11-07' AS Date), N'Xã Quy Đức, Huyện Bình Chánh, Hồ Chí Minh', N'0924655441', N'BIDV      ', N'84711746467585', N'Đào Minh Hạnh                                     ', N'May tay áo thun', N'PX0103', N'Công Nhân')
GO
SET IDENTITY_INSERT [dbo].[PhanCong] ON 

INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (3, N'NH14001', N'NVSX00080', CAST(N'2022-12-03' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (4, N'NH14002', N'NVSX00079', CAST(N'2022-12-03' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (5, N'NH14003', N'NVSX00078', CAST(N'2022-12-03' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (7, N'NH14004', N'NVSX00078', CAST(N'2022-12-02' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (8, N'NH14005', N'NVSX00077', CAST(N'2022-12-03' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (9, N'NH14006', N'NVSX00076', CAST(N'2022-12-02' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (11, N'NH14007', N'NVSX00075', CAST(N'2022-12-03' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (12, N'NH14002', N'NVSX00074', CAST(N'2022-12-03' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (13, N'NH14003', N'NVSX00058', CAST(N'2022-12-04' AS Date), 1)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (14, N'NH14003', N'NVSX00005', CAST(N'2022-12-17' AS Date), 19)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (15, N'NH14004', N'NVSX00080', CAST(N'2022-12-16' AS Date), 19)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (16, N'BL58817', N'NVSX00010', CAST(N'2022-12-17' AS Date), 9)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (17, N'BR04436', N'NVSX00002', CAST(N'2022-12-17' AS Date), 23)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (18, N'BR04490', N'NVSX00078', CAST(N'2022-12-16' AS Date), 23)
INSERT [dbo].[PhanCong] ([MaPhanCong], [MaQuyTrinh], [MaNhanVien], [NgayThamGia], [MaHopDong]) VALUES (19, N'BF32723', N'NVSX00005', CAST(N'2022-12-16' AS Date), 5)
SET IDENTITY_INSERT [dbo].[PhanCong] OFF
GO
INSERT [dbo].[PhanXuong] ([MaPhanXuong], [TenPhanXuong], [MaQuanDoc]) VALUES (N'PX01', N'Phân xưởng may áo thun', N'NVSX00001')
INSERT [dbo].[PhanXuong] ([MaPhanXuong], [TenPhanXuong], [MaQuanDoc]) VALUES (N'PX02', N'Phân xưởng may áo khoác', N'NVSX00006')
INSERT [dbo].[PhanXuong] ([MaPhanXuong], [TenPhanXuong], [MaQuanDoc]) VALUES (N'PX03', N'Phân xưởng may áo sơ mi', N'NVSX00011')
INSERT [dbo].[PhanXuong] ([MaPhanXuong], [TenPhanXuong], [MaQuanDoc]) VALUES (N'PX04', N'Phân xưởng may quần tây', N'NVSX00016')
INSERT [dbo].[PhanXuong] ([MaPhanXuong], [TenPhanXuong], [MaQuanDoc]) VALUES (N'PX05', N'Phân xưởng may quần jean', N'NVSX00021')
INSERT [dbo].[PhanXuong] ([MaPhanXuong], [TenPhanXuong], [MaQuanDoc]) VALUES (N'PX06', N'pah ', NULL)
GO
INSERT [dbo].[PhongBan] ([MaPhongBan], [TenPhongBan], [MaTruongPhong]) VALUES (N'PB01', N'Phòng nhân sự', NULL)
INSERT [dbo].[PhongBan] ([MaPhongBan], [TenPhongBan], [MaTruongPhong]) VALUES (N'PB02', N'Phòng tài chính kế toán', N'NVHC00085')
INSERT [dbo].[PhongBan] ([MaPhongBan], [TenPhongBan], [MaTruongPhong]) VALUES (N'PB03', N'Phòng hành chính', N'NVHC00089')
INSERT [dbo].[PhongBan] ([MaPhongBan], [TenPhongBan], [MaTruongPhong]) VALUES (N'PB04', N'Phòng marketing', NULL)
INSERT [dbo].[PhongBan] ([MaPhongBan], [TenPhongBan], [MaTruongPhong]) VALUES (N'PB05', N'Phòng kinh doanh', N'NVHC00097')
INSERT [dbo].[PhongBan] ([MaPhongBan], [TenPhongBan], [MaTruongPhong]) VALUES (N'PB06', N'Phong ban 2', NULL)
GO
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BF32701', N'làm tay áo', 3000, N'BF327', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BF32723', N'làm thân áo', 4000, N'BF327', 3)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BF32741', N'làm cổ áo', 1000, N'BF327', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BF32765', N'làm nút áo', 2000, N'BF327', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BL58817', N'làm thân áo', 4000, N'BL588', 3)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BL58834', N'làm cổ áo', 1000, N'BL588', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BL58835', N'làm cúc áo', 2000, N'BL588', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BL58837', N'làm tay áo', 3000, N'BL588', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BR04412', N'làm cổ áo', 4000, N'BR044', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BR04436', N'làm tay áo', 3000, N'BR044', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'BR04490', N'cắt vải', 2000, N'BR044', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'CB12720', N'gia công tay áo', 4000, N'CB127', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'CB12758', N'gia công cổ áo', 1000, N'CB127', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'CB12787', N'gia công thân áo', 4000, N'CB127', 3)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'IZ75301', N'Cắt vải', 400, N'IZ753', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'IZ75302', N'Gia công thân', 700, N'IZ753', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'IZ75303', N'Gia công tay áo', 500, N'IZ753', 3)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'IZ75304', N'Gia công khóa kéo', 300, N'IZ753', 4)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'IZ75305', N'Gấp xếp', 300, N'IZ753', 5)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'JQ53981', N'may co', 1000, N'JQ539', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14001', N'Cắt vải', 400, N'NH140', 1)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14002', N'Gia công thân trước', 600, N'NH140', 2)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14003', N'Gia công thân sau', 500, N'NH140', 3)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14004', N'Gia công tay áo', 400, N'NH140', 4)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14005', N'Gia công cổ áo', 400, N'NH140', 5)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14006', N'Đính khuy', 300, N'NH140', 6)
INSERT [dbo].[QuyTrinh] ([MaQuyTrinh], [TenQuyTrinh], [GiaQuyTrinh], [MaSanPham], [ThuTuSanXuat]) VALUES (N'NH14007', N'Ủi gấp xếp', 300, N'NH140', 7)
GO
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'BF327', N'Áo thun giữ nhiệt')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'BL588', N'Áo khoác nam')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'BR044', N'Áo thun nữ')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'CB127', N'Áo hoodie')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'GS039', N'Áo thun nam')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'IZ753', N'Áo khoác dù')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'JQ539', N'Áo sơ mi nữ')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'NH140', N'Áo sơ mi nam')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'QX946', N'Áo len nam')
INSERT [dbo].[SanPham] ([MaSanPham], [TenSanPham]) VALUES (N'WF376', N'Áo len nữ')
GO
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVHC00000', N'mGDZF+Z9NhKqEL1jFWZriBIiFM3mHGDIeWwDfXBcRn0=snhsllfo89a924ladldanfl8os2swo', NULL, N'NVHC00000')
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVHC00081', N'JgFOrC5BUKdq9t0UGGlqE8GwKncUS1tOtIS+flYf5Yo=hslh9sa9898hjjjl98sjsdwh99fdfo', NULL, N'NVHC00081')
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVHC00082', N'DOcr0ReFI71nDyKTyrVeEAywTdZnGfLd0gzPI/ic7l0=ldj9ds892ha9dl8dos9sf442aadw8o', NULL, N'NVHC00082')
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVHC00085', N'DOcr0ReFI71nDyKTyrVeEAywTdZnGfLd0gzPI/ic7l0=ldj9ds892ha9dl8dos9sf442aadw8o', NULL, N'NVHC00085')
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVSX00001', N'DOcr0ReFI71nDyKTyrVeEAywTdZnGfLd0gzPI/ic7l0=ldj9ds892ha9dl8dos9sf442aadw8o', N'NVSX00001', NULL)
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVSX00002', N'DOcr0ReFI71nDyKTyrVeEAywTdZnGfLd0gzPI/ic7l0=ldj9ds892ha9dl8dos9sf442aadw8o', N'NVSX00002', NULL)
INSERT [dbo].[TaiKhoan] ([TenDangNhap], [MatKhau], [MaNVSX], [MaNVHC]) VALUES (N'NVSX00073', N'DOcr0ReFI71nDyKTyrVeEAywTdZnGfLd0gzPI/ic7l0=ldj9ds892ha9dl8dos9sf442aadw8o', N'NVSX00073', NULL)
GO
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0101', N'Tổ may cúc áo thun', N'NVSX00002', N'PX01')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0102', N'Tổ may cổ áo thun', N'NVSX00005', N'PX01')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0103', N'Tổ may tay áo thun', N'NVSX00009', N'PX01')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0104', N'Tổ may thân áo thun', N'NVSX00013', N'PX01')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0201', N'Tổ may cúc áo khóac', N'NVSX00017', N'PX02')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0202', N'Tổ may cổ áo khoác', NULL, N'PX02')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0203', N'Tổ may tay áo khoác', NULL, N'PX02')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0204', N'Tổ may thân áo khoác', NULL, N'PX02')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0301', N'Tổ may cúc áo sơ mi', NULL, N'PX03')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0302', N'Tổ may cổ áo sơ mi', NULL, N'PX03')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0303', N'Tổ may tay áo sơ mi', NULL, N'PX03')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0304', N'Tổ may thân áo sơ mi', NULL, N'PX03')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0401', N'Tổ may đai quần tây', NULL, N'PX04')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0402', N'Tổ may khóa quần tây', NULL, N'PX04')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0403', N'Tổ may túi quần tây', NULL, N'PX04')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0404', N'Tổ may ống quần tây', NULL, N'PX04')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0501', N'Tổ may đai quần jean', NULL, N'PX05')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0502', N'Tổ may khóa quần jean', NULL, N'PX05')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0503', N'Tổ may túi quần jean', N'NVSX00073', N'PX05')
INSERT [dbo].[ToSanXuat] ([MaTo], [TenTo], [MaToTruong], [MaPhanXuong]) VALUES (N'PX0504', N'Tổ may ống quần jean', NULL, N'PX05')
GO
ALTER TABLE [dbo].[HopDongSanXuat] ADD  CONSTRAINT [DF_HopDongSanXuat_SoLuongHoanThanh]  DEFAULT ((0)) FOR [SoLuongHoanThanh]
GO
ALTER TABLE [dbo].[NhanVienHanhChinh] ADD  CONSTRAINT [DF_NhanVienHanhChinh_MaNhanVien]  DEFAULT ([DBO].[GENERATE_EMPLOYEE_OFFICE_ID]()) FOR [MaNhanVien]
GO
ALTER TABLE [dbo].[NhanVienSanXuat] ADD  CONSTRAINT [DF_NhanVienSanXuat_MaNhanVien]  DEFAULT ([DBO].[GENERATE_WORKER_ID]()) FOR [MaNhanVien]
GO
ALTER TABLE [dbo].[NhanVienSanXuat] ADD  CONSTRAINT [DF_NhanVienSanXuat_ChucVu]  DEFAULT (N'Công Nhân') FOR [ChucVu]
GO
ALTER TABLE [dbo].[ChamCongHanhChinh]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongHanhChinh_NhanVienHanhChinh] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVienHanhChinh] ([MaNhanVien])
GO
ALTER TABLE [dbo].[ChamCongHanhChinh] CHECK CONSTRAINT [FK_ChamCongHanhChinh_NhanVienHanhChinh]
GO
ALTER TABLE [dbo].[ChamCongSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_ChamCongSanXuat_PhanCong] FOREIGN KEY([MaPhanCong])
REFERENCES [dbo].[PhanCong] ([MaPhanCong])
GO
ALTER TABLE [dbo].[ChamCongSanXuat] CHECK CONSTRAINT [FK_ChamCongSanXuat_PhanCong]
GO
ALTER TABLE [dbo].[HopDongSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietSanXuat_SanPham] FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[HopDongSanXuat] CHECK CONSTRAINT [FK_ChiTietSanXuat_SanPham]
GO
ALTER TABLE [dbo].[NhanVienSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_NhanVienSanXuat_ToSanXuat] FOREIGN KEY([MaTo])
REFERENCES [dbo].[ToSanXuat] ([MaTo])
GO
ALTER TABLE [dbo].[NhanVienSanXuat] CHECK CONSTRAINT [FK_NhanVienSanXuat_ToSanXuat]
GO
ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_ChiTietSanXuat] FOREIGN KEY([MaHopDong])
REFERENCES [dbo].[HopDongSanXuat] ([MaHopDong])
GO
ALTER TABLE [dbo].[PhanCong] CHECK CONSTRAINT [FK_PhanCong_ChiTietSanXuat]
GO
ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_NhanVienSanXuat] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVienSanXuat] ([MaNhanVien])
GO
ALTER TABLE [dbo].[PhanCong] CHECK CONSTRAINT [FK_PhanCong_NhanVienSanXuat]
GO
ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_QuyTrinh] FOREIGN KEY([MaQuyTrinh])
REFERENCES [dbo].[QuyTrinh] ([MaQuyTrinh])
GO
ALTER TABLE [dbo].[PhanCong] CHECK CONSTRAINT [FK_PhanCong_QuyTrinh]
GO
ALTER TABLE [dbo].[PhongBan]  WITH CHECK ADD  CONSTRAINT [FK_PhongBan_NhanVienHanhChinh] FOREIGN KEY([MaTruongPhong])
REFERENCES [dbo].[NhanVienHanhChinh] ([MaNhanVien])
GO
ALTER TABLE [dbo].[PhongBan] CHECK CONSTRAINT [FK_PhongBan_NhanVienHanhChinh]
GO
ALTER TABLE [dbo].[QuyTrinh]  WITH CHECK ADD  CONSTRAINT [FK_QuyTrinh_SanPham] FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[QuyTrinh] CHECK CONSTRAINT [FK_QuyTrinh_SanPham]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVienHanhChinh] FOREIGN KEY([MaNVHC])
REFERENCES [dbo].[NhanVienHanhChinh] ([MaNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVienHanhChinh]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVienSanXuat] FOREIGN KEY([MaNVSX])
REFERENCES [dbo].[NhanVienSanXuat] ([MaNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVienSanXuat]
GO
ALTER TABLE [dbo].[ToSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_ToSanXuat_PhanXuong] FOREIGN KEY([MaPhanXuong])
REFERENCES [dbo].[PhanXuong] ([MaPhanXuong])
GO
ALTER TABLE [dbo].[ToSanXuat] CHECK CONSTRAINT [FK_ToSanXuat_PhanXuong]
GO
/****** Object:  Trigger [dbo].[TRIGGER_INSERT_CCSX]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_INSERT_CCSX] ON [dbo].[ChamCongSanXuat] AFTER INSERT, UPDATE, DELETE AS
BEGIN
    DECLARE @MACHAMCONG INT, @MAHOPDONG INT, @SOLUONG INT
    SELECT @MACHAMCONG = MACHAMCONG FROM INSERTED
    SELECT @MAHOPDONG = PC.MaHopDong
    FROM ChamCongSanXuat    AS CCSX
        INNER JOIN PhanCong AS PC
            ON CCSX.MaPhanCong = PC.MaPhanCong
    WHERE MaChamCong = @MACHAMCONG

	--SELECT @SOLUONG = SUM(CCSX.SoLuongThanhPham)
	--FROM ChamCongSanXuat AS CCSX INNER JOIN
 --                        PhanCong AS PC ON CCSX.MaPhanCong = PC.MaPhanCong INNER JOIN
 --                        QuyTrinh AS QT ON PC.MaQuyTrinh = QT.MaQuyTrinh
	--WHERE MaHopDong = @MAHOPDONG AND QT.ThuTuSanXuat = (SELECT MAX(ThuTuSanXuat) FROM QuyTrinh WHERE MaSanPham = (SELECT MaSanPham FROM QuyTrinh WHERE MaQuyTrinh = QT.MaQuyTrinh))
	--GROUP BY PC.MaQuyTrinh
	SELECT @SOLUONG = (SELECT MIN(SoLuong) FROM VIEW_CCSX WHERE MaHopDong = @MAHOPDONG)

	UPDATE HopDongSanXuat SET SoLuongHoanThanh = @SOLUONG WHERE MaHopDong = @MAHOPDONG
END
GO
ALTER TABLE [dbo].[ChamCongSanXuat] ENABLE TRIGGER [TRIGGER_INSERT_CCSX]
GO
/****** Object:  Trigger [dbo].[TRIGGER_UPDATE_HHSX]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_UPDATE_HHSX] ON [dbo].[HopDongSanXuat] AFTER UPDATE AS
BEGIN
	DECLARE @MAHOPDONG INT, @SOLUONG INT, @SOLUONGSX INT
	SELECT @MAHOPDONG = MAHOPDONG, @SOLUONG = SOLUONGHOANTHANH, @SOLUONGSX = SoLuongSanXuat FROM INSERTED
	IF @SOLUONG = @SOLUONGSX BEGIN
		UPDATE HopDongSanXuat SET TinhTrang = N'Hoàn Thành' WHERE MaHopDong = @MAHOPDONG
	END
	IF @SOLUONG != @SOLUONGSX BEGIN
		UPDATE HopDongSanXuat SET TinhTrang = N'Sản Xuất' WHERE MaHopDong = @MAHOPDONG
	END
END
GO
ALTER TABLE [dbo].[HopDongSanXuat] ENABLE TRIGGER [TRIGGER_UPDATE_HHSX]
GO
/****** Object:  Trigger [dbo].[TRIGGER_DELETE_NVHC]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_DELETE_NVHC] ON [dbo].[NhanVienHanhChinh] INSTEAD OF DELETE AS
BEGIN
	DECLARE @MaNhanVien CHAR(9), @MaPhongBan CHAR(4)
	SELECT @MaNhanVien = MaNhanVien, @MaPhongBan = MaPhongBan FROM DELETED
	IF EXISTS (SELECT * FROM PhongBan WHERE @MaNhanVien = MaTruongPhong) BEGIN
		UPDATE PhongBan SET MaTruongPhong = NULL WHERE MaPhongBan = @MaPhongBan
	END
	DELETE NhanVienHanhChinh WHERE MaNhanVien = @MaNhanVien
END
GO
ALTER TABLE [dbo].[NhanVienHanhChinh] ENABLE TRIGGER [TRIGGER_DELETE_NVHC]
GO
/****** Object:  Trigger [dbo].[TRIGGER_INSERT_NVHC]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_INSERT_NVHC] ON [dbo].[NhanVienHanhChinh] INSTEAD OF INSERT AS
BEGIN
	DECLARE @MaNhanVien CHAR(9), @TenNhanVien NVARCHAR(50), @GioiTinh BIT, @NgaySinh DATE, @DiaChi NVARCHAR(100), @SDT CHAR(10), @TenNganHang CHAR(10), @SoTaiKhoan CHAR(20), @TenNguoiThuHuong NCHAR(50), @LuongTheoChucDanh FLOAT, @ChucVu NVARCHAR(50), @MaPhongBan CHAR(4)
	SELECT @TenNhanVien = TenNhanVien, @GioiTinh = GioiTinh, @NgaySinh = NgaySinh, @DiaChi = DiaChi, @SDT = SDT, @TenNganHang = TenNganHang, @SoTaiKhoan = SoTaiKhoan, @TenNguoiThuHuong = TenNguoiThuHuong, @LuongTheoChucDanh = LuongTheoChucDanh, @ChucVu = ChucVu, @MaPhongBan = MaPhongBan, @MaNhanVien = MaNhanVien FROM INSERTED
	IF @ChucVu LIKE N'Trưởng Phòng' BEGIN
		IF (SELECT MATRUONGPHONG FROM PhongBan WHERE @MaPhongBan = MaPhongBan) IS NOT NULL BEGIN
			DECLARE @MATRUONGPHONG CHAR(9)
			SELECT @MATRUONGPHONG = MATRUONGPHONG FROM PhongBan WHERE MaPhongBan = @MaPhongBan
			UPDATE NhanVienHanhChinh SET ChucVu = N'Nhân Viên' WHERE MaNhanVien = @MATRUONGPHONG 
		END
	END
	INSERT [dbo].[NhanVienHanhChinh] (MaNhanVien, [TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [TenNganHang], [SoTaiKhoan], [TenNguoiThuHuong], [LuongTheoChucDanh], [ChucVu], [MaPhongBan]) VALUES (@MaNhanVien, @TenNhanVien, @GioiTinh, @NgaySinh, @DiaChi, @SDT, @TenNganHang, @SoTaiKhoan, @TenNguoiThuHuong, @LuongTheoChucDanh, @ChucVu, @MaPhongBan)
	IF @ChucVu LIKE N'Trưởng Phòng' BEGIN
		UPDATE PhongBan SET MaTruongPhong = @MaNhanVien WHERE MaPhongBan = @MaPhongBan
	END
END
GO
ALTER TABLE [dbo].[NhanVienHanhChinh] ENABLE TRIGGER [TRIGGER_INSERT_NVHC]
GO
/****** Object:  Trigger [dbo].[TRIGGER_UPDATE_NVHC]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_UPDATE_NVHC] ON [dbo].[NhanVienHanhChinh] INSTEAD OF UPDATE AS
BEGIN
	DECLARE @MaNhanVien CHAR(9), @TenNhanVien NVARCHAR(50), @GioiTinh BIT, @NgaySinh DATE, @DiaChi NVARCHAR(100), @SDT CHAR(10), @TenNganHang CHAR(10), @SoTaiKhoan CHAR(20), @TenNguoiThuHuong NCHAR(50), @LuongTheoChucDanh FLOAT, @ChucVu NVARCHAR(50), @MaPhongBan CHAR(4)
	DECLARE @ChucVu2 NVARCHAR(50), @MaPhongBan2 CHAR(4)
	SELECT @TenNhanVien = TenNhanVien, @GioiTinh = GioiTinh, @NgaySinh = NgaySinh, @DiaChi = DiaChi, @SDT = SDT, @TenNganHang = TenNganHang, @SoTaiKhoan = SoTaiKhoan, @TenNguoiThuHuong = TenNguoiThuHuong, @LuongTheoChucDanh = LuongTheoChucDanh, @ChucVu = ChucVu, @MaPhongBan = MaPhongBan, @MaNhanVien = MaNhanVien FROM INSERTED
	SELECT @ChucVu2 = ChucVu, @MaPhongBan2 = MaPhongBan FROM DELETED
	IF @MaPhongBan = @MaPhongBan2 BEGIN
		IF @ChucVu != @ChucVu2 BEGIN
			IF @ChucVu2 = N'Trưởng Phòng' AND @ChucVu = N'Nhân Viên' BEGIN
				UPDATE PhongBan SET MaTruongPhong = NULL WHERE MaPhongBan = @MaPhongBan
			END
			ELSE BEGIN
				UPDATE PhongBan SET MaTruongPhong = @MaNhanVien WHERE MaPhongBan = @MaPhongBan
			END
		END
	END
	ELSE BEGIN
		IF @ChucVu = @ChucVu2 BEGIN
			IF @ChucVu = N'Trưởng Phòng' BEGIN
				UPDATE PhongBan SET MaTruongPhong = NULL WHERE MaPhongBan = @MaPhongBan2
				UPDATE PhongBan SET MaTruongPhong = @MaNhanVien WHERE MaPhongBan = @MaPhongBan
			END
		END
		ELSE BEGIN
			IF @ChucVu = N'Trưởng Phòng' BEGIN
				UPDATE PhongBan SET MaTruongPhong = @MaNhanVien WHERE MaPhongBan = @MaPhongBan
			END
			ELSE BEGIN
				UPDATE PhongBan SET MaTruongPhong = NULL WHERE MaPhongBan = @MaPhongBan2
			END
		END
	END
	UPDATE NhanVienHanhChinh SET TenNhanVien = @TenNhanVien, GioiTinh = @GioiTinh, NgaySinh = @NgaySinh, DiaChi = @DiaChi, SDT = @SDT, TenNganHang = @TenNganHang, SoTaiKhoan = @SoTaiKhoan, TenNguoiThuHuong = @TenNguoiThuHuong, LuongTheoChucDanh = @LuongTheoChucDanh, ChucVu = @ChucVu, MaPhongBan = @MaPhongBan WHERE MaNhanVien = @MaNhanVien
END
GO
ALTER TABLE [dbo].[NhanVienHanhChinh] ENABLE TRIGGER [TRIGGER_UPDATE_NVHC]
GO
/****** Object:  Trigger [dbo].[TRIGGER_DELETE_NVSX]    Script Date: 12/17/2022 12:10:17 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_DELETE_NVSX] ON [dbo].[NhanVienSanXuat] INSTEAD OF DELETE AS
BEGIN
	DECLARE @MaNhanVien CHAR(9), @MaTo CHAR(6), @MaPhanXuong CHAR(4), @ChucVu NVARCHAR(50)
	SELECT @MaNhanVien = MaNhanVien, @MaTo = MaTo, @ChucVu = ChucVu FROM DELETED
	IF @ChucVu = N'Tổ Trưởng' BEGIN
		UPDATE ToSanXuat SET MaToTruong = NULL WHERE MaTo = @MaTo
		DELETE NhanVienSanXuat WHERE MaNhanVien = @MaNhanVien
	END
	IF @ChucVu = N'Quản Đốc' BEGIN
		SELECT @MaPhanXuong = MaPhanXuong FROM ToSanXuat WHERE MaTo = @MaTo
		UPDATE PhanXuong SET MaQuanDoc = NULL WHERE MaPhanXuong = @MaPhanXuong
		DELETE NhanVienSanXuat WHERE MaNhanVien = @MaNhanVien
	END
	IF @ChucVu = N'Công Nhân' BEGIN
		DELETE NhanVienSanXuat WHERE MaNhanVien = @MaNhanVien
	END
END
GO
ALTER TABLE [dbo].[NhanVienSanXuat] ENABLE TRIGGER [TRIGGER_DELETE_NVSX]
GO
/****** Object:  Trigger [dbo].[TRIGGER_INSERT_NVSX]    Script Date: 12/17/2022 12:10:18 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_INSERT_NVSX] ON [dbo].[NhanVienSanXuat] AFTER INSERT AS
BEGIN
	DECLARE @CHUCVU NVARCHAR(50), @MATRUONGPHONG CHAR(9), @TO CHAR(6), @MANHANVIEN CHAR(9)
	SELECT @CHUCVU = CHUCVU, @TO = MaTo, @MANHANVIEN = MaNhanVien FROM inserted
	IF @CHUCVU = N'Quản Đốc' BEGIN
		DECLARE @MAXUONG CHAR(4)
		SELECT @MAXUONG = MAPHANXUONG FROM ToSanXuat WHERE MaTo = @TO
		IF (SELECT MAQUANDOC FROM PhanXuong WHERE MaPhanXuong = @MAXUONG) IS NOT NULL BEGIN
			ROLLBACK
		END
		ELSE BEGIN
			UPDATE PhanXuong SET MAQUANDOC = @MANHANVIEN WHERE MaPhanXuong = @MAXUONG
		END
	END
	IF @CHUCVU = N'Tổ Trưởng' BEGIN
		IF (SELECT MATOTRUONG FROM ToSanXuat WHERE MaTo = @TO) IS NOT NULL BEGIN
			ROLLBACK
		END
		ELSE BEGIN
			UPDATE ToSanXuat SET MATOTRUONG = @MANHANVIEN WHERE MaTo = @TO
		END
	END
END
GO
ALTER TABLE [dbo].[NhanVienSanXuat] ENABLE TRIGGER [TRIGGER_INSERT_NVSX]
GO
/****** Object:  Trigger [dbo].[TRIGGER_UPDATE_NVSX]    Script Date: 12/17/2022 12:10:18 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TRIGGER_UPDATE_NVSX] ON [dbo].[NhanVienSanXuat] AFTER UPDATE AS
BEGIN
	DECLARE @MaNhanVien_Moi CHAR(9), @MaTo_Moi CHAR(6), @MaPhanXuong_Moi CHAR(4), @ChucVu_Moi NVARCHAR(50), @ChucVu_Cu NVARCHAR(50), @MaTo_Cu CHAR(6), @MaPhanXuong_Cu CHAR(4)
	SELECT @MaNhanVien_Moi = MaNhanVien, @MaTo_Moi = MaTo, @ChucVu_Moi = ChucVu FROM INSERTED
	SELECT @MaPhanXuong_Moi = MaPhanXuong FROM ToSanXuat WHERE MaTo = @MaTo_Moi
	SELECT @MaTo_Cu = MaTo, @ChucVu_Cu = ChucVu FROM DELETED
	SELECT @MaPhanXuong_Cu = MaPhanXuong FROM ToSanXuat WHERE MaTo = @MaTo_Cu
	IF @MaTo_Moi = @MaTo_Cu BEGIN
		IF @ChucVu_Cu = N'Quản Đốc' BEGIN
			IF @ChucVu_Moi = N'Tổ Trưởng' BEGIN
				IF (SELECT MaToTruong FROM ToSanXuat WHERE MaTo = @MaTo_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = NULL WHERE MaPhanXuong = @MaPhanXuong_Cu
					UPDATE ToSanXuat SET MaToTruong = @MaNhanVien_Moi WHERE MaTo = @MaTo_Moi
				END
			END
			IF @ChucVu_Moi = N'Công Nhân' BEGIN
				UPDATE PhanXuong SET MaQuanDoc = NULL WHERE MaPhanXuong = @MaPhanXuong_Cu
			END
		END
		ELSE IF @ChucVu_Cu = N'Tổ Trưởng' BEGIN
			IF @ChucVu_Moi = N'Quản Đốc' BEGIN
				IF (SELECT MaQuanDoc FROM PhanXuong WHERE MaPhanXuong = @MaPhanXuong_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = @MaNhanVien_Moi WHERE MaPhanXuong = @MaPhanXuong_Moi
					UPDATE ToSanXuat SET MaToTruong = NULL WHERE MaTo = @MaTo_Cu
				END
			END
			IF @ChucVu_Moi = N'Công Nhân' BEGIN
				UPDATE ToSanXuat SET MaToTruong = NULL WHERE MaTo = @MaTo_Cu
			END
		END
		ELSE BEGIN
			IF @ChucVu_Moi = N'Quản Đốc' BEGIN
				IF (SELECT MaQuanDoc FROM PhanXuong WHERE MaPhanXuong = @MaPhanXuong_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = @MaNhanVien_Moi WHERE MaPhanXuong = @MaPhanXuong_Moi
				END
			END
			IF @ChucVu_Moi = N'Tổ Trưởng' BEGIN
				IF (SELECT MaToTruong FROM ToSanXuat WHERE MaTo = @MaTo_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE ToSanXuat SET MaToTruong = @MaNhanVien_Moi WHERE MaTo = @MaTo_Moi
				END
			END
		END
	END
	ELSE BEGIN
		IF @ChucVu_Cu = N'Quản Đốc' BEGIN
			IF @ChucVu_Moi = N'Quản Đốc' BEGIN
				IF (SELECT MaQuanDoc FROM PhanXuong WHERE MaPhanXuong = @MaPhanXuong_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = NULL WHERE MaPhanXuong = @MaPhanXuong_Cu
					UPDATE PhanXuong SET MaQuanDoc = @MaNhanVien_Moi WHERE MaPhanXuong = @MaPhanXuong_Moi
				END
			END
			ELSE IF @ChucVu_Moi = N'Tổ Trưởng' BEGIN
				IF (SELECT MaToTruong FROM ToSanXuat WHERE MaTo = @MaTo_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = NULL WHERE MaPhanXuong = @MaPhanXuong_Cu
					UPDATE ToSanXuat SET MaToTruong = @MaNhanVien_Moi WHERE MaTo = @MaTo_Moi
				END
			END
			ELSE BEGIN
				UPDATE PhanXuong SET MaQuanDoc = NULL WHERE MaPhanXuong = @MaPhanXuong_Cu
			END
		END
		ELSE IF @ChucVu_Cu = N'Tổ Trưởng' BEGIN
			IF @ChucVu_Moi = N'Quản Đốc' BEGIN
				IF (SELECT MaQuanDoc FROM PhanXuong WHERE MaPhanXuong = @MaPhanXuong_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = @MaNhanVien_Moi WHERE MaPhanXuong = @MaPhanXuong_Moi
					UPDATE ToSanXuat SET MaToTruong = NULL WHERE MaTo = @MaTo_Cu
				END
			END
			ELSE IF @ChucVu_Moi = N'Tổ Trưởng' BEGIN
				IF (SELECT MaToTruong FROM ToSanXuat WHERE MaTo = @MaTo_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE ToSanXuat SET MaToTruong = NULL WHERE MaTo = @MaTo_Cu
					UPDATE ToSanXuat SET MaToTruong = @MaNhanVien_Moi WHERE MaTo = @MaTo_Moi
				END
			END
			ELSE BEGIN
				UPDATE ToSanXuat SET MaToTruong = NULL WHERE MaTo = @MaTo_Cu
			END
		END
		ELSE BEGIN
			IF @ChucVu_Moi = N'Quản Đốc' BEGIN
				IF (SELECT MaQuanDoc FROM PhanXuong WHERE MaPhanXuong = @MaPhanXuong_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE PhanXuong SET MaQuanDoc = @MaNhanVien_Moi WHERE MaPhanXuong = @MaPhanXuong_Moi
				END
			END
			IF @ChucVu_Moi = N'Tổ Trưởng' BEGIN
				IF (SELECT MaToTruong FROM ToSanXuat WHERE MaTo = @MaTo_Moi) IS NOT NULL BEGIN
					ROLLBACK
				END
				ELSE BEGIN
					UPDATE ToSanXuat SET MaToTruong = @MaNhanVien_Moi WHERE MaTo = @MaTo_Moi
				END
			END
		END
	END
END
GO
ALTER TABLE [dbo].[NhanVienSanXuat] ENABLE TRIGGER [TRIGGER_UPDATE_NVSX]
GO
USE [master]
GO
ALTER DATABASE [PayrollManagementSystem] SET  READ_WRITE 
GO
