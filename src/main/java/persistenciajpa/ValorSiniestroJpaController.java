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
import logica.ValorSiniestro;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class ValorSiniestroJpaController implements Serializable {

    public ValorSiniestroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValorSiniestro valorSiniestro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valorSiniestro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValorSiniestro valorSiniestro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valorSiniestro = em.merge(valorSiniestro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = valorSiniestro.getId();
                if (findValorSiniestro(id) == null) {
                    throw new NonexistentEntityException("The valorSiniestro with id " + id + " no longer exists.");
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
            ValorSiniestro valorSiniestro;
            try {
                valorSiniestro = em.getReference(ValorSiniestro.class, id);
                valorSiniestro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valorSiniestro with id " + id + " no longer exists.", enfe);
            }
            em.remove(valorSiniestro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValorSiniestro> findValorSiniestroEntities() {
        return findValorSiniestroEntities(true, -1, -1);
    }

    public List<ValorSiniestro> findValorSiniestroEntities(int maxResults, int firstResult) {
        return findValorSiniestroEntities(false, maxResults, firstResult);
    }

    private List<ValorSiniestro> findValorSiniestroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValorSiniestro.class));
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

    public ValorSiniestro findValorSiniestro(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValorSiniestro.class, id);
        } finally {
            em.close();
        }
    }

    public int getValorSiniestroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValorSiniestro> rt = cq.from(ValorSiniestro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
