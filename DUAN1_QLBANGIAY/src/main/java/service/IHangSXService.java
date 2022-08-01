/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entities.HangSX;
import java.util.List;
import model.QLHangSX;

/**
 *
 * @author Admin
 */
public interface IHangSXService {
    List<HangSX> getAllHangSX();
    List<QLHangSX> getProducts();
    QLHangSX createNewTL(QLHangSX theLoai);
    List<QLHangSX>getProductByName(String name);
}
