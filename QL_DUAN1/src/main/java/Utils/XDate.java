/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HOANGGOANG
 */
public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    public static Date toDate(String date,String pattern){
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String toString(Date date, String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
}
