/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.DoiTraHangEntity;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author boquy
 */
public class DoiTraHangRepoo implements IRepoository<DoiTraHangEntity>{

    @Override
    public List<DoiTraHangEntity> selectAll() {
         List<DoiTraHangEntity> DoiTraHang;
          try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM DOITRAHANG  p";
            TypedQuery<DoiTraHangEntity> query = session.createQuery(hql,DoiTraHangEntity.class);
            DoiTraHang= query.getResultList();
        } 
        return DoiTraHang;
        
    }

    @Override
    public List<DoiTraHangEntity> findById(String DK) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DoiTraHangEntity selectID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DoiTraHangEntity save(DoiTraHangEntity traHang) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(traHang);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                traHang = null;
            }
        } finally {
            return traHang;
        }


    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
