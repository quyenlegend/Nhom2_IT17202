/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Admin
 */
@Entity(name="HOADONCHITIET")
public class HoaDonChiTiet implements Serializable{
    @Id
    @Column(name="IDChiTiet")
    private int IDChiTiet;
    @ManyToOne
    @JoinColumn(name = "MaHD")
    private HoaDon MaHdID;
    
    
    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham maSpID;
    
    @Column(name="TenSP")
    private String TenSP;
    @Column(name="Size")
    private String Size;
    @Column(name="Mau")
    private String Mau;
    @Column(name="TenHang")
    private String TenHang;
    @Column(name="TheLoai")
    private String TheLoai;
    @Column(name="SoLuong")
    private int SoLuong;
    @Column(name="GiaTien")
    private float GiaTien;
    
    
    
}
