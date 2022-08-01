/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.HibernateUtil;
import Utilities.JpaUtils;
import entities.Size;
import entities.TheLoai;
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
    public List<Size> findAll() {
        List<Size> size;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT s FROM SIZE s";
        TypedQuery<Size> query = em.createQuery(hql, Size.class);
        size = query.getResultList();
        return size;
    
    }

    @Override
    public Size save(Size size) {
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
    public Size findByName(String name) {
        Size size;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM SIZE s WHERE Size = :name ";
            TypedQuery<Size> query = session.createQuery(hql, Size.class);
            query.setParameter("name", name);
            size = query.getSingleResult();
        }
        return size;
    }
    
}
