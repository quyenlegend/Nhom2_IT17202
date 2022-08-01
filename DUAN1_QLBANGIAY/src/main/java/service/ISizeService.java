/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entities.Size;
import java.util.List;
import model.QLSize;

/**
 *
 * @author Admin
 */
public interface ISizeService {
    List<Size> getAllTheLoai();
    List<QLSize> getProducts();
    QLSize createNewTL(QLSize theLoai);
    List<QLSize>getProductByName(String name);
}
