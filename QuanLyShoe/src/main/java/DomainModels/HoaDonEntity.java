/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author boquy
 */
@Entity(name="HOADON")
public class HoaDonEntity implements Serializable {
    @Id
    private  String MaHD;
    private Date NgayTao;
    private String MaNV;
    private String MaKH;
    private int TrangThai;
    @OneToMany(mappedBy = "HoaDonEntity")
    private List<ChiTietHoaDonEntity>_listCtHd= new ArrayList<ChiTietHoaDonEntity>();

    public HoaDonEntity() {
    }

    public HoaDonEntity(String MaHD, Date NgayTao, String MaNV, String MaKH, int TrangThai) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
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

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public List<ChiTietHoaDonEntity> getListCtHd() {
        return _listCtHd;
    }

    public void setListCtHd(List<ChiTietHoaDonEntity> _listCtHd) {
        this._listCtHd = _listCtHd;
    }

    
}
