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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author boquy
 */
@Entity(name="THELOAI")
public class TheLoaiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTL;
    private String TenLoai;
    @OneToMany(mappedBy = "TheLoaiEntity")
    private List<SanPhamEntity> _listtheLoaiSP;

    public TheLoaiEntity() {
    }

    public TheLoaiEntity(int MaTL, String TenLoai, List<SanPhamEntity> _listtheLoaiSP) {
        this.MaTL = MaTL;
        this.TenLoai = TenLoai;
        this._listtheLoaiSP = _listtheLoaiSP;
    }

    public int getMaTL() {
        return MaTL;
    }

    public void setMaTL(int MaTL) {
        this.MaTL = MaTL;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public List<SanPhamEntity> getListtheLoaiSP() {
        return _listtheLoaiSP;
    }

    public void setListtheLoaiSP(List<SanPhamEntity> _listtheLoaiSP) {
        this._listtheLoaiSP = _listtheLoaiSP;
    }
    
    
    
}
