/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas
 */
public class EntityManagerUtil {
    
    public static EntityManager getEntityManager(){
        EntityManager em = getEntityManagerFactory().createEntityManager();
        return em;
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
        return emf;
    }
    
}