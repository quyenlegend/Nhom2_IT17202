/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import DomainModels.MauSacEntity;
import ViewModels.MauSacModel;
import java.util.List;

/**
 *
 * @author boquy
 */
public class MauSacService {
         public static MauSacModel mauModel(MauSacEntity x){
             return new MauSacModel(x.getMaMS(),x.getTenMau());
         }

    
}
