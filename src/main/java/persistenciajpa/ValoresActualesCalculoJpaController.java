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
import logica.ValoresActualesCalculo;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class ValoresActualesCalculoJpaController implements Serializable {

    public ValoresActualesCalculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValoresActualesCalculo valoresActualesCalculo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valoresActualesCalculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValoresActualesCalculo valoresActualesCalculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valoresActualesCalculo = em.merge(valoresActualesCalculo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = valoresActualesCalculo.getId();
                if (findValoresActualesCalculo(id) == null) {
                    throw new NonexistentEntityException("The valoresActualesCalculo with id " + id + " no longer exists.");
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
            ValoresActualesCalculo valoresActualesCalculo;
            try {
                valoresActualesCalculo = em.getReference(ValoresActualesCalculo.class, id);
                valoresActualesCalculo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valoresActualesCalculo with id " + id + " no longer exists.", enfe);
            }
            em.remove(valoresActualesCalculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValoresActualesCalculo> findValoresActualesCalculoEntities() {
        return findValoresActualesCalculoEntities(true, -1, -1);
    }

    public List<ValoresActualesCalculo> findValoresActualesCalculoEntities(int maxResults, int firstResult) {
        return findValoresActualesCalculoEntities(false, maxResults, firstResult);
    }

    private List<ValoresActualesCalculo> findValoresActualesCalculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValoresActualesCalculo.class));
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

    public ValoresActualesCalculo findValoresActualesCalculo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValoresActualesCalculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getValoresActualesCalculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValoresActualesCalculo> rt = cq.from(ValoresActualesCalculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
