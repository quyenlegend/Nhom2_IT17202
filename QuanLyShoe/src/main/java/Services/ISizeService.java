/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.SizeEntity;
import ViewModels.SizeModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISizeService {
    List<SizeEntity> getAllSIZE();
    List<SizeModel> getProducts();
    SizeModel createNewTL(SizeModel size);
    List<SizeModel>getProductByName(String name);
}
