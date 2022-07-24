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
@Entity(name = "HOADONCHITIET")
public class ChiTietHoaDonEntity implements Serializable {

    @Id
    @ManyToOne
    private HoaDonEntity HoaDonEntity;
    @Id
    @ManyToOne
    private SanPhamEntity SanPhamEntity;
    private String TenSP;
    private String Size;
    private String Mau;
    private String TenHang;
    private String TheLoai;
    private int SoLuong;
    private float Gia;

    public ChiTietHoaDonEntity() {
    }

    public ChiTietHoaDonEntity(HoaDonEntity HoaDonEntity, SanPhamEntity SanPhamEntity, String TenSP, String Size, String Mau, String TenHang, String TheLoai, int SoLuong, float Gia) {
        this.HoaDonEntity = HoaDonEntity;
        this.SanPhamEntity = SanPhamEntity;
        this.TenSP = TenSP;
        this.Size = Size;
        this.Mau = Mau;
        this.TenHang = TenHang;
        this.TheLoai = TheLoai;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
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

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }
    

}
