/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.JpaUtils;
import entities.HangSX;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Admin
 */
public class HangSXRepository implements IHangSXRepository{

    
    @Override
    public List<HangSX> findAll() {
        List<HangSX> hang;
        EntityManager em = JpaUtils.getEntityManager();
            String hql = "SELECT h FROM HANGSX h";
        TypedQuery<HangSX> query = em.createQuery(hql, HangSX.class);
        hang = query.getResultList();
        return hang;
    }
    
}
