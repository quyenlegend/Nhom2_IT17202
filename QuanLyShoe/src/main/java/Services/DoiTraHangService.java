/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.DoiTraHangEntity;
import Repositories.DoiTraHangRepoo;
import Repositories.IRepoository;
import ViewModels.DoiTraHangModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class DoiTraHangService implements InterfaceService<DoiTraHangModel>{
     private final IRepoository<DoiTraHangEntity>_IRepoo;
     List<DoiTraHangModel>_ListTraHang;

    public DoiTraHangService() {
        _IRepoo = new DoiTraHangRepoo();
        _ListTraHang = new ArrayList<>();
    }
     

    @Override
    public List<DoiTraHangModel> Select() {
        var traHang = _IRepoo.selectAll();
        for (DoiTraHangEntity x : traHang) {
            _ListTraHang.add(new DoiTraHangModel(x.getMaDTH(),x.getHoaDonEntity(), x.getTenKH(), x.getNgayDTH(), x.getTongTienDoiTra(),x.getMaNV()));
        }
        return _ListTraHang;
    }

    @Override
    public DoiTraHangModel getID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DoiTraHangModel createNew(DoiTraHangModel traHang) {
     var x = _IRepoo.save(new DoiTraHangEntity(-1,traHang.getHoaDonEntity(), traHang.getTenKH(), traHang.getNgayDTH(), traHang.getTongTienDoiTra(),traHang.getMaNV()));
     return new DoiTraHangModel(-1,x.getHoaDonEntity(), x.getTenKH(), x.getNgayDTH(), x.getTongTienDoiTra(),x.getMaNV());
    }

    @Override
    public DoiTraHangModel updateById(DoiTraHangModel KeyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DoiTraHangModel> getIDnhieu(String MaHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
