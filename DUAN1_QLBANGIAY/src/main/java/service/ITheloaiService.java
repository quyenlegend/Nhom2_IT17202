/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entities.TheLoai;
import java.util.List;
import model.QLTheLoai;

/**
 *
 * @author Admin
 */
public interface ITheloaiService {
    List<TheLoai> getAllTheLoai();
    List<QLTheLoai> getProducts();
    QLTheLoai createNewTL(QLTheLoai theLoai);
    List<QLTheLoai>getProductByName(String name);
    
}
