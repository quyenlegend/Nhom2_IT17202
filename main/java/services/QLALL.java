/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import Entities.KhachHang;
import Models.KhachHangModel;
import java.util.List;

/**
 *
 * @author HOANGGOANG
 */
public interface QLALL {
    List<KhachHang> getKhachHang();
    KhachHangModel getKhachHangById(String maKH);
    KhachHangModel createKH(KhachHangModel khModel);
    KhachHangModel updateKH(KhachHangModel khModel);
}
