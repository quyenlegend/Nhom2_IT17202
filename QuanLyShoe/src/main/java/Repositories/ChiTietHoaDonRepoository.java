/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.ChiTietHoaDonEntity;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author boquy
 */
public class ChiTietHoaDonRepoository implements IRepoository<ChiTietHoaDonEntity> {

    @Override
    public List<ChiTietHoaDonEntity> selectAll() {
        List<ChiTietHoaDonEntity> _listCTHD;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "Select p From HOADONCHITIET p";
            TypedQuery<ChiTietHoaDonEntity> query = session.createQuery(hql, ChiTietHoaDonEntity.class);
            _listCTHD = query.getResultList();
        }
        return _listCTHD;
    }

    @Override
    public ChiTietHoaDonEntity selectID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietHoaDonEntity save(ChiTietHoaDonEntity chiTietHD) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(chiTietHD);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                chiTietHD = null;
            }
        } finally {
            return chiTietHD;
        }
    }

    @Override
    public int delete(int id) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE HOADONCHITIET p WHERE p.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                int result = query.executeUpdate();
                if (result == 0) {
                    id = 0;
                }
                trans.commit();
            } catch (Exception e) {
                id = -1;
            }
           
        }
        return id;
        
        
    }

    @Override
    public List<ChiTietHoaDonEntity> findById(String DK) {
        List<ChiTietHoaDonEntity> _listCTHD;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM HOADONCHITIET p WHERE MaHD = :id";
            TypedQuery<ChiTietHoaDonEntity> query = session.createQuery(hql, ChiTietHoaDonEntity.class);
            query.setParameter("id", DK);
            _listCTHD = query.getResultList();
        }
        return _listCTHD;

    }

}
