/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author boquy
 */
public class MauSacModel {

    private int MaMS;
    private String TenMau;

    public MauSacModel(int MaMS, String TenMau) {
        this.MaMS = MaMS;
        this.TenMau = TenMau;
    }

    public MauSacModel() {
    }

    public int getMaMS() {
        return MaMS;
    }

    public void setMaMS(int MaMS) {
        this.MaMS = MaMS;
    }

    public String getTenMau() {
        return TenMau;
    }

    public void setTenMau(String TenMau) {
        this.TenMau = TenMau;
    }
    

}
