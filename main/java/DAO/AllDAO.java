/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entities.KhachHang;
import java.util.List;

/**
 *
 * @author HOANGGOANG
 */
public interface AllDAO {
    List<KhachHang> findAll();
    KhachHang findById(String maKH);
    KhachHang insert(KhachHang kh);
    KhachHang update(KhachHang kh);
    
}
