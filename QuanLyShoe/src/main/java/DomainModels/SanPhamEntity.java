/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author boquy
 */
@Entity(name = "SANPHAM")
public class SanPhamEntity implements Serializable {

    @Id
    private String MaSP;
    @ManyToOne
    @JoinColumn(name = "MaHangSX")
    private HangEntity HangEntity;
    @ManyToOne
    @JoinColumn(name = "MaTL")
    private TheLoaiEntity TheLoaiEntity;
    @ManyToOne
    @JoinColumn(name = "MaSize")
    private SizeEntity SizeEntity;
    @ManyToOne                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
    @JoinColumn(name = "MaMS")
    private MauSacEntity MauSacEntity;
    private String TenSP;
    private Date NgayNhap;
    private float Gia;
    private int SoLuong;
    private String Anh;
    private int TrangThai;
//    @OneToMany(mappedBy = "SanPhamEntity")
//    private List<ChiTietHoaDonEntity> _listCtSp = new ArrayList<ChiTietHoaDonEntity>();

    public SanPhamEntity() {
    }

    public SanPhamEntity(String MaSP) {
        this.MaSP = MaSP;
    }
    

    public SanPhamEntity(String MaSP, HangEntity HangEntity, TheLoaiEntity TheLoaiEntity, SizeEntity SizeEntity, MauSacEntity MauSacEntity, String TenSP, Date NgayNhap, float Gia, int SoLuong, String Anh, int TrangThai) {
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

//    public List<ChiTietHoaDonEntity> getListCtSp() {
//        return _listCtSp;
//    }
//
//    public void setListCtSp(List<ChiTietHoaDonEntity> _listCtSp) {
//        this._listCtSp = _listCtSp;
//    }



}
