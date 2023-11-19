/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistenciajpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Modificacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import logica.Poliza;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class PolizaJpaController implements Serializable {

    public PolizaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Poliza poliza) {
        if (poliza.getModificaciones() == null) {
            poliza.setModificaciones(new ArrayList<Modificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Modificacion> attachedModificaciones = new ArrayList<Modificacion>();
            for (Modificacion modificacionesModificacionToAttach : poliza.getModificaciones()) {
                modificacionesModificacionToAttach = em.getReference(modificacionesModificacionToAttach.getClass(), modificacionesModificacionToAttach.getId());
                attachedModificaciones.add(modificacionesModificacionToAttach);
            }
            poliza.setModificaciones(attachedModificaciones);
            em.persist(poliza);
            for (Modificacion modificacionesModificacion : poliza.getModificaciones()) {
                Poliza oldPolizaOfModificacionesModificacion = modificacionesModificacion.getPoliza();
                modificacionesModificacion.setPoliza(poliza);
                modificacionesModificacion = em.merge(modificacionesModificacion);
                if (oldPolizaOfModificacionesModificacion != null) {
                    oldPolizaOfModificacionesModificacion.getModificaciones().remove(modificacionesModificacion);
                    oldPolizaOfModificacionesModificacion = em.merge(oldPolizaOfModificacionesModificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Poliza poliza) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Poliza persistentPoliza = em.find(Poliza.class, poliza.getId());
            List<Modificacion> modificacionesOld = persistentPoliza.getModificaciones();
            List<Modificacion> modificacionesNew = poliza.getModificaciones();
            List<Modificacion> attachedModificacionesNew = new ArrayList<Modificacion>();
            for (Modificacion modificacionesNewModificacionToAttach : modificacionesNew) {
                modificacionesNewModificacionToAttach = em.getReference(modificacionesNewModificacionToAttach.getClass(), modificacionesNewModificacionToAttach.getId());
                attachedModificacionesNew.add(modificacionesNewModificacionToAttach);
            }
            modificacionesNew = attachedModificacionesNew;
            poliza.setModificaciones(modificacionesNew);
            poliza = em.merge(poliza);
            for (Modificacion modificacionesOldModificacion : modificacionesOld) {
                if (!modificacionesNew.contains(modificacionesOldModificacion)) {
                    modificacionesOldModificacion.setPoliza(null);
                    modificacionesOldModificacion = em.merge(modificacionesOldModificacion);
                }
            }
            for (Modificacion modificacionesNewModificacion : modificacionesNew) {
                if (!modificacionesOld.contains(modificacionesNewModificacion)) {
                    Poliza oldPolizaOfModificacionesNewModificacion = modificacionesNewModificacion.getPoliza();
                    modificacionesNewModificacion.setPoliza(poliza);
                    modificacionesNewModificacion = em.merge(modificacionesNewModificacion);
                    if (oldPolizaOfModificacionesNewModificacion != null && !oldPolizaOfModificacionesNewModificacion.equals(poliza)) {
                        oldPolizaOfModificacionesNewModificacion.getModificaciones().remove(modificacionesNewModificacion);
                        oldPolizaOfModificacionesNewModificacion = em.merge(oldPolizaOfModificacionesNewModificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = poliza.getId();
                if (findPoliza(id) == null) {
                    throw new NonexistentEntityException("The poliza with id " + id + " no longer exists.");
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
            Poliza poliza;
            try {
                poliza = em.getReference(Poliza.class, id);
                poliza.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The poliza with id " + id + " no longer exists.", enfe);
            }
            List<Modificacion> modificaciones = poliza.getModificaciones();
            for (Modificacion modificacionesModificacion : modificaciones) {
                modificacionesModificacion.setPoliza(null);
                modificacionesModificacion = em.merge(modificacionesModificacion);
            }
            em.remove(poliza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Poliza> findPolizaEntities() {
        return findPolizaEntities(true, -1, -1);
    }

    public List<Poliza> findPolizaEntities(int maxResults, int firstResult) {
        return findPolizaEntities(false, maxResults, firstResult);
    }

    private List<Poliza> findPolizaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Poliza.class));
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

    public Poliza findPoliza(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Poliza.class, id);
        } finally {
            em.close();
        }
    }

    public int getPolizaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Poliza> rt = cq.from(Poliza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
