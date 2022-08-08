/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.List;

/**
 *
 * @author boquy
 */
public class HangModel {
    private int MaHangSX;
    private String TenHang;
  

    public HangModel() {
    }

    public HangModel(int MaHangSX, String TenHang) {
        this.MaHangSX = MaHangSX;
        this.TenHang = TenHang;
       
    }

    public int getMaHangSX() {
        return MaHangSX;
    }

    public void setMaHangSX(int MaHangSX) {
        this.MaHangSX = MaHangSX;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

 

    
    
}
