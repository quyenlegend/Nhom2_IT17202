/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HOANGGOANG
 */
public class JpaUtils {
    private static EntityManagerFactory factory;
    private static EntityManager em;
    
    private static EntityManagerFactory getFactory() {
        if (JpaUtils.factory == null || JpaUtils.factory.isOpen() == false) {
            JpaUtils.factory = Persistence.createEntityManagerFactory("DemoJPA");
        }
        
        return JpaUtils.factory;
    }
    
    public static EntityManager getEntityManager() {
        if (JpaUtils.em == null || JpaUtils.em.isOpen() == false) {
            JpaUtils.em = JpaUtils.getFactory().createEntityManager();
        }
        
        return JpaUtils.em;
    }
}
