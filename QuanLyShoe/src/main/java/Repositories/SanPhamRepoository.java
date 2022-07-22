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

/**
 *
 * @author boquy
 */
// domainmodel
public class SanPhamRepoository implements IRepoository<SanPhamEntity>{

    
    
    @Override
    public List<SanPhamEntity> selectAll() {
          List<SanPhamEntity> SanPhamEntity;
          try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM SANPHAM  p";

            TypedQuery<SanPhamEntity> query = session.createQuery(hql,SanPhamEntity.class);
            SanPhamEntity= query.getResultList();
        } 
        return SanPhamEntity;
    }

    @Override
    public SanPhamEntity selectID(int id) {
       return null;
    }

    @Override
    public SanPhamEntity save(SanPhamEntity KeyType) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;          
    }
}