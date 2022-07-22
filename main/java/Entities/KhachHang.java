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
import javax.persistence.Table;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author HOANGGOANG
 */
@Entity
@Table (name = "khachhang")
public class KhachHang implements Serializable{
    @Id
    private String maKH;
    
    @Column
    @Nationalized
    private String tenKH;
    
    @Column
    private String SDT;
    
    @Column
    private boolean gioiTinh;
    
    @Column
    private String diaChi;
    
    @Column
    private Date ngayThamGia;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String SDT, boolean gioiTinh, String diaChi, Date ngayThamGia) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngayThamGia = ngayThamGia;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }
    
    
    
}
