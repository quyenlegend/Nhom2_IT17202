/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModels.MauSacEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMauSacRepository {
    List<MauSacEntity> findAll();
    MauSacEntity save(MauSacEntity theLoai);
    MauSacEntity findByName(String name);
}
