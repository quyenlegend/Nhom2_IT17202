/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DAO.AllDAO;
import DAO.KhachHangDAO;
import Entities.KhachHang;
import Models.KhachHangModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOANGGOANG
 */
public class QLKhachHang implements QLALL{
    private final AllDAO _allDAO;
    private List<KhachHangModel> _lstKHModel;
    public QLKhachHang(){
        _allDAO = new KhachHangDAO();
        _lstKHModel = new ArrayList<>();
    }
    @Override
    public List<KhachHang> getKhachHang() {
        return _allDAO.findAll();
    }

    @Override
    public KhachHangModel getKhachHangById(String maKH) {
        var x = _allDAO.findById(maKH);
        return new KhachHangModel(x.getMaKH(),x.getTenKH(),x.getSDT(),x.isGioiTinh(),x.getDiaChi(),x.getNgayThamGia());
    }

    @Override
    public String createKH(KhachHangModel khModel) {
        
        return _allDAO.insert(new KhachHang(khModel.getMaKH(),khModel.getTenKH(),khModel.getSDT(),khModel.isGioiTinh(),khModel.getDiaChi(),khModel.getNgayThamGia()));
    }

    @Override
    public String updateKH(KhachHangModel khModel) {
        
        return _allDAO.update(new KhachHang(khModel.getMaKH(),khModel.getTenKH(),khModel.getSDT(),khModel.isGioiTinh(),khModel.getDiaChi(),khModel.getNgayThamGia()));
    }

    @Override
    public List<KhachHang> findBySdt(String sdt) {
        return _allDAO.findBySdt(sdt);
    }
}
