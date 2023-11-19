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
import logica.Cobertura;
import logica.PorcentajeCobertura;
import persistenciajpa.exceptions.NonexistentEntityException;
import persistenciajpa.exceptions.PreexistingEntityException;

/**
 *
 * @author Lucas
 */
public class CoberturaJpaController implements Serializable {

    public CoberturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cobertura cobertura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PorcentajeCobertura porcentajeActual = cobertura.getPorcentajeActual();
            if (porcentajeActual != null) {
                porcentajeActual = em.getReference(porcentajeActual.getClass(), porcentajeActual.getId());
                cobertura.setPorcentajeActual(porcentajeActual);
            }
            em.persist(cobertura);
            if (porcentajeActual != null) {
                Cobertura oldCoberturaOfPorcentajeActual = porcentajeActual.getCobertura();
                if (oldCoberturaOfPorcentajeActual != null) {
                    oldCoberturaOfPorcentajeActual.setPorcentajeActual(null);
                    oldCoberturaOfPorcentajeActual = em.merge(oldCoberturaOfPorcentajeActual);
                }
                porcentajeActual.setCobertura(cobertura);
                porcentajeActual = em.merge(porcentajeActual);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCobertura(cobertura.getDetalle()) != null) {
                throw new PreexistingEntityException("Cobertura " + cobertura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cobertura cobertura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cobertura persistentCobertura = em.find(Cobertura.class, cobertura.getDetalle());
            PorcentajeCobertura porcentajeActualOld = persistentCobertura.getPorcentajeActual();
            PorcentajeCobertura porcentajeActualNew = cobertura.getPorcentajeActual();
            if (porcentajeActualNew != null) {
                porcentajeActualNew = em.getReference(porcentajeActualNew.getClass(), porcentajeActualNew.getId());
                cobertura.setPorcentajeActual(porcentajeActualNew);
            }
            cobertura = em.merge(cobertura);
            if (porcentajeActualOld != null && !porcentajeActualOld.equals(porcentajeActualNew)) {
                porcentajeActualOld.setCobertura(null);
                porcentajeActualOld = em.merge(porcentajeActualOld);
            }
            if (porcentajeActualNew != null && !porcentajeActualNew.equals(porcentajeActualOld)) {
                Cobertura oldCoberturaOfPorcentajeActual = porcentajeActualNew.getCobertura();
                if (oldCoberturaOfPorcentajeActual != null) {
                    oldCoberturaOfPorcentajeActual.setPorcentajeActual(null);
                    oldCoberturaOfPorcentajeActual = em.merge(oldCoberturaOfPorcentajeActual);
                }
                porcentajeActualNew.setCobertura(cobertura);
                porcentajeActualNew = em.merge(porcentajeActualNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cobertura.getDetalle();
                if (findCobertura(id) == null) {
                    throw new NonexistentEntityException("The cobertura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cobertura cobertura;
            try {
                cobertura = em.getReference(Cobertura.class, id);
                cobertura.getDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cobertura with id " + id + " no longer exists.", enfe);
            }
            PorcentajeCobertura porcentajeActual = cobertura.getPorcentajeActual();
            if (porcentajeActual != null) {
                porcentajeActual.setCobertura(null);
                porcentajeActual = em.merge(porcentajeActual);
            }
            em.remove(cobertura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cobertura> findCoberturaEntities() {
        return findCoberturaEntities(true, -1, -1);
    }

    public List<Cobertura> findCoberturaEntities(int maxResults, int firstResult) {
        return findCoberturaEntities(false, maxResults, firstResult);
    }

    private List<Cobertura> findCoberturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cobertura.class));
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

    public Cobertura findCobertura(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cobertura.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoberturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cobertura> rt = cq.from(Cobertura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
