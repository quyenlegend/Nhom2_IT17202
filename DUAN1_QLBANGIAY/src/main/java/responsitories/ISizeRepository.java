/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitories;

import entities.Size;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISizeRepository {
    List<Size> findAll();
}
