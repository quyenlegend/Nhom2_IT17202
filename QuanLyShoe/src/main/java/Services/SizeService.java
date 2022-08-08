/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.SizeEntity;
import Repositories.ISizeRepository;
import Repositories.SizeRepository;
import ViewModels.SizeModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class SizeService implements ISizeService{
private final ISizeRepository sizeRepository;
    private List<SizeModel> lstSize;
    
    public SizeService() {
        sizeRepository = new SizeRepository();
        lstSize = new ArrayList<>();
    }
    @Override
    public List<SizeEntity> getAllSIZE() {
        return sizeRepository.findAll();
    }

    @Override
    public List<SizeModel> getProducts() {
        lstSize = new ArrayList<>();
        var theloai = sizeRepository.findAll();
        for(SizeEntity sp: theloai){
            lstSize.add(new SizeModel(sp.getMaSize(),sp.getSize()
            ));
        }
        return lstSize;
    }

    @Override
    public SizeModel createNewTL(SizeModel size) {
        var x = sizeRepository.save(new SizeEntity(size.getMaSize(),size.getSize()
    ));
    return new SizeModel(x.getMaSize(), x.getSize()
        );
    }

    @Override
    public List<SizeModel> getProductByName(String name) {
       var x = sizeRepository.findByName(name);
        List<SizeModel> listByName = new ArrayList<>();
        listByName.add( new SizeModel(x.getMaSize(), x.getSize()
            ));
        return listByName;
    }
       
   


    
}
