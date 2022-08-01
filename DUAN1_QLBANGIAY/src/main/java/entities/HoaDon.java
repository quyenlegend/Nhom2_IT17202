/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Admin
 */
@Entity(name="HOADON")
public class HoaDon {
    @Id
    @Column(name="MaHD")
    private String MaHD;
    @Column(name="MaNV")
    private String MaNV;
    @Column(name="NgayTao")
    private Date NgayTao;
    @Column(name="TenKH")
    private String TenKH;
    @Column(name="TienKhachDua")
    private float TienKhachDua;
    @Column(name="TienThua")
    private float TienThua;
    @Column(name="TongTien")
    private float TongTien;
    @Column(name="TrangThai")
    private boolean TrangThai;
    
    @OneToMany(mappedBy="MaHdID")
    List<HoaDonChiTiet> hdct;
    
    
    
}
