USE [PayrollManagementSystem]
GO
/****** Object:  Table [dbo].[ChamCongHanhChinh]    Script Date: 11/20/2022 7:10:43 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVienHanhChinh]    Script Date: 11/20/2022 7:10:43 PM ******/
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
	[TenNganHang] [char](10) NULL,
	[SoTaiKhoan] [char](20) NULL,
	[TenNguoiThuHuong] [nchar](50) NULL,
	[LuongTheoChucDanh] [float] NOT NULL,
	[ChucVu] [nvarchar](50) NOT NULL,
	[MaPhongBan] [char](4) NOT NULL,
 CONSTRAINT [PK_NhanVienHanhChinh] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[VIEW_CHAMCONGHANHCHINH]    Script Date: 11/20/2022 7:10:43 PM ******/
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
/****** Object:  Table [dbo].[ChamCongSanXuat]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChamCongSanXuat](
	[MaChamCong] [char](10) NOT NULL,
	[NgayChamCong] [date] NOT NULL,
	[SoLuongThanhPham] [int] NOT NULL,
	[MaPhanCong] [char](10) NULL,
 CONSTRAINT [PK_ChamCongSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaChamCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietSanXuat]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietSanXuat](
	[MaCTSX] [char](7) NOT NULL,
	[SoLuongSanXuat] [int] NULL,
	[SoLuongHoanThanh] [int] NULL,
	[TinhTrang] [nvarchar](50) NULL,
	[MaSanPham] [char](5) NOT NULL,
	[ThoiGian] [date] NULL,
	[ThuTu] [int] NULL,
 CONSTRAINT [PK_ChiTietSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaCTSX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhenThuongKyLuat]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhenThuongKyLuat](
	[MaKT_KL] [char](10) NOT NULL,
	[LyDo] [nvarchar](100) NOT NULL,
	[NgayApDung] [date] NOT NULL,
	[SoTien] [float] NOT NULL,
	[MaNVSX] [char](9) NULL,
	[MaNVHC] [char](9) NULL,
 CONSTRAINT [PK_KhenThuongKyLuat] PRIMARY KEY CLUSTERED 
(
	[MaKT_KL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NghiPhep]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NghiPhep](
	[MaNghiPhep] [nchar](10) NOT NULL,
	[NgayNghi] [date] NOT NULL,
	[NghiDen] [date] NOT NULL,
	[LyDo] [nvarchar](100) NOT NULL,
	[HuongLuong] [float] NOT NULL,
	[MaNhanVien] [char](9) NOT NULL,
 CONSTRAINT [PK_NghiPhep] PRIMARY KEY CLUSTERED 
(
	[MaNghiPhep] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVienSanXuat]    Script Date: 11/20/2022 7:10:43 PM ******/
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
	[TenNganHang] [char](10) NULL,
	[SoTaiKhoan] [char](20) NULL,
	[TenNguoiThuHuong] [nchar](50) NULL,
	[ChuyenMon] [nvarchar](50) NOT NULL,
	[MaTo] [char](6) NOT NULL,
 CONSTRAINT [PK_NhanVienSanXuat] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhanCong]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanCong](
	[MaPhanCong] [char](10) NOT NULL,
	[MaQuyTrinh] [char](7) NULL,
	[MaNhanVien] [char](9) NOT NULL,
	[NgayThamGia] [date] NOT NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[MaPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhanXuong]    Script Date: 11/20/2022 7:10:43 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongBan]    Script Date: 11/20/2022 7:10:43 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuyTrinh]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuyTrinh](
	[MaQuyTrinh] [char](7) NOT NULL,
	[TenQuyTrinh] [nvarchar](50) NOT NULL,
	[GiaQuyTrinh] [float] NOT NULL,
	[MaSanPham] [char](5) NULL,
	[ThuTuSanXuat] [int] NULL,
 CONSTRAINT [PK_QuyTrinh] PRIMARY KEY CLUSTERED 
(
	[MaQuyTrinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSanPham] [char](5) NOT NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
	[SoLuongSanXuat] [int] NOT NULL,
 CONSTRAINT [PK_SanPham] PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/20/2022 7:10:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TenDangNhap] [char](9) NOT NULL,
	[MatKhau] [char](44) NOT NULL,
	[GiaTriSalt] [char](30) NULL,
	[MaNVSX] [char](9) NULL,
	[MaNVHC] [char](9) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[TenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ToSanXuat]    Script Date: 11/20/2022 7:10:43 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
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
ALTER TABLE [dbo].[ChiTietSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietSanXuat_SanPham] FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[SanPham] ([MaSanPham])
GO
ALTER TABLE [dbo].[ChiTietSanXuat] CHECK CONSTRAINT [FK_ChiTietSanXuat_SanPham]
GO
ALTER TABLE [dbo].[KhenThuongKyLuat]  WITH CHECK ADD  CONSTRAINT [FK_KhenThuongKyLuat_NhanVienHanhChinh] FOREIGN KEY([MaNVHC])
REFERENCES [dbo].[NhanVienHanhChinh] ([MaNhanVien])
GO
ALTER TABLE [dbo].[KhenThuongKyLuat] CHECK CONSTRAINT [FK_KhenThuongKyLuat_NhanVienHanhChinh]
GO
ALTER TABLE [dbo].[KhenThuongKyLuat]  WITH CHECK ADD  CONSTRAINT [FK_KhenThuongKyLuat_NhanVienSanXuat] FOREIGN KEY([MaNVSX])
REFERENCES [dbo].[NhanVienSanXuat] ([MaNhanVien])
GO
ALTER TABLE [dbo].[KhenThuongKyLuat] CHECK CONSTRAINT [FK_KhenThuongKyLuat_NhanVienSanXuat]
GO
ALTER TABLE [dbo].[NghiPhep]  WITH CHECK ADD  CONSTRAINT [FK_NghiPhep_NhanVienHanhChinh] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVienHanhChinh] ([MaNhanVien])
GO
ALTER TABLE [dbo].[NghiPhep] CHECK CONSTRAINT [FK_NghiPhep_NhanVienHanhChinh]
GO
ALTER TABLE [dbo].[NhanVienSanXuat]  WITH CHECK ADD  CONSTRAINT [FK_NhanVienSanXuat_ToSanXuat] FOREIGN KEY([MaTo])
REFERENCES [dbo].[ToSanXuat] ([MaTo])
GO
ALTER TABLE [dbo].[NhanVienSanXuat] CHECK CONSTRAINT [FK_NhanVienSanXuat_ToSanXuat]
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
