/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.NhanVien;
import Utilities.HibernateUtili;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class NhanVien_Mapped {

    public List<NhanVien> getList() {
        List<NhanVien> nhanViens;
        try ( Session session = HibernateUtili.getSessionFactory().openSession()) {
            TypedQuery<NhanVien> query = session.createQuery("from NHANVIEN");
            nhanViens = query.getResultList();
        }
        return nhanViens;
    }

    public String add(NhanVien nv) {
        try ( Session session = HibernateUtili.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                session.save(nv);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                return "Them Khong Thanh Cong";
            }
        }
        return "Them Thanh Cong";
    }

    public String edit(NhanVien nv) {
        try ( Session session = HibernateUtili.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                session.update(nv);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                return "Sua Khong Thanh Cong";
            }
        }
        return "Sua Thanh Cong";
    }

}
