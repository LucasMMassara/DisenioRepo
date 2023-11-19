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
import logica.Cuota;
import logica.Pago;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuota cuota = pago.getCuota();
            if (cuota != null) {
                cuota = em.getReference(cuota.getClass(), cuota.getId());
                pago.setCuota(cuota);
            }
            em.persist(pago);
            if (cuota != null) {
                Pago oldPagoOfCuota = cuota.getPago();
                if (oldPagoOfCuota != null) {
                    oldPagoOfCuota.setCuota(null);
                    oldPagoOfCuota = em.merge(oldPagoOfCuota);
                }
                cuota.setPago(pago);
                cuota = em.merge(cuota);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getNroRecibo());
            Cuota cuotaOld = persistentPago.getCuota();
            Cuota cuotaNew = pago.getCuota();
            if (cuotaNew != null) {
                cuotaNew = em.getReference(cuotaNew.getClass(), cuotaNew.getId());
                pago.setCuota(cuotaNew);
            }
            pago = em.merge(pago);
            if (cuotaOld != null && !cuotaOld.equals(cuotaNew)) {
                cuotaOld.setPago(null);
                cuotaOld = em.merge(cuotaOld);
            }
            if (cuotaNew != null && !cuotaNew.equals(cuotaOld)) {
                Pago oldPagoOfCuota = cuotaNew.getPago();
                if (oldPagoOfCuota != null) {
                    oldPagoOfCuota.setCuota(null);
                    oldPagoOfCuota = em.merge(oldPagoOfCuota);
                }
                cuotaNew.setPago(pago);
                cuotaNew = em.merge(cuotaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pago.getNroRecibo();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getNroRecibo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            Cuota cuota = pago.getCuota();
            if (cuota != null) {
                cuota.setPago(null);
                cuota = em.merge(cuota);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
