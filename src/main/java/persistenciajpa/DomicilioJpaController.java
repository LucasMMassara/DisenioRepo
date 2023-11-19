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
import logica.Departamento;
import logica.Domicilio;
import persistenciajpa.exceptions.NonexistentEntityException;
import persistenciajpa.exceptions.PreexistingEntityException;

/**
 *
 * @author Lucas
 */
public class DomicilioJpaController implements Serializable {

    public DomicilioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Domicilio domicilio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento depto = domicilio.getDepto();
            if (depto != null) {
                depto = em.getReference(depto.getClass(), depto.getId());
                domicilio.setDepto(depto);
            }
            em.persist(domicilio);
            if (depto != null) {
                Domicilio oldDomicilioOfDepto = depto.getDomicilio();
                if (oldDomicilioOfDepto != null) {
                    oldDomicilioOfDepto.setDepto(null);
                    oldDomicilioOfDepto = em.merge(oldDomicilioOfDepto);
                }
                depto.setDomicilio(domicilio);
                depto = em.merge(depto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDomicilio(domicilio.getId()) != null) {
                throw new PreexistingEntityException("Domicilio " + domicilio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Domicilio domicilio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilio persistentDomicilio = em.find(Domicilio.class, domicilio.getId());
            Departamento deptoOld = persistentDomicilio.getDepto();
            Departamento deptoNew = domicilio.getDepto();
            if (deptoNew != null) {
                deptoNew = em.getReference(deptoNew.getClass(), deptoNew.getId());
                domicilio.setDepto(deptoNew);
            }
            domicilio = em.merge(domicilio);
            if (deptoOld != null && !deptoOld.equals(deptoNew)) {
                deptoOld.setDomicilio(null);
                deptoOld = em.merge(deptoOld);
            }
            if (deptoNew != null && !deptoNew.equals(deptoOld)) {
                Domicilio oldDomicilioOfDepto = deptoNew.getDomicilio();
                if (oldDomicilioOfDepto != null) {
                    oldDomicilioOfDepto.setDepto(null);
                    oldDomicilioOfDepto = em.merge(oldDomicilioOfDepto);
                }
                deptoNew.setDomicilio(domicilio);
                deptoNew = em.merge(deptoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = domicilio.getId();
                if (findDomicilio(id) == null) {
                    throw new NonexistentEntityException("The domicilio with id " + id + " no longer exists.");
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
            Domicilio domicilio;
            try {
                domicilio = em.getReference(Domicilio.class, id);
                domicilio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The domicilio with id " + id + " no longer exists.", enfe);
            }
            Departamento depto = domicilio.getDepto();
            if (depto != null) {
                depto.setDomicilio(null);
                depto = em.merge(depto);
            }
            em.remove(domicilio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Domicilio> findDomicilioEntities() {
        return findDomicilioEntities(true, -1, -1);
    }

    public List<Domicilio> findDomicilioEntities(int maxResults, int firstResult) {
        return findDomicilioEntities(false, maxResults, firstResult);
    }

    private List<Domicilio> findDomicilioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Domicilio.class));
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

    public Domicilio findDomicilio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Domicilio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDomicilioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Domicilio> rt = cq.from(Domicilio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
