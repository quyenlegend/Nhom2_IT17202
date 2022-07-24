/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entities.SanPham;
import java.util.List;
import model.QLSanPham;

/**
 *
 * @author Admin
 */
public interface ISanphamService {
    List<QLSanPham> getProducts();
    
    //QLSanPham getProductById(String id);
     List<QLSanPham>getProductById(String id);
    
    QLSanPham getProductByMaMS(int MaMS);
    
    QLSanPham createNewProduct(QLSanPham product);
    
    QLSanPham updateProductById(QLSanPham product);
}
