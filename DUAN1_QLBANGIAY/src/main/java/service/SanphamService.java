/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.SanPham;
import java.util.ArrayList;
import java.util.List;
import model.QLSanPham;
import responsitories.ISanPhamRepository;
import responsitories.SanPhamRepository;

/**
 *
 * @author Admin
 */
public class SanphamService implements ISanphamService{
    private final ISanPhamRepository spRepository;
    private List<QLSanPham> lstSP;
    
    public SanphamService() {
    spRepository = new SanPhamRepository();
    lstSP = new ArrayList<>();
  }
    @Override
    public List<QLSanPham> getProducts() {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAll();
        for(SanPham sp: sanpham){
            lstSP.add(new QLSanPham(sp.getMaSP(),sp.getHangSXId(),sp.getTheLoaiID(),sp.getMaSizeId(),
                    sp.getMauSacId(),sp.getTenSP(),sp.getNgayNhap(),sp.getGia(),sp.getSoLuong(),sp.getAnh(),sp.isTrangThai()
            ));
        }
        return lstSP;
        
    }

    @Override
    public List<QLSanPham> getProductById(String id) {
        var x = spRepository.findById(id);
        List<QLSanPham> listById = new ArrayList<>();
        listById.add( new QLSanPham(x.getMaSP(), x.getHangSXId(), x.getTheLoaiID(), x.getMaSizeId()
            ,x.getMauSacId(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.isTrangThai()
            ));
        return listById;
    }

    @Override
    public QLSanPham createNewProduct(QLSanPham product) {
        //product.setMaSP(null);
    var x = spRepository.save(new SanPham(product.getMaSP(), product.getHangSXId(), product.getTheLoaiID(), 
            product.getMaSizeId(),product.getMauSacId(),product.getTenSP(),product.getNgayNhap(),product.getGia(),
            product.getSoLuong(),product.getAnh(),product.isTrangThai()
    ));
    return new QLSanPham(x.getMaSP(), x.getHangSXId(), x.getTheLoaiID(), x.getMaSizeId(),
                x.getMauSacId(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.isTrangThai()
        );
    }

    @Override
    public QLSanPham updateProductById(QLSanPham product) {
        var x = spRepository.save(new SanPham(product.getMaSP(), product.getHangSXId(), product.getTheLoaiID(), 
            product.getMaSizeId(),product.getMauSacId(),product.getTenSP(),product.getNgayNhap(),product.getGia(),
            product.getSoLuong(),product.getAnh(),product.isTrangThai()
    ));
    return new QLSanPham(x.getMaSP(), x.getHangSXId(), x.getTheLoaiID(), x.getMaSizeId(),
                x.getMauSacId(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.isTrangThai()
        );
    }

    @Override
    public QLSanPham getProductByMaMS(int MaMS) {
        var x = spRepository.findByMS(MaMS);
    return new QLSanPham(x.getMaSP(), x.getHangSXId(), x.getTheLoaiID(), x.getMaSizeId()
            ,x.getMauSacId(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.isTrangThai()
            );
    }

    @Override
    public List<QLSanPham> getProductsActive() {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAllActive();
        for(SanPham sp: sanpham){
            lstSP.add(new QLSanPham(sp.getMaSP(),sp.getHangSXId(),sp.getTheLoaiID(),sp.getMaSizeId(),
                    sp.getMauSacId(),sp.getTenSP(),sp.getNgayNhap(),sp.getGia(),sp.getSoLuong(),sp.getAnh(),sp.isTrangThai()
            ));
        }
        return lstSP;
    }

    @Override
    public List<QLSanPham> getProductsinActive() {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAllInActive();
        for(SanPham sp: sanpham){
            lstSP.add(new QLSanPham(sp.getMaSP(),sp.getHangSXId(),sp.getTheLoaiID(),sp.getMaSizeId(),
                    sp.getMauSacId(),sp.getTenSP(),sp.getNgayNhap(),sp.getGia(),sp.getSoLuong(),sp.getAnh(),sp.isTrangThai()
            ));
        }
        return lstSP;
    }

    

    
    
}
