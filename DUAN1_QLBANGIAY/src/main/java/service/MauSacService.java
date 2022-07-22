/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.MauSac;
import java.util.List;
import responsitories.IMauSacRepository;
import responsitories.MauSacRepository;

/**
 *
 * @author Admin
 */
public class MauSacService implements IMauSacService{
    
    private final IMauSacRepository mauiRepository;
    
    public MauSacService() {
        mauiRepository = new MauSacRepository();
    }
    @Override
    public List<MauSac> getAllMauSac() {
        return mauiRepository.findAll();
    }
}
