/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.SizeEntity;
import ViewModels.SizeModel;
import java.util.List;

/**
 *
 * @author boquy
 */
public class SizeService{
       public static SizeModel SSSZ(SizeEntity x){
           return new SizeModel(x.getMaSize(),x.getSize());
       }
   


    
}
