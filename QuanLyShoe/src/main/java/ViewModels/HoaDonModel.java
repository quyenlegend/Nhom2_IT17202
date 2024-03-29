/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author boquy
 */
public class HoaDonModel {

    private  String MaHD;
    private Date NgayTao;
    private String MaNV;
    private String TenKH;
    private float TienKhachDua;
    private float TienThua;
     private float  TongTien;   
    private int TrangThai;

    public HoaDonModel() {
    }

    public HoaDonModel(String MaHD, Date NgayTao, String MaNV, String TenKH, float TienKhachDua, float TienThua, float TongTien, int TrangThai) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.MaNV = MaNV;
        this.TenKH = TenKH;
        this.TienKhachDua = TienKhachDua;
        this.TienThua = TienThua;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public float getTienKhachDua() {
        return TienKhachDua;
    }

    public void setTienKhachDua(float TienKhachDua) {
        this.TienKhachDua = TienKhachDua;
    }

    public float getTienThua() {
        return TienThua;
    }

    public void setTienThua(float TienThua) {
        this.TienThua = TienThua;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

   
  
    
}
