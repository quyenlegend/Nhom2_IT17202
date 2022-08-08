/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModels.SanPhamEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISanPhamRepository {
    List<SanPhamEntity> fildAll();
    List<SanPhamEntity> fildAllActive();
    List<SanPhamEntity> fildAllInActive();
    SanPhamEntity save(SanPhamEntity sanpham);
    SanPhamEntity findById(String id);
    SanPhamEntity findByName(String TenSP);
    SanPhamEntity findByMS(int MaMS);
    SanPhamEntity findBYSize (int MaSize);
    List<SanPhamEntity> fildAllMa();
}
