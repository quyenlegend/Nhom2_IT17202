/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import DomainModels.HangEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHangSXRepository {
    List<HangEntity> findAll();
    HangEntity save(HangEntity hangSX);
    HangEntity findByName(String name);
}
