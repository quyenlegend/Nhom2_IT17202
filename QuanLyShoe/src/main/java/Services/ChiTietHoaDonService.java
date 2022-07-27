/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.ChiTietHoaDonEntity;
import Repositories.ChiTietHoaDonRepoository;
import Repositories.IRepoository;
import ViewModels.ChiTietHoaDonModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class ChiTietHoaDonService implements InterfaceService<ChiTietHoaDonModel>{
    private final IRepoository<ChiTietHoaDonEntity> _IRepoo;
    private List<ChiTietHoaDonModel>_ListCTHD;

    public ChiTietHoaDonService() {
        _IRepoo = new ChiTietHoaDonRepoository();
        _ListCTHD = new ArrayList<>();
    }
    
    
    @Override
    public List<ChiTietHoaDonModel> Select() {
         var chiTietHD = _IRepoo.selectAll();
         for (ChiTietHoaDonEntity x : chiTietHD) {
             _ListCTHD.add(new ChiTietHoaDonModel(x.getHoaDonEntity(), x.getSanPhamEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()));
        }
         return _ListCTHD;
         
    }

    @Override
    public ChiTietHoaDonModel getID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietHoaDonModel createNew(ChiTietHoaDonModel chiTietHD) {
       var x = _IRepoo.save(new ChiTietHoaDonEntity(chiTietHD.getHoaDonEntity(), chiTietHD.getSanPhamEntity(), chiTietHD.getTenSP(), chiTietHD.getSize(), chiTietHD.getMau(), chiTietHD.getTenHang(), chiTietHD.getTheLoai(), chiTietHD.getSoLuong(), chiTietHD.getGiaTien()));
        return new ChiTietHoaDonModel(x.getHoaDonEntity(), x.getSanPhamEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien());
    }

    @Override
    public ChiTietHoaDonModel updateById(ChiTietHoaDonModel KeyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietHoaDonModel> getIDnhieu(String MaHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
