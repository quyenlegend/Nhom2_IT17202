/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.SizeEntity;
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
public class SizeRepository implements ISizeRepository{

    @Override
    public List<SizeEntity> findAll() {
        List<SizeEntity> size;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT s FROM SIZE s";
        TypedQuery<SizeEntity> query = em.createQuery(hql, SizeEntity.class);
        size = query.getResultList();
        return size;
    
    }

    @Override
    public SizeEntity save(SizeEntity size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(size);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                size = null;
            }
        } finally {
            return size;
        }
    }

    @Override
    public SizeEntity findByName(String name) {
        SizeEntity size;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM SIZE s WHERE Size = :name ";
            TypedQuery<SizeEntity> query = session.createQuery(hql, SizeEntity.class);
            query.setParameter("name", name);
            size = query.getSingleResult();
        }
        return size;
    }
    
}
