/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.HangSX;
import java.util.ArrayList;
import java.util.List;
import model.QLHangSX;
import responsitories.HangSXRepository;
import responsitories.IHangSXRepository;

/**
 *
 * @author Admin
 */
public class HangSXService implements IHangSXService
{
    private final IHangSXRepository hangRepository;
    private List<QLHangSX> lstHSX;
    public HangSXService() {
        hangRepository = new HangSXRepository();
        lstHSX = new ArrayList<>();
    }
    
    @Override
    public List<HangSX> getAllHangSX() {
       return hangRepository.findAll();
    }

    @Override
    public List<QLHangSX> getProducts() {
        lstHSX = new ArrayList<>();
        var hangSX = hangRepository.findAll();
        for(HangSX sp: hangSX){
            lstHSX.add(new QLHangSX(sp.getMaHangSX(),sp.getTenHang()
            ));
        }
        return lstHSX;
    }

    @Override
    public QLHangSX createNewTL(QLHangSX theLoai) {
        var x = hangRepository.save(new HangSX(theLoai.getMaHangSX(),theLoai.getTenHang()
    ));
    return new QLHangSX(x.getMaHangSX(), x.getTenHang()
        );
    }

    @Override
    public List<QLHangSX> getProductByName(String name) {
        var x = hangRepository.findByName(name);
        List<QLHangSX> listByName = new ArrayList<>();
        listByName.add( new QLHangSX(x.getMaHangSX(), x.getTenHang()
            ));
        return listByName;
    }
    
}
