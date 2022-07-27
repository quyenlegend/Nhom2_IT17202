/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.HangEntity;
import ViewModels.HangModel;
import java.util.List;

/**
 *
 * @author boquy
 */
public class HangService {
      
    public static HangModel hangMD( HangEntity x){
        return new HangModel(x.getMaHangSX(),x.getTenHang());
    }
     

   
    
}
