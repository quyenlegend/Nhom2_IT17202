/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.MauSacEntity;
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
public class MauSacRepository implements IMauSacRepository{

    @Override
    public List<MauSacEntity> findAll() {
        List<MauSacEntity> mausac;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT m FROM MAUSAC m";
        TypedQuery<MauSacEntity> query = em.createQuery(hql, MauSacEntity.class);
        mausac = query.getResultList();
        return mausac;
    }

    @Override
    public MauSacEntity save(MauSacEntity theLoai) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(theLoai);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                theLoai = null;
            }
        } finally {
            return theLoai;
        }
    }

    @Override
    public MauSacEntity findByName(String name) {
        MauSacEntity mauSac;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m FROM MAUSAC m WHERE TenMau = :name ";
            TypedQuery<MauSacEntity> query = session.createQuery(hql, MauSacEntity.class);
            query.setParameter("name", name);
            mauSac = query.getSingleResult();
        }
        return mauSac;
    }
    
}
