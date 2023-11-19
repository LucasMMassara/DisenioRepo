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
import logica.IndicadorRiesgo;
import logica.Localidad;
import persistenciajpa.exceptions.NonexistentEntityException;
import util.EntityManagerUtil;

/**
 *
 * @author Lucas
 */
public class IndicadorRiesgoJpaController implements Serializable {

    public IndicadorRiesgoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public IndicadorRiesgoJpaController() {
        this.emf = EntityManagerUtil.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IndicadorRiesgo indicadorRiesgo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad localidad = indicadorRiesgo.getLocalidad();
            if (localidad != null) {
                localidad = em.getReference(localidad.getClass(), localidad.getId());
                indicadorRiesgo.setLocalidad(localidad);
            }
            em.persist(indicadorRiesgo);
            if (localidad != null) {
                IndicadorRiesgo oldIndicadorActualOfLocalidad = localidad.getIndicadorActual();
                if (oldIndicadorActualOfLocalidad != null) {
                    oldIndicadorActualOfLocalidad.setLocalidad(null);
                    oldIndicadorActualOfLocalidad = em.merge(oldIndicadorActualOfLocalidad);
                }
                localidad.setIndicadorActual(indicadorRiesgo);
                localidad = em.merge(localidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IndicadorRiesgo indicadorRiesgo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IndicadorRiesgo persistentIndicadorRiesgo = em.find(IndicadorRiesgo.class, indicadorRiesgo.getId());
            Localidad localidadOld = persistentIndicadorRiesgo.getLocalidad();
            Localidad localidadNew = indicadorRiesgo.getLocalidad();
            if (localidadNew != null) {
                localidadNew = em.getReference(localidadNew.getClass(), localidadNew.getId());
                indicadorRiesgo.setLocalidad(localidadNew);
            }
            indicadorRiesgo = em.merge(indicadorRiesgo);
            if (localidadOld != null && !localidadOld.equals(localidadNew)) {
                localidadOld.setIndicadorActual(null);
                localidadOld = em.merge(localidadOld);
            }
            if (localidadNew != null && !localidadNew.equals(localidadOld)) {
                IndicadorRiesgo oldIndicadorActualOfLocalidad = localidadNew.getIndicadorActual();
                if (oldIndicadorActualOfLocalidad != null) {
                    oldIndicadorActualOfLocalidad.setLocalidad(null);
                    oldIndicadorActualOfLocalidad = em.merge(oldIndicadorActualOfLocalidad);
                }
                localidadNew.setIndicadorActual(indicadorRiesgo);
                localidadNew = em.merge(localidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = indicadorRiesgo.getId();
                if (findIndicadorRiesgo(id) == null) {
                    throw new NonexistentEntityException("The indicadorRiesgo with id " + id + " no longer exists.");
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
            IndicadorRiesgo indicadorRiesgo;
            try {
                indicadorRiesgo = em.getReference(IndicadorRiesgo.class, id);
                indicadorRiesgo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indicadorRiesgo with id " + id + " no longer exists.", enfe);
            }
            Localidad localidad = indicadorRiesgo.getLocalidad();
            if (localidad != null) {
                localidad.setIndicadorActual(null);
                localidad = em.merge(localidad);
            }
            em.remove(indicadorRiesgo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IndicadorRiesgo> findIndicadorRiesgoEntities() {
        return findIndicadorRiesgoEntities(true, -1, -1);
    }

    public List<IndicadorRiesgo> findIndicadorRiesgoEntities(int maxResults, int firstResult) {
        return findIndicadorRiesgoEntities(false, maxResults, firstResult);
    }

    private List<IndicadorRiesgo> findIndicadorRiesgoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IndicadorRiesgo.class));
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

    public IndicadorRiesgo findIndicadorRiesgo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IndicadorRiesgo.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndicadorRiesgoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IndicadorRiesgo> rt = cq.from(IndicadorRiesgo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
