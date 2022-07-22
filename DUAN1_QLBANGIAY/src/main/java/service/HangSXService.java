/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entities.HangSX;
import java.util.List;
import responsitories.HangSXRepository;
import responsitories.IHangSXRepository;

/**
 *
 * @author Admin
 */
public class HangSXService implements IHangSXService
{
    private final IHangSXRepository hangRepository;
    
    public HangSXService() {
        hangRepository = new HangSXRepository();
    }
    
    @Override
    public List<HangSX> getAllHangSX() {
       return hangRepository.findAll();
    }
    
}
