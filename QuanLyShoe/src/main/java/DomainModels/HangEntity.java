/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author boquy
 */
@Entity(name = "HANGSX")
public class HangEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaHangSX;
    private String TenHang;
    @OneToMany(mappedBy = "HangEntity")
    List<SanPhamEntity> _listHangsp;

    public HangEntity() {
    }

    public HangEntity(int MaHangSX, String TenHang) {
        this.MaHangSX = MaHangSX;
        this.TenHang = TenHang;
    }

    public HangEntity(int MaHangSX, String TenHang, List<SanPhamEntity> _listHangsp) {
        this.MaHangSX = MaHangSX;
        this.TenHang = TenHang;
        this._listHangsp = _listHangsp;
    }

    public int getMaHangSX() {
        return MaHangSX;
    }

    public void setMaHangSX(int MaHangSX) {
        this.MaHangSX = MaHangSX;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public List<SanPhamEntity> getListHangsp() {
        return _listHangsp;
    }

    public void setListHangsp(List<SanPhamEntity> _listHangsp) {
        this._listHangsp = _listHangsp;
    }

    @Override
    public String toString() {
        return TenHang;
    }

}
