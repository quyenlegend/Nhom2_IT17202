/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author boquy
 */
@Entity(name="HOADONCHITIET") 
public class ChiTietHoaDonEntity implements Serializable {
    @Id
    @ManyToOne
    private HoaDonEntity  HoaDonEntity;
    @Id
    @ManyToOne
    private SanPhamEntity SanPhamEntity;
    
    private float Gia;
    private float SoLuong;
    private float TienKhach;
    private float TienMua;
    private float ThanhTien;
    private int TrangThai;

    public ChiTietHoaDonEntity() {
    }
    
    public ChiTietHoaDonEntity(HoaDonEntity HoaDonEntity, SanPhamEntity SanPhamEntity, float Gia, float SoLuong, float TienKhach, float TienMua, float ThanhTien, int TrangThai) {
        this.HoaDonEntity = HoaDonEntity;
        this.SanPhamEntity = SanPhamEntity;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.TienKhach = TienKhach;
        this.TienMua = TienMua;
        this.ThanhTien = ThanhTien;
        this.TrangThai = TrangThai;
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
