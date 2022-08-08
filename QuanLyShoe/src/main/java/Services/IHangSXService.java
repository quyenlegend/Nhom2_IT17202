/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModels.HangEntity;
import ViewModels.HangModel;

import java.util.List;


/**
 *
 * @author Admin
 */
public interface IHangSXService {
    List<HangEntity> getAllHangSX();
    List<HangModel> getProducts();
    HangModel createNewTL(HangModel theLoai);
    List<HangModel>getProductByName(String name);
}
