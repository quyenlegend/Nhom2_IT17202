/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.Size;
import java.util.ArrayList;
import java.util.List;
import model.QLSize;
import responsitories.ISizeRepository;
import responsitories.SizeRepository;

/**
 *
 * @author Admin
 */
public class SizeService implements ISizeService{

    private final ISizeRepository sizeRepository;
    private List<QLSize> lstSize;
    
    public SizeService() {
        sizeRepository = new SizeRepository();
        lstSize = new ArrayList<>();
    }
    @Override
    public List<Size> getAllTheLoai() {
        return sizeRepository.findAll();
    }

    @Override
    public List<QLSize> getProducts() {
        lstSize = new ArrayList<>();
        var theloai = sizeRepository.findAll();
        for(Size sp: theloai){
            lstSize.add(new QLSize(sp.getMaSize(),sp.getSize()
            ));
        }
        return lstSize;
    }

    @Override
    public QLSize createNewTL(QLSize size) {
        var x = sizeRepository.save(new Size(size.getMaSize(),size.getSize()
    ));
    return new QLSize(x.getMaSize(), x.getSize()
        );
    }

    @Override
    public List<QLSize> getProductByName(String name) {
        var x = sizeRepository.findByName(name);
        List<QLSize> listByName = new ArrayList<>();
        listByName.add( new QLSize(x.getMaSize(), x.getSize()
            ));
        return listByName;
    }
    
}
