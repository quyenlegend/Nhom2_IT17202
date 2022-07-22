/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import DomainModels.HangEntity;
import DomainModels.MauSacEntity;
import DomainModels.SizeEntity;
import DomainModels.TheLoaiEntity;

/**
 *
 * @author boquy
 */
public class SanPhamModel {
    private String MaSP;
    private HangEntity HangEntity;
    private TheLoaiEntity TheLoaiEntity;
    private SizeEntity SizeEntity;
    private MauSacEntity MauSacEntity;
    private String TenSP;
    private String NgayNhap;
    private float Gia;
    private int SoLuong;
    private String Anh;
    private int TrangThai;

    public SanPhamModel() {
    }

    public SanPhamModel(String MaSP, HangEntity HangEntity, TheLoaiEntity TheLoaiEntity, SizeEntity SizeEntity, MauSacEntity MauSacEntity, String TenSP, String NgayNhap, float Gia, int SoLuong, String Anh, int TrangThai) {
        this.MaSP = MaSP;
        this.HangEntity = HangEntity;
        this.TheLoaiEntity = TheLoaiEntity;
        this.SizeEntity = SizeEntity;
        this.MauSacEntity = MauSacEntity;
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

    public HangEntity getHangEntity() {
        return HangEntity;
    }

    public void setHangEntity(HangEntity HangEntity) {
        this.HangEntity = HangEntity;
    }

    public TheLoaiEntity getTheLoaiEntity() {
        return TheLoaiEntity;
    }

    public void setTheLoaiEntity(TheLoaiEntity TheLoaiEntity) {
        this.TheLoaiEntity = TheLoaiEntity;
    }

    public SizeEntity getSizeEntity() {
        return SizeEntity;
    }

    public void setSizeEntity(SizeEntity SizeEntity) {
        this.SizeEntity = SizeEntity;
    }

    public MauSacEntity getMauSacEntity() {
        return MauSacEntity;
    }

    public void setMauSacEntity(MauSacEntity MauSacEntity) {
        this.MauSacEntity = MauSacEntity;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

  
    
   
    
    
}
