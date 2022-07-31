/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author HOANGGOANG
 */
public class KhachHangModel {
    private String MaKH;
    private String TenKH;
    private String SDT;
    private boolean GioiTinh;
    private String DiaChi;
    private Date NgayThamGia;

    public KhachHangModel() {
    }

    public KhachHangModel(String MaKH, String TenKH, String SDT, boolean GioiTinh, String DiaChi, Date NgayThamGia) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.NgayThamGia = NgayThamGia;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Date getNgayThamGia() {
        return NgayThamGia;
    }

    public void setNgayThamGia(Date NgayThamGia) {
        this.NgayThamGia = NgayThamGia;
    }

    
}
