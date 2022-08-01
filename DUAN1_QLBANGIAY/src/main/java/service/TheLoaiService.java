/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.TheLoai;
import java.util.ArrayList;
import java.util.List;
import model.QLTheLoai;
import responsitories.ITheLoaiRepository;
import responsitories.TheLoaiRepository;

/**
 *
 * @author Admin
 */
public class TheLoaiService implements ITheloaiService{
    
    private final ITheLoaiRepository theloaiRepository;
    private List<QLTheLoai> lstTL;
    public TheLoaiService() {
        theloaiRepository = new TheLoaiRepository();
        lstTL = new ArrayList<>();
    }

    @Override
    public List<TheLoai> getAllTheLoai() {
        return theloaiRepository.findAll();
    }

    @Override
    public List<QLTheLoai> getProducts() {
       lstTL = new ArrayList<>();
        var theloai = theloaiRepository.findAll();
        for(TheLoai sp: theloai){
            lstTL.add(new QLTheLoai(sp.getMaTL(),sp.getTenLoai()
            ));
        }
        return lstTL;
    }

    @Override
    public QLTheLoai createNewTL(QLTheLoai theLoai) {
        var x = theloaiRepository.save(new TheLoai(theLoai.getMaTL(),theLoai.getTenLoai()
    ));
    return new QLTheLoai(x.getMaTL(), x.getTenLoai()
        );
    }

    @Override
    public List<QLTheLoai> getProductByName(String name) {
        var x = theloaiRepository.findByName(name);
        List<QLTheLoai> listByName = new ArrayList<>();
        listByName.add( new QLTheLoai(x.getMaTL(), x.getTenLoai()
            ));
        return listByName;
    }
    
}
