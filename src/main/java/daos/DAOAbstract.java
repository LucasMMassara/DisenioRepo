/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import util.EntityManagerUtil;

/**
 *
 * @author Lucas
 */
public abstract class DAOAbstract<T> implements DAO<T> {
    
    private EntityManager em = EntityManagerUtil.getEntityManager();
    private Class<T> clazz;

    @Override
    public Optional<T> get(int id) {
        return Optional.ofNullable(em.find(clazz, id));
    }

    @Override
    public List<T> getAll() {
        String qlString = "SELECT o FROM " + clazz.getName()+" o";
        Query query = em.createQuery(qlString);
        return query.getResultList();
    }

    @Override
    public void update(T t) {
        excecuteInsideTransaction(em -> em.merge(t));
    }

    @Override
    public void delete(T t) {
        excecuteInsideTransaction(em -> em.remove(t));
    }

    @Override
    public void save(T t) {
        excecuteInsideTransaction(em -> em.persist(t));
    }
    
    private void excecuteInsideTransaction(Consumer<EntityManager> action){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch(RuntimeException e){
            tx.rollback();
            throw e;
        }
        
    }
    
    public void setClazz(Class<T> clazz){
        this.clazz = clazz;
    }
}
