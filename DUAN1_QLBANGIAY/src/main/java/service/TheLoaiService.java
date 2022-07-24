/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.TheLoai;
import java.util.List;
import responsitories.ITheLoaiRepository;
import responsitories.TheLoaiRepository;

/**
 *
 * @author Admin
 */
public class TheLoaiService implements ITheloaiService{
    
    private final ITheLoaiRepository theloaiRepository;
    
    public TheLoaiService() {
        theloaiRepository = new TheLoaiRepository();
    }

    @Override
    public List<TheLoai> getAllTheLoai() {
        return theloaiRepository.findAll();
    }
    
}
