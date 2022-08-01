/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.MauSac;
import java.util.ArrayList;
import java.util.List;
import model.QlMauSac;
import responsitories.IMauSacRepository;
import responsitories.MauSacRepository;

/**
 *
 * @author Admin
 */
public class MauSacService implements IMauSacService{
    
    private final IMauSacRepository mauiRepository;
    private List<QlMauSac> lstMS;
    
    public MauSacService() {
        mauiRepository = new MauSacRepository();
        lstMS = new ArrayList<>();
    }
    @Override
    public List<MauSac> getAllMauSac() {
        return mauiRepository.findAll();
    }

    @Override
    public List<QlMauSac> getProducts() {
         lstMS = new ArrayList<>();
        var theloai = mauiRepository.findAll();
        for(MauSac sp: theloai){
            lstMS.add(new QlMauSac(sp.getMaMS(),sp.getTenMau()
            ));
        }
        return lstMS;
    }

    @Override
    public QlMauSac createNewMS(QlMauSac mauSac) {
        var x = mauiRepository.save(new MauSac(mauSac.getMaMS(),mauSac.getTenMau()
        ));
    return new QlMauSac(x.getMaMS(), x.getTenMau()
        );
    }

    @Override
    public List<QlMauSac> getProductByName(String name) {
        var x = mauiRepository.findByName(name);
        List<QlMauSac> listByName = new ArrayList<>();
        listByName.add( new QlMauSac(x.getMaMS(), x.getTenMau()
            ));
        return listByName;
    }
}
