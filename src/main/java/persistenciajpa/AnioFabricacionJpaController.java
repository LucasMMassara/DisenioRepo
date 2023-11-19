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
import logica.AnioFabricacion;
import persistenciajpa.exceptions.NonexistentEntityException;
import persistenciajpa.exceptions.PreexistingEntityException;

/**
 *
 * @author Lucas
 */
public class AnioFabricacionJpaController implements Serializable {

    public AnioFabricacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AnioFabricacion anioFabricacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(anioFabricacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnioFabricacion(anioFabricacion.getAnio()) != null) {
                throw new PreexistingEntityException("AnioFabricacion " + anioFabricacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AnioFabricacion anioFabricacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            anioFabricacion = em.merge(anioFabricacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anioFabricacion.getAnio();
                if (findAnioFabricacion(id) == null) {
                    throw new NonexistentEntityException("The anioFabricacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AnioFabricacion anioFabricacion;
            try {
                anioFabricacion = em.getReference(AnioFabricacion.class, id);
                anioFabricacion.getAnio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anioFabricacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(anioFabricacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AnioFabricacion> findAnioFabricacionEntities() {
        return findAnioFabricacionEntities(true, -1, -1);
    }

    public List<AnioFabricacion> findAnioFabricacionEntities(int maxResults, int firstResult) {
        return findAnioFabricacionEntities(false, maxResults, firstResult);
    }

    private List<AnioFabricacion> findAnioFabricacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AnioFabricacion.class));
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

    public AnioFabricacion findAnioFabricacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AnioFabricacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnioFabricacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AnioFabricacion> rt = cq.from(AnioFabricacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
