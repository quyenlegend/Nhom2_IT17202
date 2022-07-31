/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author boquy
 */
public class TheLoaiModel {
    private int MaTL;
    private String TenLoai;

    public TheLoaiModel() {
    }

    public TheLoaiModel(int MaTL, String TenLoai) {
        this.MaTL = MaTL;
        this.TenLoai = TenLoai;
    }

    public int getMaTL() {
        return MaTL;
    }

    public void setMaTL(int MaTL) {
        this.MaTL = MaTL;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
    
}
