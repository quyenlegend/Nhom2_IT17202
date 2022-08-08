/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModels.SizeEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISizeRepository {
    List<SizeEntity> findAll();
    SizeEntity save(SizeEntity size);
    SizeEntity findByName(String name);
}
