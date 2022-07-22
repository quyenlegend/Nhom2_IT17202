/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.Size;
import java.util.List;
import responsitories.ISizeRepository;
import responsitories.SizeRepository;

/**
 *
 * @author Admin
 */
public class SizeService implements ISizeService{

    private final ISizeRepository sizeRepository;
    
    public SizeService() {
        sizeRepository = new SizeRepository();
    }
    @Override
    public List<Size> getAllTheLoai() {
        return sizeRepository.findAll();
    }
    
}
