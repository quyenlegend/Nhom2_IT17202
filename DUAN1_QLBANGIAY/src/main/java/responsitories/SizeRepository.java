/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.JpaUtils;
import entities.Size;
import entities.TheLoai;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    
}
