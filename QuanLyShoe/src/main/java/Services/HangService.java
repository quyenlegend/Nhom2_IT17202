/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.HangEntity;
import Repositories.HangSXRepository;
import Repositories.IHangSXRepository;
import ViewModels.HangModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class HangService implements IHangSXService{
private final IHangSXRepository hangRepository;
    private List<HangModel> lstHSX;
    public HangService() {
        hangRepository = new HangSXRepository();
        lstHSX = new ArrayList<>();
    }
    @Override
    public List<HangEntity> getAllHangSX() {
         return hangRepository.findAll();
    }

    @Override
    public List<HangModel> getProducts() {
         lstHSX = new ArrayList<>();
        var hangSX = hangRepository.findAll();
        for(HangEntity sp: hangSX){
            lstHSX.add(new HangModel(sp.getMaHangSX(),sp.getTenHang()
            ));
        }
        return lstHSX;
    }

    @Override
    public HangModel createNewTL(HangModel theLoai) {
        var x = hangRepository.save(new HangEntity(theLoai.getMaHangSX(),theLoai.getTenHang()
    ));
    return new HangModel(x.getMaHangSX(), x.getTenHang()
        );
    }

    @Override
    public List<HangModel> getProductByName(String name) {
        var x = hangRepository.findByName(name);
        List<HangModel> listByName = new ArrayList<>();
        listByName.add( new HangModel(x.getMaHangSX(), x.getTenHang()
            ));
        return listByName;
    }
      
    
     

   
    
}
