/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.ChiTietTraHangEntity;
import Repositories.ChiTietTraHangRepoo;
import Repositories.IRepoository;
import ViewModels.ChiTietTraHangModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class ChiTietTraHangService implements InterfaceService<ChiTietTraHangModel>{
    final private IRepoository<ChiTietTraHangEntity> _IRepoo;
    List<ChiTietTraHangModel> _ListCTTraHang;

    public ChiTietTraHangService() {
        _IRepoo = new ChiTietTraHangRepoo();
        _ListCTTraHang = new ArrayList<>();
    }
    
    
    @Override
    public List<ChiTietTraHangModel> Select() {
        var ctTraHang = _IRepoo.selectAll();
        for (ChiTietTraHangEntity x : ctTraHang) {
            _ListCTTraHang.add(new ChiTietTraHangModel(x.getDoiTraHangEntity(), x.getChiTietHoaDonEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()));
        }
        return _ListCTTraHang;
    }

    @Override
    public ChiTietTraHangModel getID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietTraHangModel createNew(ChiTietTraHangModel traHangCT) {
       var x = _IRepoo.save(new ChiTietTraHangEntity(traHangCT.getDoiTraHangEntity(), traHangCT.getChiTietHoaDonEntity(), traHangCT.getTenSP(), traHangCT.getSize(), traHangCT.getMau(), traHangCT.getTenHang(), traHangCT.getTheLoai(), traHangCT.getSoLuong(),traHangCT.getGiaTien()));
       return new ChiTietTraHangModel(x.getDoiTraHangEntity(), x.getChiTietHoaDonEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien());
    }

    @Override
    public ChiTietTraHangModel updateById(ChiTietTraHangModel KeyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietTraHangModel> getIDnhieu(String MaHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
