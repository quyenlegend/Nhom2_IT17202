/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author boquy
 */
public class ChiTietHoaDonModel {

    private String MaHD;
    private String MaSP;
    private float Gia;
    private float SoLuong;
    private float TienKhach;
    private float TienMua;
    private float ThanhTien;
    private int TrangThai;

    public ChiTietHoaDonModel() {
    }

    public ChiTietHoaDonModel(String MaHD, String MaSP, float Gia, float SoLuong, float TienKhach, float TienMua, float ThanhTien, int TrangThai) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.TienKhach = TienKhach;
        this.TienMua = TienMua;
        this.ThanhTien = ThanhTien;
        this.TrangThai = TrangThai;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public float getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(float SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getTienKhach() {
        return TienKhach;
    }

    public void setTienKhach(float TienKhach) {
        this.TienKhach = TienKhach;
    }

    public float getTienMua() {
        return TienMua;
    }

    public void setTienMua(float TienMua) {
        this.TienMua = TienMua;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
