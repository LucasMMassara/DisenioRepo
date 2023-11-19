/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistenciajpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.PremioYDescuentos;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class PremioYDescuentosJpaController implements Serializable {

    public PremioYDescuentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PremioYDescuentos premioYDescuentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(premioYDescuentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PremioYDescuentos premioYDescuentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            premioYDescuentos = em.merge(premioYDescuentos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = premioYDescuentos.getId();
                if (findPremioYDescuentos(id) == null) {
                    throw new NonexistentEntityException("The premioYDescuentos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PremioYDescuentos premioYDescuentos;
            try {
                premioYDescuentos = em.getReference(PremioYDescuentos.class, id);
                premioYDescuentos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The premioYDescuentos with id " + id + " no longer exists.", enfe);
            }
            em.remove(premioYDescuentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PremioYDescuentos> findPremioYDescuentosEntities() {
        return findPremioYDescuentosEntities(true, -1, -1);
    }

    public List<PremioYDescuentos> findPremioYDescuentosEntities(int maxResults, int firstResult) {
        return findPremioYDescuentosEntities(false, maxResults, firstResult);
    }

    private List<PremioYDescuentos> findPremioYDescuentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PremioYDescuentos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PremioYDescuentos findPremioYDescuentos(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PremioYDescuentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPremioYDescuentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PremioYDescuentos> rt = cq.from(PremioYDescuentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
