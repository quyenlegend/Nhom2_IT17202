/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.HoaDonEntity;
import Repositories.HoaDonRepoository;
import Repositories.IRepoository;
import ViewModels.HoaDonModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class HoaDonService implements InterfaceService<HoaDonModel>{
        private final IRepoository<HoaDonEntity>_IRepoo;
        private List<HoaDonModel>_listHD;
    
    public HoaDonService() {
        _IRepoo = new HoaDonRepoository();
        _listHD = new ArrayList<>();
    }

    
      
    @Override
    public List<HoaDonModel> Select() {
         var hoaDon = _IRepoo.selectAll();
         for (HoaDonEntity x : hoaDon) {
             _listHD.add(new HoaDonModel(x.getMaHD(), x.getNgayTao(), x.getMaNV(), x.getTenKH(), Float.parseFloat(String.valueOf( x.getTienKhachDua())),
                     Float.parseFloat(String.valueOf( x.getTienThua())),  Float.parseFloat(String.valueOf( x.getTongTien())),Integer.parseInt(String.valueOf(x.getTrangThai()))));
            
        }
         return _listHD;
    }

    @Override
    public HoaDonModel getID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonModel createNew(HoaDonModel hoaDon) {
          var  x = _IRepoo.save(new HoaDonEntity(hoaDon.getMaHD(), hoaDon.getNgayTao(), hoaDon.getMaNV(), hoaDon.getTenKH(), hoaDon.getTienKhachDua(), hoaDon.getTienThua(), hoaDon.getTongTien(), hoaDon.getTrangThai()));
          return new HoaDonModel(x.getMaHD(), x.getNgayTao(), x.getMaNV(), x.getTenKH(), x.getTienKhachDua(), x.getTienThua(), x.getTongTien(), x.getTrangThai());
    }

    @Override
    public HoaDonModel updateById(HoaDonModel KeyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonModel> getIDnhieu(String id, String s, String m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
