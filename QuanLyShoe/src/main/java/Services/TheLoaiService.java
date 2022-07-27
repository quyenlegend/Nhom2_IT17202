/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.TheLoaiEntity;
import ViewModels.TheLoaiModel;
import java.util.List;

/**
 *
 * @author boquy
 */
public class TheLoaiService {
     public static TheLoaiModel TLModel(TheLoaiEntity x){
         return new TheLoaiModel(x.getMaTL(), x.getTenLoai());
     }
   
}
