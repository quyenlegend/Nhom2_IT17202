/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entities.MauSac;
import java.util.List;
import model.QlMauSac;

/**
 *
 * @author Admin
 */
public interface IMauSacService {
    List<MauSac> getAllMauSac();
    List<QlMauSac> getProducts();
    QlMauSac createNewMS(QlMauSac mauSac);
    List<QlMauSac>getProductByName(String name);
}
