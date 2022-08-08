/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.MauSacEntity;
import Repositories.IMauSacRepository;
import Repositories.MauSacRepository;
import ViewModels.MauSacModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author boquy
 */
public class MauSacService implements IMauSacService{
private final IMauSacRepository mauiRepository;
    private List<MauSacModel> lstMS;
    
    public MauSacService() {
        mauiRepository = new MauSacRepository();
        lstMS = new ArrayList<>();
    }
    @Override
    public List<MauSacEntity> getAllMauSac() {
        return mauiRepository.findAll();
    }

    @Override
    public List<MauSacModel> getProducts() {
        lstMS = new ArrayList<>();
        var theloai = mauiRepository.findAll();
        for(MauSacEntity sp: theloai){
            lstMS.add(new MauSacModel(sp.getMaMS(),sp.getTenMau()
            ));
        }
        return lstMS;
    }

    @Override
    public MauSacModel createNewMS(MauSacModel mauSac) {
        var x = mauiRepository.save(new MauSacEntity(mauSac.getMaMS(),mauSac.getTenMau()
        ));
    return new MauSacModel(x.getMaMS(), x.getTenMau()
        );
    }

    @Override
    public List<MauSacModel> getProductByName(String name) {
        var x = mauiRepository.findByName(name);
        List<MauSacModel> listByName = new ArrayList<>();
        listByName.add( new MauSacModel(x.getMaMS(), x.getTenMau()
            ));
        return listByName;
    }
         

    
}
