/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.JpaUtils;
import entities.MauSac;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Admin
 */
public class MauSacRepository implements IMauSacRepository{

    @Override
    public List<MauSac> findAll() {
        List<MauSac> mausac;
        EntityManager em = JpaUtils.getEntityManager();
        String hql = "SELECT m FROM MAUSAC m";
        TypedQuery<MauSac> query = em.createQuery(hql, MauSac.class);
        mausac = query.getResultList();
        return mausac;
    }
    
}
