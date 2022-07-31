/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author HOANGGOANG
 */
@Entity (name = "KHACHHANG")
public class KhachHang implements Serializable{
    @Id
    private String MaKH;
    
    @Column
    @Nationalized
    private String TenKH;
    
    @Column
    private String SDT;
    
    @Column
    private boolean GioiTinh;
    
    @Column
    private String DiaChi;
    
    @Column
    private Date NgayThamGia;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String TenKH, String SDT, boolean GioiTinh, String DiaChi, Date NgayThamGia) {
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
