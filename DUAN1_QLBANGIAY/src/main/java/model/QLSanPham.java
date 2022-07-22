/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entities.HangSX;
import entities.MauSac;
import entities.Size;
import entities.TheLoai;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class QLSanPham {
    private String MaSP;
	private HangSX hangSXId;
	private TheLoai TheLoaiID;
	private Size maSizeId;
	private MauSac mauSacId;
	private String TenSP;
	private Date NgayNhap;
	private float Gia;
	private int SoLuong;
	private String Anh;
	private boolean TrangThai;

    public QLSanPham() {
    }

//    public QLSanPham(String maSP, HangSX hangSXId, TheLoai TheLoaiID, Size maSizeId, MauSac mauSacId, String TenSP, Date NgayNhap, float Gia, int SoLuong, String Anh, boolean TrangThai) {
//        this.maSP = maSP;
//        this.hangSXId = hangSXId;
//        this.TheLoaiID = TheLoaiID;
//        this.maSizeId = maSizeId;
//        this.mauSacId = mauSacId;
//        this.TenSP = TenSP;
//        this.NgayNhap = NgayNhap;
//        this.Gia = Gia;
//        this.SoLuong = SoLuong;
//        this.Anh = Anh;
//        this.TrangThai = TrangThai;
//    }
//public QLSanPham(String MaSP, HangSX hangSXId, TheLoai TheLoaiID, Size maSizeId, MauSac mauSacId, String TenSP, Date NgayNhap, float Gia, int SoLuong, String Anh, boolean TrangThai) {
//        this.MaSP = MaSP;
//        this.hangSXId = hangSXId;
//        this.TheLoaiID = TheLoaiID;
//        this.maSizeId = maSizeId;
//        this.mauSacId = mauSacId;
//        this.TenSP = TenSP;
//        this.NgayNhap = NgayNhap;
//        this.Gia = Gia;
//        this.SoLuong = SoLuong;
//        this.Anh = Anh;
//        this.TrangThai = TrangThai;
//    }

    public QLSanPham(String MaSP, HangSX hangSXId, TheLoai TheLoaiID, Size maSizeId, MauSac mauSacId, String TenSP, Date NgayNhap, float Gia, int SoLuong, String Anh, boolean TrangThai) {
        this.MaSP = MaSP;
        this.hangSXId = hangSXId;
        this.TheLoaiID = TheLoaiID;
        this.maSizeId = maSizeId;
        this.mauSacId = mauSacId;
        this.TenSP = TenSP;
        this.NgayNhap = NgayNhap;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.Anh = Anh;
        this.TrangThai = TrangThai;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public HangSX getHangSXId() {
        return hangSXId;
    }

    public void setHangSXId(HangSX hangSXId) {
        this.hangSXId = hangSXId;
    }

    public TheLoai getTheLoaiID() {
        return TheLoaiID;
    }

    public void setTheLoaiID(TheLoai TheLoaiID) {
        this.TheLoaiID = TheLoaiID;
    }

    public Size getMaSizeId() {
        return maSizeId;
    }

    public void setMaSizeId(Size maSizeId) {
        this.maSizeId = maSizeId;
    }

    public MauSac getMauSacId() {
        return mauSacId;
    }

    public void setMauSacId(MauSac mauSacId) {
        this.mauSacId = mauSacId;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    


        
        
}
