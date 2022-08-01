/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.HibernateUtil;
import Utilities.JpaUtils;
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
public class TheLoaiRepository implements ITheLoaiRepository{

    @Override
    public List<TheLoai> findAll() {
       List<TheLoai> theloai;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT t FROM THELOAI t";
        TypedQuery<TheLoai> query = em.createQuery(hql, TheLoai.class);
        theloai = query.getResultList();
        return theloai;
    }

    @Override
    public TheLoai save(TheLoai theLoai) {
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
    public TheLoai findByName(String name) {
        TheLoai theLoai;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM THELOAI t WHERE TenLoai = :name ";
            TypedQuery<TheLoai> query = session.createQuery(hql, TheLoai.class);
            query.setParameter("name", name);
            theLoai = query.getSingleResult();
        }
        return theLoai;
    }
    
}
