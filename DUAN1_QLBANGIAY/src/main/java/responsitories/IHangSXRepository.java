/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitories;

import entities.HangSX;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHangSXRepository {
    List<HangSX> findAll();
    HangSX save(HangSX hangSX);
    HangSX findByName(String name);
}
