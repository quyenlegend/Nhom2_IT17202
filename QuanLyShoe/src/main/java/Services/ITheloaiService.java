/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.TheLoaiEntity;
import ViewModels.TheLoaiModel;
import java.util.List;


/**
 *
 * @author Admin
 */
public interface ITheloaiService {
    List<TheLoaiEntity> getAllTheLoai();
    List<TheLoaiModel> getProducts();
    TheLoaiModel createNewTL(TheLoaiModel theLoai);
    List<TheLoaiModel>getProductByName(String name);
    
}
