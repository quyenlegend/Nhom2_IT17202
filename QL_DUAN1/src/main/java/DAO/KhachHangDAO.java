/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entities.KhachHang;
import Utils.HibernateUtils;
import Utils.JpaUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author HOANGGOANG
 */
public class KhachHangDAO implements AllDAO {

    @Override
    public List<KhachHang> findAll() {
        List<KhachHang> lstKH;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM KHACHHANG p";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            lstKH = query.getResultList();
        }
        return lstKH;
    }

    @Override
    public KhachHang findById(String maKH) {
        KhachHang khachHang;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT b FROM KHACHHANG b WHERE b.MaKH = :MaKH";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("MaKH", maKH);
            khachHang = query.getSingleResult();
        }
        return khachHang;
    }

    @Override
    public String insert(KhachHang kh) {
        if(checkId(kh.getMaKH()) != -1){
            return "Không được trùng mã!";
        }
        if (checkSdt(kh.getSDT()) != -1) {
            return "Không được trùng số điện thoại!";
        }
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(kh);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();

                trans.rollback();
                return "Thêm không thành công";
            }
        }
        return "Thêm thành công";
    }

    @Override
    public String update(KhachHang kh) {
        if(checkId(kh.getMaKH()) != -1){
            return "Không được trùng mã!";
        }
        if (checkSdt(kh.getSDT()) != -1) {
            return "Không được trùng số điện thoại!";
        }
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(kh);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                return "Sửa không thành công";
            }
        }
        return "Sửa thành công";
    }

    @Override
    public List<KhachHang> findBySdt(String sdt) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            try {
                Query query = session.createQuery("from KHACHHANG where SDT like: sdt");
                query.setParameter("sdt", sdt);
                List<KhachHang> lst = query.list();
                session.getTransaction().commit();
                session.close();
                return lst;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int checkId(String id) {
        List<KhachHang> lstKH;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM KHACHHANG p";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            lstKH = query.getResultList();
        }
        for (int i = 0; i < lstKH.size(); i++) {
            if (lstKH.get(i).getMaKH().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public int checkSdt(String sdt) {
        List<KhachHang> lstKH;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM KHACHHANG p";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            lstKH = query.getResultList();
        }
        for (int i = 0; i < lstKH.size(); i++) {
            if (lstKH.get(i).getSDT().equals(sdt)) {
                return i;
                
            }
        }
        return -1;
    }
}
