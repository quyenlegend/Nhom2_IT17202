/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.TheLoaiEntity;
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
public class TheLoaiRepository implements ITheLoaiRepository{

    @Override
    public List<TheLoaiEntity> findAll() {
       List<TheLoaiEntity> theloai;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT t FROM THELOAI t";
        TypedQuery<TheLoaiEntity> query = em.createQuery(hql, TheLoaiEntity.class);
        theloai = query.getResultList();
        return theloai;
    }

    @Override
    public TheLoaiEntity save(TheLoaiEntity theLoai) {
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
    public TheLoaiEntity findByName(String name) {
        TheLoaiEntity theLoai;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT t FROM THELOAI t WHERE TenLoai = :name ";
            TypedQuery<TheLoaiEntity> query = session.createQuery(hql, TheLoaiEntity.class);
            query.setParameter("name", name);
            theLoai = query.getSingleResult();
        }
        return theLoai;
    }
    
}
