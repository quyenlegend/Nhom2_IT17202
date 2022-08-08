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
@Entity(name = "MAUSAC")
public class MauSacEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaMS;
    private String TenMau;
    @OneToMany(mappedBy = "MauSacEntity")
    List<SanPhamEntity> _listMausac;

    public MauSacEntity() {
    }

    public MauSacEntity(int MaMS, String TenMau) {
        this.MaMS = MaMS;
        this.TenMau = TenMau;
    }

    public MauSacEntity(int MaMS, String TenMau, List<SanPhamEntity> _listMausac) {
        this.MaMS = MaMS;
        this.TenMau = TenMau;
        this._listMausac = _listMausac;
    }

    public int getMaMS() {
        return MaMS;
    }

    public void setMaMS(int MaMS) {
        this.MaMS = MaMS;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }

    public List<SanPhamEntity> getListMausac() {
        return _listMausac;
    }

    public void setListMausac(List<SanPhamEntity> _listMausac) {
        this._listMausac = _listMausac;
    }

    @Override
    public String toString() {
        return TenMau;
    }

}
