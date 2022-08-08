/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.MauSacEntity;
import ViewModels.MauSacModel;
import java.util.List;


/**
 *
 * @author Admin
 */
public interface IMauSacService {
    List<MauSacEntity> getAllMauSac();
    List<MauSacModel> getProducts();
    MauSacModel createNewMS(MauSacModel mauSac);
    List<MauSacModel>getProductByName(String name);
}
