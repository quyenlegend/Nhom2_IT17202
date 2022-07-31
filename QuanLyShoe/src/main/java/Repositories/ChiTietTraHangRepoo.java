/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.ChiTietTraHangEntity;
import java.util.List;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author boquy
 */
public class ChiTietTraHangRepoo implements IRepoository<ChiTietTraHangEntity>{

    @Override
    public List<ChiTietTraHangEntity> selectAll() {
          List<ChiTietTraHangEntity> CT_TraHang;
          try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM ChiTietTra  p";
            TypedQuery<ChiTietTraHangEntity> query = session.createQuery(hql,ChiTietTraHangEntity.class);
            CT_TraHang= query.getResultList();
        } 
        return CT_TraHang;
    }

    @Override
    public List<ChiTietTraHangEntity> findById(String DK) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietTraHangEntity selectID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietTraHangEntity save(ChiTietTraHangEntity CTTraHang) {
           try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(CTTraHang);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                CTTraHang = null;
            }
        } finally {
            return CTTraHang;
        }
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
