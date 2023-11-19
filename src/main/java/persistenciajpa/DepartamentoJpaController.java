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
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilio domicilio = departamento.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getId());
                departamento.setDomicilio(domicilio);
            }
            em.persist(departamento);
            if (domicilio != null) {
                Departamento oldDeptoOfDomicilio = domicilio.getDepto();
                if (oldDeptoOfDomicilio != null) {
                    oldDeptoOfDomicilio.setDomicilio(null);
                    oldDeptoOfDomicilio = em.merge(oldDeptoOfDomicilio);
                }
                domicilio.setDepto(departamento);
                domicilio = em.merge(domicilio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartamento(departamento.getId()) != null) {
                throw new PreexistingEntityException("Departamento " + departamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
            Domicilio domicilioOld = persistentDepartamento.getDomicilio();
            Domicilio domicilioNew = departamento.getDomicilio();
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getId());
                departamento.setDomicilio(domicilioNew);
            }
            departamento = em.merge(departamento);
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.setDepto(null);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                Departamento oldDeptoOfDomicilio = domicilioNew.getDepto();
                if (oldDeptoOfDomicilio != null) {
                    oldDeptoOfDomicilio.setDomicilio(null);
                    oldDeptoOfDomicilio = em.merge(oldDeptoOfDomicilio);
                }
                domicilioNew.setDepto(departamento);
                domicilioNew = em.merge(domicilioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = departamento.getId();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            Domicilio domicilio = departamento.getDomicilio();
            if (domicilio != null) {
                domicilio.setDepto(null);
                domicilio = em.merge(domicilio);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
