/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.NhanVien;
import Repositories.NhanVien_Mapped;
import ViewModels.NhanVien_Model;
import java.util.List;

/**
 *
 * @author s2ngo
 */
public class NhanVienServices {

    NhanVien_Mapped nhanVien_Mapped;

    public NhanVienServices() {
        nhanVien_Mapped = new NhanVien_Mapped();
    }

    public List getList() {
        return nhanVien_Mapped.getList();
    }

    public String add(NhanVien_Model nv) {
        return nhanVien_Mapped.add(new NhanVien(nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(),
                nv.getSDT(), nv.getChucVu(), nv.getMatKhau(), nv.getEmail(), nv.getDiaChi(), nv.getTrangThai()));
    }

    public String edit(NhanVien_Model nv) {
        return nhanVien_Mapped.edit(new NhanVien(nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(),
                nv.getSDT(), nv.getChucVu(), nv.getMatKhau(), nv.getEmail(), nv.getDiaChi(), nv.getTrangThai()));
    }

//    public String timKiemTheoTen(NhanVien_Model nv) {
//        return nhanVien_Mapped.findByTen(new NhanVien(MaNV, TenNV, 0, SDT, 0, MatKhau, Email, DiaChi, 0));
//    }
}
