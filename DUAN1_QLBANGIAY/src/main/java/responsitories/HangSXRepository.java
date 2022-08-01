/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.HibernateUtil;
import Utilities.JpaUtils;
import entities.HangSX;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class HangSXRepository implements IHangSXRepository{

    
    @Override
    public List<HangSX> findAll() {
        List<HangSX> hang;
        EntityManager em = JpaUtils.getEntityManager();
            String hql = "SELECT h FROM HANGSX h";
        TypedQuery<HangSX> query = em.createQuery(hql, HangSX.class);
        hang = query.getResultList();
        return hang;
    }

    @Override
    public HangSX save(HangSX hangSX) {
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
    public HangSX findByName(String name) {
       HangSX hangSX;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT h FROM HANGSX h WHERE TenHang = :name ";
            TypedQuery<HangSX> query = session.createQuery(hql, HangSX.class);
            query.setParameter("name", name);
            hangSX = query.getSingleResult();
        }
        return hangSX;
    }
    
}
