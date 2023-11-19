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
import logica.Modificacion;
import logica.Poliza;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class ModificacionJpaController implements Serializable {

    public ModificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modificacion modificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Poliza poliza = modificacion.getPoliza();
            if (poliza != null) {
                poliza = em.getReference(poliza.getClass(), poliza.getId());
                modificacion.setPoliza(poliza);
            }
            em.persist(modificacion);
            if (poliza != null) {
                poliza.getModificaciones().add(modificacion);
                poliza = em.merge(poliza);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modificacion modificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modificacion persistentModificacion = em.find(Modificacion.class, modificacion.getId());
            Poliza polizaOld = persistentModificacion.getPoliza();
            Poliza polizaNew = modificacion.getPoliza();
            if (polizaNew != null) {
                polizaNew = em.getReference(polizaNew.getClass(), polizaNew.getId());
                modificacion.setPoliza(polizaNew);
            }
            modificacion = em.merge(modificacion);
            if (polizaOld != null && !polizaOld.equals(polizaNew)) {
                polizaOld.getModificaciones().remove(modificacion);
                polizaOld = em.merge(polizaOld);
            }
            if (polizaNew != null && !polizaNew.equals(polizaOld)) {
                polizaNew.getModificaciones().add(modificacion);
                polizaNew = em.merge(polizaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = modificacion.getId();
                if (findModificacion(id) == null) {
                    throw new NonexistentEntityException("The modificacion with id " + id + " no longer exists.");
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
            Modificacion modificacion;
            try {
                modificacion = em.getReference(Modificacion.class, id);
                modificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modificacion with id " + id + " no longer exists.", enfe);
            }
            Poliza poliza = modificacion.getPoliza();
            if (poliza != null) {
                poliza.getModificaciones().remove(modificacion);
                poliza = em.merge(poliza);
            }
            em.remove(modificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modificacion> findModificacionEntities() {
        return findModificacionEntities(true, -1, -1);
    }

    public List<Modificacion> findModificacionEntities(int maxResults, int firstResult) {
        return findModificacionEntities(false, maxResults, firstResult);
    }

    private List<Modificacion> findModificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modificacion.class));
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

    public Modificacion findModificacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getModificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modificacion> rt = cq.from(Modificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
