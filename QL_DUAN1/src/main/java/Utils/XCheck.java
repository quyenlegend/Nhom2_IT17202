/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Entities.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author HOANGGOANG
 */
public class XCheck {
    List<KhachHang> _lstKH;

    public XCheck() {
        _lstKH = new ArrayList<>();
    }
    public boolean checkSo(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text.replace(".", ""));
        return matcher.matches();
    }
    public boolean checkSDT(String s) {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    public int checkId(String id) {
        for (int i = 0; i < _lstKH.size(); i++) {
            if (_lstKH.get(i).getMaKH().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public int checkSdt(String sdt){
        for (int i = 0; i< _lstKH.size();i++){
            if(_lstKH.get(i).getSDT().equals(sdt)){
                return i;
            }
        }
        return -1;
    }
}
