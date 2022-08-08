/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import ViewModels.SanPhamModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanphamService {
    List<SanPhamModel> getProducts();
    List<SanPhamModel> getProductsActive();
    List<SanPhamModel> getProductsinActive();
    
    //QLSanPham getProductById(String id);
     List<SanPhamModel>getProductById(String id);
     List<SanPhamModel>getProductByIdMa(String id);
    
    SanPhamModel getProductByMaMS(int MaMS);
    
    SanPhamModel createNewProduct(SanPhamModel product);
    
    SanPhamModel updateProductById(SanPhamModel product);
}
