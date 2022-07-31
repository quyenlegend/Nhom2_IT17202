/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// OneToMany : Chi Tiết Trả Hàng
// ManyToOne : Hóa Đơn  | Sản Phâm
@Entity(name = "HOADONCHITIET")
public class ChiTietHoaDonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDChiTiet;

    @ManyToOne
    @JoinColumn(name = "MaHD")
    private HoaDonEntity HoaDonEntity;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPhamEntity SanPhamEntity;
    private String TenSP;
    private String Size;
    private String Mau;
    private String TenHang;
    private String TheLoai;
    private int SoLuong;
    private float GiaTien;
    @OneToMany(mappedBy = "ChiTietHoaDonEntity")
    private List<ChiTietTraHangEntity> _ListHDCT_TraH = new ArrayList<ChiTietTraHangEntity>();

    public ChiTietHoaDonEntity() {
    }

    public ChiTietHoaDonEntity(int IDChiTiet) {
        this.IDChiTiet = IDChiTiet;
    }

    public ChiTietHoaDonEntity(int IDChiTiet, HoaDonEntity HoaDonEntity, SanPhamEntity SanPhamEntity, String TenSP, String Size, String Mau, String TenHang, String TheLoai, int SoLuong, float GiaTien) {
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

    public List<ChiTietTraHangEntity> getListHDCT_TraH() {
        return _ListHDCT_TraH;
    }

    public void setListHDCT_TraH(List<ChiTietTraHangEntity> _ListHDCT_TraH) {
        this._ListHDCT_TraH = _ListHDCT_TraH;
    }

}
