/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.TheLoaiEntity;
import Repositories.ITheLoaiRepository;
import Repositories.TheLoaiRepository;
import ViewModels.TheLoaiModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class TheLoaiService implements ITheloaiService{
     private final ITheLoaiRepository theloaiRepository;
    private List<TheLoaiModel> lstTL;
    public TheLoaiService() {
        theloaiRepository = new TheLoaiRepository();
        lstTL = new ArrayList<>();
    }

    @Override
    public List<TheLoaiEntity> getAllTheLoai() {
        return theloaiRepository.findAll();
    }

    @Override
    public List<TheLoaiModel> getProducts() {
        lstTL = new ArrayList<>();
        var theloai = theloaiRepository.findAll();
        for(TheLoaiEntity sp: theloai){
            lstTL.add(new TheLoaiModel(sp.getMaTL(),sp.getTenLoai()
            ));
        }
        return lstTL;
    }

    @Override
    public TheLoaiModel createNewTL(TheLoaiModel theLoai) {
        var x = theloaiRepository.save(new TheLoaiEntity(theLoai.getMaTL(),theLoai.getTenLoai()
    ));
    return new TheLoaiModel(x.getMaTL(), x.getTenLoai()
        );
    }

    @Override
    public List<TheLoaiModel> getProductByName(String name) {
        var x = theloaiRepository.findByName(name);
        List<TheLoaiModel> listByName = new ArrayList<>();
        listByName.add( new TheLoaiModel(x.getMaTL(), x.getTenLoai()
            ));
        return listByName;
    }
   
}
