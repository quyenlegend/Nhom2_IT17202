/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import DomainModels.HoaDonEntity;
import java.util.Date;

/**
 *
 * @author boquy
 */
public class DoiTraHangModel {
    private int MaDTH;
    private HoaDonEntity HoaDonEntity;
    private String TenKH;
    private Date NgayDTH;
    private float TongTienDoiTra;
    private String MaNV;

    public DoiTraHangModel() {
    }

    public DoiTraHangModel(int MaDTH, HoaDonEntity HoaDonEntity, String TenKH, Date NgayDTH, float TongTienDoiTra, String MaNV) {
        this.MaDTH = MaDTH;
        this.HoaDonEntity = HoaDonEntity;
        this.TenKH = TenKH;
        this.NgayDTH = NgayDTH;
        this.TongTienDoiTra = TongTienDoiTra;
        this.MaNV = MaNV;
    }

    public int getMaDTH() {
        return MaDTH;
    }

    public void setMaDTH(int MaDTH) {
        this.MaDTH = MaDTH;
    }

    public HoaDonEntity getHoaDonEntity() {
        return HoaDonEntity;
    }

    public void setHoaDonEntity(HoaDonEntity HoaDonEntity) {
        this.HoaDonEntity = HoaDonEntity;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public Date getNgayDTH() {
        return NgayDTH;
    }

    public void setNgayDTH(Date NgayDTH) {
        this.NgayDTH = NgayDTH;
    }

    public float getTongTienDoiTra() {
        return TongTienDoiTra;
    }

    public void setTongTienDoiTra(float TongTienDoiTra) {
        this.TongTienDoiTra = TongTienDoiTra;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    
    
}
