/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Utilities.HibernateUtili;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author s2ngo
 */
public class NewClass {

    public static void main(String[] args) {
        try ( Session session = HibernateUtili.getSessionFactory().openSession()) {
//            Query<DomainModels.NhanVien> query = session.createQuery("select c from NHANVIEN c");
            org.hibernate.Query<DomainModels.NhanVien> query = session.createQuery("select c from NHANVIEN c");
            List<DomainModels.NhanVien> lst = query.list();
            lst.forEach(c -> System.out.println(c.getMaNV()));
        }
    }
}
