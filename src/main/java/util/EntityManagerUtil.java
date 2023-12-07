package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
