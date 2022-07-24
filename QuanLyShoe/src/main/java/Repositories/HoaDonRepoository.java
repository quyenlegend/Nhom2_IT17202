/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.HoaDonEntity;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author boquy
 */
public class HoaDonRepoository implements IRepoository<HoaDonEntity>{

    
    @Override
    public List<HoaDonEntity> selectAll() {
        List<HoaDonEntity> HoaDonEntity;
          try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM HOADON  p";
            TypedQuery<HoaDonEntity> query = session.createQuery(hql,HoaDonEntity.class);
            HoaDonEntity= query.getResultList();
        } 
        return HoaDonEntity;
    }

    @Override
    public HoaDonEntity selectID(int id) {
       
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonEntity save(HoaDonEntity hoaDon) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(hoaDon);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                hoaDon = null;
            }
        } finally {
            return hoaDon;
        }
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonEntity findById(String id, String Size, String Mau) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
  

   


   
}
