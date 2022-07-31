/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import DomainModels.HoaDonEntity;
import DomainModels.SanPhamEntity;

/**
 *
 * @author boquy
 */
public class ChiTietHoaDonModel {
    private int IDChiTiet;
    private HoaDonEntity HoaDonEntity;
    private SanPhamEntity SanPhamEntity;
    private String TenSP;
    private String Size;
    private String Mau;
    private String TenHang;
    private String TheLoai;
    private int SoLuong;
    private float GiaTien;

    public ChiTietHoaDonModel() {
    }

    public ChiTietHoaDonModel(int IDChiTiet) {
        this.IDChiTiet = IDChiTiet;
    }

    public ChiTietHoaDonModel(int IDChiTiet, HoaDonEntity HoaDonEntity, SanPhamEntity SanPhamEntity, String TenSP, String Size, String Mau, String TenHang, String TheLoai, int SoLuong, float GiaTien) {
        this.IDChiTiet = IDChiTiet;
        this.HoaDonEntity = HoaDonEntity;
        this.SanPhamEntity = SanPhamEntity;
        this.TenSP = TenSP;
        this.Size = Size;
        this.Mau = Mau;
        this.TenHang = TenHang;
        this.TheLoai = TheLoai;
        this.SoLuong = SoLuong;
        this.GiaTien = GiaTien;
    }

    public int getIDChiTiet() {
        return IDChiTiet;
    }

    public void setIDChiTiet(int IDChiTiet) {
        this.IDChiTiet = IDChiTiet;
    }

    public HoaDonEntity getHoaDonEntity() {
        return HoaDonEntity;
    }

    public void setHoaDonEntity(HoaDonEntity HoaDonEntity) {
        this.HoaDonEntity = HoaDonEntity;
    }

    public SanPhamEntity getSanPhamEntity() {
        return SanPhamEntity;
    }

    public void setSanPhamEntity(SanPhamEntity SanPhamEntity) {
        this.SanPhamEntity = SanPhamEntity;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getMau() {
        return Mau;
    }

    public void setMau(String Mau) {
        this.Mau = Mau;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(float GiaTien) {
        this.GiaTien = GiaTien;
    }

 

  
  

}
