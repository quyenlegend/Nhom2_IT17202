/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity(name = "SANPHAM")
public class SanPham implements Serializable{
    private static final long serialVersionUID = 1L;
	
	@Id
        @Column(name="MaSP")
	private String MaSP;
	
	//hãng
	@ManyToOne()
	@JoinColumn(name="MaHangSX")
	private HangSX hangSXId;
	
	@ManyToOne()
	@JoinColumn(name="MaTL")
	private TheLoai TheLoaiID;//đổi int -->Category
	
	//Size
	@ManyToOne()
	@JoinColumn(name="MaSize")
	private Size maSizeId;
	
	//mapping màu sắc
	@ManyToOne()
	@JoinColumn(name="MaMS")
	private MauSac mauSacId;
	
	@Column(name="TenSP")
	private String TenSP;
	
	@Column(name="NgayNhap")
	private Date NgayNhap;
	
	@Column(name="Gia")
	private float Gia;
	
	@Column(name="SoLuong")
	private int SoLuong;
	
	@Column(name="Anh")
	private String Anh;
	
	@Column(name="TrangThai")
	private boolean TrangThai;

    public SanPham() {
    }
        
        

    public SanPham(String MaSP, HangSX hangSXId, TheLoai TheLoaiID, Size maSizeId, MauSac mauSacId, String TenSP, Date NgayNhap, float Gia, int SoLuong, String Anh, boolean TrangThai) {
        this.MaSP = MaSP;
        this.hangSXId = hangSXId;
        this.TheLoaiID = TheLoaiID;
        this.maSizeId = maSizeId;
        this.mauSacId = mauSacId;
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

    public HangSX getHangSXId() {
        return hangSXId;
    }

    public void setHangSXId(HangSX hangSXId) {
        this.hangSXId = hangSXId;
    }

    public TheLoai getTheLoaiID() {
        return TheLoaiID;
    }

    public void setTheLoaiID(TheLoai TheLoaiID) {
        this.TheLoaiID = TheLoaiID;
    }

    public Size getMaSizeId() {
        return maSizeId;
    }

    public void setMaSizeId(Size maSizeId) {
        this.maSizeId = maSizeId;
    }

    public MauSac getMauSacId() {
        return mauSacId;
    }

    public void setMauSacId(MauSac mauSacId) {
        this.mauSacId = mauSacId;
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

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
        
        
}
