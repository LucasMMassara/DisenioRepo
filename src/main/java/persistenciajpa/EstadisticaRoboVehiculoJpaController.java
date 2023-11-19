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
import logica.EstadisticaRoboVehiculo;
import logica.Modelo;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class EstadisticaRoboVehiculoJpaController implements Serializable {

    public EstadisticaRoboVehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadisticaRoboVehiculo estadisticaRoboVehiculo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelo modelo = estadisticaRoboVehiculo.getModelo();
            if (modelo != null) {
                modelo = em.getReference(modelo.getClass(), modelo.getId());
                estadisticaRoboVehiculo.setModelo(modelo);
            }
            em.persist(estadisticaRoboVehiculo);
            if (modelo != null) {
                modelo.getHistorial().add(estadisticaRoboVehiculo);
                modelo = em.merge(modelo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadisticaRoboVehiculo estadisticaRoboVehiculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadisticaRoboVehiculo persistentEstadisticaRoboVehiculo = em.find(EstadisticaRoboVehiculo.class, estadisticaRoboVehiculo.getId());
            Modelo modeloOld = persistentEstadisticaRoboVehiculo.getModelo();
            Modelo modeloNew = estadisticaRoboVehiculo.getModelo();
            if (modeloNew != null) {
                modeloNew = em.getReference(modeloNew.getClass(), modeloNew.getId());
                estadisticaRoboVehiculo.setModelo(modeloNew);
            }
            estadisticaRoboVehiculo = em.merge(estadisticaRoboVehiculo);
            if (modeloOld != null && !modeloOld.equals(modeloNew)) {
                modeloOld.getHistorial().remove(estadisticaRoboVehiculo);
                modeloOld = em.merge(modeloOld);
            }
            if (modeloNew != null && !modeloNew.equals(modeloOld)) {
                modeloNew.getHistorial().add(estadisticaRoboVehiculo);
                modeloNew = em.merge(modeloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estadisticaRoboVehiculo.getId();
                if (findEstadisticaRoboVehiculo(id) == null) {
                    throw new NonexistentEntityException("The estadisticaRoboVehiculo with id " + id + " no longer exists.");
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
            EstadisticaRoboVehiculo estadisticaRoboVehiculo;
            try {
                estadisticaRoboVehiculo = em.getReference(EstadisticaRoboVehiculo.class, id);
                estadisticaRoboVehiculo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadisticaRoboVehiculo with id " + id + " no longer exists.", enfe);
            }
            Modelo modelo = estadisticaRoboVehiculo.getModelo();
            if (modelo != null) {
                modelo.getHistorial().remove(estadisticaRoboVehiculo);
                modelo = em.merge(modelo);
            }
            em.remove(estadisticaRoboVehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadisticaRoboVehiculo> findEstadisticaRoboVehiculoEntities() {
        return findEstadisticaRoboVehiculoEntities(true, -1, -1);
    }

    public List<EstadisticaRoboVehiculo> findEstadisticaRoboVehiculoEntities(int maxResults, int firstResult) {
        return findEstadisticaRoboVehiculoEntities(false, maxResults, firstResult);
    }

    private List<EstadisticaRoboVehiculo> findEstadisticaRoboVehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadisticaRoboVehiculo.class));
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

    public EstadisticaRoboVehiculo findEstadisticaRoboVehiculo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadisticaRoboVehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadisticaRoboVehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadisticaRoboVehiculo> rt = cq.from(EstadisticaRoboVehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
