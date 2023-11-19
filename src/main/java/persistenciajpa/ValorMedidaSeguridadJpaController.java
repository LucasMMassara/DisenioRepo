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
import logica.ValorMedidaSeguridad;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class ValorMedidaSeguridadJpaController implements Serializable {

    public ValorMedidaSeguridadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ValorMedidaSeguridad valorMedidaSeguridad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(valorMedidaSeguridad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ValorMedidaSeguridad valorMedidaSeguridad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            valorMedidaSeguridad = em.merge(valorMedidaSeguridad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = valorMedidaSeguridad.getId();
                if (findValorMedidaSeguridad(id) == null) {
                    throw new NonexistentEntityException("The valorMedidaSeguridad with id " + id + " no longer exists.");
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
            ValorMedidaSeguridad valorMedidaSeguridad;
            try {
                valorMedidaSeguridad = em.getReference(ValorMedidaSeguridad.class, id);
                valorMedidaSeguridad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valorMedidaSeguridad with id " + id + " no longer exists.", enfe);
            }
            em.remove(valorMedidaSeguridad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ValorMedidaSeguridad> findValorMedidaSeguridadEntities() {
        return findValorMedidaSeguridadEntities(true, -1, -1);
    }

    public List<ValorMedidaSeguridad> findValorMedidaSeguridadEntities(int maxResults, int firstResult) {
        return findValorMedidaSeguridadEntities(false, maxResults, firstResult);
    }

    private List<ValorMedidaSeguridad> findValorMedidaSeguridadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ValorMedidaSeguridad.class));
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

    public ValorMedidaSeguridad findValorMedidaSeguridad(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ValorMedidaSeguridad.class, id);
        } finally {
            em.close();
        }
    }

    public int getValorMedidaSeguridadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ValorMedidaSeguridad> rt = cq.from(ValorMedidaSeguridad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
