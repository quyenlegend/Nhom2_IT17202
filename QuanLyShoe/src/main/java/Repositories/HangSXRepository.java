/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HangEntity;
import Utilities.HibernateUtil;
import Utilities.JpaUtils;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class HangSXRepository implements IHangSXRepository {

    
    @Override
    public List<HangEntity> findAll() {
        List<HangEntity> hang;
        EntityManager em = JpaUtils.getEntityManager();
            String hql = "SELECT h FROM HANGSX h";
        TypedQuery<HangEntity> query = em.createQuery(hql, HangEntity.class);
        hang = query.getResultList();
        return hang;
    }

    @Override
    public HangEntity save(HangEntity hangSX) {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(hangSX);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                hangSX = null;
            }
        } finally {
            return hangSX;
        }
    }

    @Override
    public HangEntity findByName(String name) {
       HangEntity hangSX;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HANGSX h WHERE TenHang = :name ";
            TypedQuery<HangEntity> query = session.createQuery(hql, HangEntity.class);
            query.setParameter("name", name);
            hangSX = query.getSingleResult();
        }
        return hangSX;
    }
    
}
