/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity(name="HANGSX")
public class HangSX implements Serializable{
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="MaHangSX")
	private int MaHangSX;
	@Column(name="TenHang")
	private String TenHang;
	
	@OneToMany(mappedBy="hangSXId")
	private List<SanPham> sanpham;

    public HangSX() {
    }

        
    public HangSX(int MaHangSX, String TenHang) {
        this.MaHangSX = MaHangSX;
        this.TenHang = TenHang;
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

    public List<SanPham> getSanpham() {
        return sanpham;
    }

    public void setSanpham(List<SanPham> sanpham) {
        this.sanpham = sanpham;
    }

    @Override
    public String toString() {
        return  TenHang ;
    }
        
        
}
