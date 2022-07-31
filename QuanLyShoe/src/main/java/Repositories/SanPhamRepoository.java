/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.SanPhamEntity;
import Utilities.HibernateUtil;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Transaction;

/**
 *
 * @author boquy
 */
// domainmodel
public class SanPhamRepoository implements IRepoository<SanPhamEntity> {

    @Override
    public List<SanPhamEntity> selectAll() {
        List<SanPhamEntity> SanPhamEntity;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM SANPHAM  p";

            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            SanPhamEntity = query.getResultList();
        }
        return SanPhamEntity;
    }

    @Override
    public SanPhamEntity selectID(int id) {

        return null;
    }

    @Override
    public SanPhamEntity save(SanPhamEntity sanPham) {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(sanPham);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                sanPham = null;
            }
        } finally {
            return sanPham;
        }
        
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<SanPhamEntity> findById(String DK) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    

 
  

}
