/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitories;

import entities.SanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepository {
    List<SanPham> fildAll();
    List<SanPham> fildAllActive();
    List<SanPham> fildAllInActive();
    SanPham save(SanPham sanpham);
    SanPham findById(String id);
    SanPham findByName(String TenSP);
    SanPham findByMS(int MaMS);
    SanPham findBYSize (int MaSize);
}
