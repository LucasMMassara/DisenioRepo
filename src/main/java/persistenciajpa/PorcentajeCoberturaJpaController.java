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

/**
 *
 * @author Lucas
 */
public class PorcentajeCoberturaJpaController implements Serializable {

    public PorcentajeCoberturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PorcentajeCobertura porcentajeCobertura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cobertura cobertura = porcentajeCobertura.getCobertura();
            if (cobertura != null) {
                cobertura = em.getReference(cobertura.getClass(), cobertura.getId());
                porcentajeCobertura.setCobertura(cobertura);
            }
            em.persist(porcentajeCobertura);
            if (cobertura != null) {
                PorcentajeCobertura oldPorcentajeActualOfCobertura = cobertura.getPorcentajeActual();
                if (oldPorcentajeActualOfCobertura != null) {
                    oldPorcentajeActualOfCobertura.setCobertura(null);
                    oldPorcentajeActualOfCobertura = em.merge(oldPorcentajeActualOfCobertura);
                }
                cobertura.setPorcentajeActual(porcentajeCobertura);
                cobertura = em.merge(cobertura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PorcentajeCobertura porcentajeCobertura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PorcentajeCobertura persistentPorcentajeCobertura = em.find(PorcentajeCobertura.class, porcentajeCobertura.getId());
            Cobertura coberturaOld = persistentPorcentajeCobertura.getCobertura();
            Cobertura coberturaNew = porcentajeCobertura.getCobertura();
            if (coberturaNew != null) {
                coberturaNew = em.getReference(coberturaNew.getClass(), coberturaNew.getId());
                porcentajeCobertura.setCobertura(coberturaNew);
            }
            porcentajeCobertura = em.merge(porcentajeCobertura);
            if (coberturaOld != null && !coberturaOld.equals(coberturaNew)) {
                coberturaOld.setPorcentajeActual(null);
                coberturaOld = em.merge(coberturaOld);
            }
            if (coberturaNew != null && !coberturaNew.equals(coberturaOld)) {
                PorcentajeCobertura oldPorcentajeActualOfCobertura = coberturaNew.getPorcentajeActual();
                if (oldPorcentajeActualOfCobertura != null) {
                    oldPorcentajeActualOfCobertura.setCobertura(null);
                    oldPorcentajeActualOfCobertura = em.merge(oldPorcentajeActualOfCobertura);
                }
                coberturaNew.setPorcentajeActual(porcentajeCobertura);
                coberturaNew = em.merge(coberturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = porcentajeCobertura.getId();
                if (findPorcentajeCobertura(id) == null) {
                    throw new NonexistentEntityException("The porcentajeCobertura with id " + id + " no longer exists.");
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
            PorcentajeCobertura porcentajeCobertura;
            try {
                porcentajeCobertura = em.getReference(PorcentajeCobertura.class, id);
                porcentajeCobertura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The porcentajeCobertura with id " + id + " no longer exists.", enfe);
            }
            Cobertura cobertura = porcentajeCobertura.getCobertura();
            if (cobertura != null) {
                cobertura.setPorcentajeActual(null);
                cobertura = em.merge(cobertura);
            }
            em.remove(porcentajeCobertura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PorcentajeCobertura> findPorcentajeCoberturaEntities() {
        return findPorcentajeCoberturaEntities(true, -1, -1);
    }

    public List<PorcentajeCobertura> findPorcentajeCoberturaEntities(int maxResults, int firstResult) {
        return findPorcentajeCoberturaEntities(false, maxResults, firstResult);
    }

    private List<PorcentajeCobertura> findPorcentajeCoberturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PorcentajeCobertura.class));
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

    public PorcentajeCobertura findPorcentajeCobertura(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PorcentajeCobertura.class, id);
        } finally {
            em.close();
        }
    }

    public int getPorcentajeCoberturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PorcentajeCobertura> rt = cq.from(PorcentajeCobertura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
