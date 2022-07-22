/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package responsitories;

import entities.MauSac;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMauSacRepository {
    List<MauSac> findAll();
}
