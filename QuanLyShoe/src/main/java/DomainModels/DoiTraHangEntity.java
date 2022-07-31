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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// onetomany : ChiTietTra    |
//Manytoone  : Hóa đơn
@Entity(name = "DOITRAHANG")
public class DoiTraHangEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaDTH;
    @ManyToOne
    @JoinColumn(name = "MaHD")
    private HoaDonEntity HoaDonEntity;
    private String TenKH;
    private Date NgayDTH;
    private Float TongTienDoiTra;
    private String MaNV;
    @OneToMany(mappedBy = "DoiTraHangEntity")
    private List<ChiTietTraHangEntity> _listCTTraHang = new ArrayList<ChiTietTraHangEntity>();

    public DoiTraHangEntity() {
    }

    public DoiTraHangEntity(int MaDTH) {
        this.MaDTH = MaDTH;
    }

    public DoiTraHangEntity(int MaDTH, HoaDonEntity HoaDonEntity, String TenKH, Date NgayDTH, Float TongTienDoiTra, String MaNV) {
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

    public Float getTongTienDoiTra() {
        return TongTienDoiTra;
    }

    public void setTongTienDoiTra(Float TongTienDoiTra) {
        this.TongTienDoiTra = TongTienDoiTra;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public List<ChiTietTraHangEntity> getListCTTraHang() {
        return _listCTTraHang;
    }

    public void setListCTTraHang(List<ChiTietTraHangEntity> _listCTTraHang) {
        this._listCTTraHang = _listCTTraHang;
    }

}
