/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.SanPhamEntity;
import Repositories.IRepoository;
import Repositories.SanPhamRepoository;
import ViewModels.SanPhamModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class SanPhamService implements InterfaceService<SanPhamModel>{

    private final IRepoository<SanPhamEntity> _IRepoo;
    private List<SanPhamModel> _Listsp;

    public SanPhamService() {
        _IRepoo = new SanPhamRepoository();
        _Listsp = new ArrayList<>();
    }

    @Override
    public List<SanPhamModel> Select() {
        var sp = _IRepoo.selectAll();
        for (SanPhamEntity x : sp) {
            _Listsp.add(new SanPhamModel(x.getMaSP(), x.getHangEntity(), x.getTheLoaiEntity(), x.getSizeEntity(), x.getMauSacEntity(), x.getTenSP(), x.getNgayNhap(), x.getGia(), x.getSoLuong(), x.getAnh(), x.getTrangThai()));
            
        }
        return _Listsp;
    }

    @Override
    public SanPhamModel getID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamModel createNew(SanPhamModel KeyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamModel updateById(SanPhamModel KeyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamModel> getIDnhieu(String MaHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    }

//    public  List<SanPhamModel> Select() {
//        var sanPham =_IR.selectAll();
//        for (SanPhamEntity x : sanPham) {
//           // _listSpModel.add(SanPhamService.sp(x));
//             _listSpModel.add(new SanPhamModel(x.getMaSP(), x.getHangEntity(), x.getTheLoaiEntity(), x.getSizeEntity(), x.getMauSacEntity(), x.getTenSP(), x.getNgayNhap(), 0, 0, "", 0));
//        }
//        return _listSpModel;
//    }

//mauModel /TLModel /SSSZ ,hangMD
//    public static SanPhamModel sp(SanPhamEntity x){
//        return new SanPhamModel(x.getMaSP(), HangService.hangMD(x.getHangEntity()), TheLoaiService.TLModel(x.getTheLoaiEntity()) ,SizeService.SSSZ(x.getSizeEntity()) ,MauSacService.mauModel(x.getMauSacEntity()) , x.getTenSP(), x.getNgayNhap(), 0, 0, x.getAnh(), 0);
//    }
