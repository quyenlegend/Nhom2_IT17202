/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.SanPhamEntity;
import Repositories.ISanPhamRepository;
import Repositories.ProductsRepository;
import ViewModels.SanPhamModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductsService implements ISanphamService{
    private final ISanPhamRepository spRepository;
    private List<SanPhamModel> lstSP;
    
    public ProductsService() {
    spRepository = new ProductsRepository();
    lstSP = new ArrayList<>();
  }
    @Override
    public List<SanPhamModel> getProducts() {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAll();
        for(SanPhamEntity sp: sanpham){
            lstSP.add(new SanPhamModel(sp.getMaSP(),sp.getHangEntity(),sp.getTheLoaiEntity(),sp.getSizeEntity(),
                    sp.getMauSacEntity(),sp.getTenSP(),sp.getNgayNhap(),sp.getGia(),sp.getSoLuong(),sp.getAnh(),sp.getTrangThai()
            ));
        }
        return lstSP;
        
    }

    @Override
    public List<SanPhamModel> getProductById(String id) {
        var x = spRepository.findById(id);
        List<SanPhamModel> listById = new ArrayList<>();
        listById.add( new SanPhamModel(x.getMaSP(),x.getHangEntity(),x.getTheLoaiEntity(),x.getSizeEntity(),
                    x.getMauSacEntity(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.getTrangThai()
            ));
        return listById;
    }

    @Override
    public SanPhamModel createNewProduct(SanPhamModel product) {
        //product.setMaSP(null);
    var x = spRepository.save(new SanPhamEntity(product.getMaSP(), product.getHangEntity(), product.getTheLoaiEntity(), 
            product.getSizeEntity(),product.getMauSacEntity(),product.getTenSP(),product.getNgayNhap(),product.getGia(),
            product.getSoLuong(),product.getAnh(),product.getTrangThai()
    ));
    return new SanPhamModel(x.getMaSP(),x.getHangEntity(),x.getTheLoaiEntity(),x.getSizeEntity(),
                    x.getMauSacEntity(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.getTrangThai()
            );
    }

    @Override
    public SanPhamModel updateProductById(SanPhamModel product) {
        var x = spRepository.save(new SanPhamEntity(product.getMaSP(), product.getHangEntity(), product.getTheLoaiEntity(), 
            product.getSizeEntity(),product.getMauSacEntity(),product.getTenSP(),product.getNgayNhap(),product.getGia(),
            product.getSoLuong(),product.getAnh(),product.getTrangThai()
    ));
    return new SanPhamModel(x.getMaSP(),x.getHangEntity(),x.getTheLoaiEntity(),x.getSizeEntity(),
                    x.getMauSacEntity(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.getTrangThai()
            );
    }

    @Override
    public SanPhamModel getProductByMaMS(int MaMS) {
        var x = spRepository.findByMS(MaMS);
    return new SanPhamModel(x.getMaSP(),x.getHangEntity(),x.getTheLoaiEntity(),x.getSizeEntity(),
                    x.getMauSacEntity(),x.getTenSP(),x.getNgayNhap(),x.getGia(),x.getSoLuong(),x.getAnh(),x.getTrangThai()
            );
    }

    @Override
    public List<SanPhamModel> getProductsActive() {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAllActive();
        for(SanPhamEntity sp: sanpham){
            lstSP.add(new SanPhamModel(sp.getMaSP(),sp.getHangEntity(),sp.getTheLoaiEntity(),sp.getSizeEntity(),
                    sp.getMauSacEntity(),sp.getTenSP(),sp.getNgayNhap(),sp.getGia(),sp.getSoLuong(),sp.getAnh(),sp.getTrangThai()
            ));
        }
        return lstSP;
    }

    @Override
    public List<SanPhamModel> getProductsinActive() {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAllInActive();
        for(SanPhamEntity sp: sanpham){
            lstSP.add(new SanPhamModel(sp.getMaSP(),sp.getHangEntity(),sp.getTheLoaiEntity(),sp.getSizeEntity(),
                    sp.getMauSacEntity(),sp.getTenSP(),sp.getNgayNhap(),sp.getGia(),sp.getSoLuong(),sp.getAnh(),sp.getTrangThai()
            ));
        }
        return lstSP;
    }

    @Override
    public List<SanPhamModel> getProductByIdMa(String id) {
        lstSP = new ArrayList<>();
        var sanpham = spRepository.fildAllMa();
        for(SanPhamEntity sp: sanpham){
            lstSP.add(new SanPhamModel(sp.getMaSP()
            ));
        }
        return lstSP;
    }

    

    
    
}
