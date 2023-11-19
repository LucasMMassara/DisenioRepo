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
import logica.ValoresGenerales;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class ValoresGeneralesJpaController implements Serializable {

    public ValoresGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValoresGenerales valoresGenerales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valoresGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValoresGenerales valoresGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valoresGenerales = em.merge(valoresGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = valoresGenerales.getId();
                if (findValoresGenerales(id) == null) {
                    throw new NonexistentEntityException("The valoresGenerales with id " + id + " no longer exists.");
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
            ValoresGenerales valoresGenerales;
            try {
                valoresGenerales = em.getReference(ValoresGenerales.class, id);
                valoresGenerales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valoresGenerales with id " + id + " no longer exists.", enfe);
            }
            em.remove(valoresGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValoresGenerales> findValoresGeneralesEntities() {
        return findValoresGeneralesEntities(true, -1, -1);
    }

    public List<ValoresGenerales> findValoresGeneralesEntities(int maxResults, int firstResult) {
        return findValoresGeneralesEntities(false, maxResults, firstResult);
    }

    private List<ValoresGenerales> findValoresGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValoresGenerales.class));
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

    public ValoresGenerales findValoresGenerales(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValoresGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getValoresGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValoresGenerales> rt = cq.from(ValoresGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
