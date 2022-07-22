/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entities.KhachHang;
import Utils.HibernateUtils;
import Utils.JpaUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HOANGGOANG
 */
public class KhachHangDAO implements AllDAO{

    @Override
    public List<KhachHang> findAll() {
        List<KhachHang> lstKH;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT c FROM KHACHHANG c";
        TypedQuery<KhachHang> query = em.createQuery(hql, KhachHang.class);
        lstKH = query.getResultList();
        return lstKH;
    }

    @Override
    public KhachHang findById(String maKH) {
        KhachHang khachHang;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            String hql = "SELECT b FROM KHACHHANG b WHERE b.maKH = :maKH";
        TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setParameter("maKH", maKH);
            khachHang = query.getSingleResult();
        }
        return khachHang;
    }

    @Override
    public KhachHang insert(KhachHang kh) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(kh);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                kh = null;
            }
        } finally {
            return kh;
        }
    }

    @Override
    public KhachHang update(KhachHang kh) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.update(kh);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                kh = null;
            }
        } finally {
            return kh;
        }
    }

}
