/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.HibernateUtil;
import Utilities.JpaUtils;
import entities.MauSac;
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
    public List<MauSac> findAll() {
        List<MauSac> mausac;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT m FROM MAUSAC m";
        TypedQuery<MauSac> query = em.createQuery(hql, MauSac.class);
        mausac = query.getResultList();
        return mausac;
    }

    @Override
    public MauSac save(MauSac theLoai) {
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
    public MauSac findByName(String name) {
        MauSac mauSac;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m FROM MAUSAC m WHERE TenMau = :name ";
            TypedQuery<MauSac> query = session.createQuery(hql, MauSac.class);
            query.setParameter("name", name);
            mauSac = query.getSingleResult();
        }
        return mauSac;
    }
    
}
