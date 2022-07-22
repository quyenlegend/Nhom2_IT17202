/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.JpaUtils;
import entities.TheLoai;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    
}
