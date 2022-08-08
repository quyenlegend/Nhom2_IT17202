/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Validator {
    public static boolean checkEmpty(JTextField field, StringBuilder sb,String msg){
        boolean check = true;
        if(field.getText().equals("")){
            sb.append(msg).append("\n");//chứa thông báo lỗi
            field.setBackground(Color.red);
            check=false;
        }else{
            field.setBackground(Color.white);
        }
        return check;
    }
    
    public static boolean checkSoLuong(JTextField field, StringBuilder sb,String msg){
        boolean check = true;
        if(!checkEmpty(field, sb, "Bạn Chưa Nhập Số Lượng")){
            return false;
        }
        try {
            int soLuong =Integer.parseInt(field.getText());
            if(soLuong<0 || soLuong>100){
                sb.append("Số Lượng Không hợp lệ(số lượng > 0)");
                field.setBackground(Color.red);
                check = false;
            }
        } catch (Exception e) {
            sb.append("Giá trị Tuổi hông hợp lệ");
            check = false;
        }
        if(check){
            field.setBackground(Color.white);
        }
        return check;
    }
}
