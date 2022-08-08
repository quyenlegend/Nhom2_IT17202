/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModels.TheLoaiEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ITheLoaiRepository {
    List<TheLoaiEntity> findAll();
    TheLoaiEntity save(TheLoaiEntity theLoai);
    TheLoaiEntity findByName(String name);
}
