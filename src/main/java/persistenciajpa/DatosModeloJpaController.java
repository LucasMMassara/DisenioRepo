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
import logica.DatosModelo;
import logica.Modelo;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class DatosModeloJpaController implements Serializable {

    public DatosModeloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosModelo datosModelo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelo modelo = datosModelo.getModelo();
            if (modelo != null) {
                modelo = em.getReference(modelo.getClass(), modelo.getId());
                datosModelo.setModelo(modelo);
            }
            em.persist(datosModelo);
            if (modelo != null) {
                modelo.getDatosModelo().add(datosModelo);
                modelo = em.merge(modelo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatosModelo datosModelo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatosModelo persistentDatosModelo = em.find(DatosModelo.class, datosModelo.getId());
            Modelo modeloOld = persistentDatosModelo.getModelo();
            Modelo modeloNew = datosModelo.getModelo();
            if (modeloNew != null) {
                modeloNew = em.getReference(modeloNew.getClass(), modeloNew.getId());
                datosModelo.setModelo(modeloNew);
            }
            datosModelo = em.merge(datosModelo);
            if (modeloOld != null && !modeloOld.equals(modeloNew)) {
                modeloOld.getDatosModelo().remove(datosModelo);
                modeloOld = em.merge(modeloOld);
            }
            if (modeloNew != null && !modeloNew.equals(modeloOld)) {
                modeloNew.getDatosModelo().add(datosModelo);
                modeloNew = em.merge(modeloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = datosModelo.getId();
                if (findDatosModelo(id) == null) {
                    throw new NonexistentEntityException("The datosModelo with id " + id + " no longer exists.");
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
            DatosModelo datosModelo;
            try {
                datosModelo = em.getReference(DatosModelo.class, id);
                datosModelo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosModelo with id " + id + " no longer exists.", enfe);
            }
            Modelo modelo = datosModelo.getModelo();
            if (modelo != null) {
                modelo.getDatosModelo().remove(datosModelo);
                modelo = em.merge(modelo);
            }
            em.remove(datosModelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatosModelo> findDatosModeloEntities() {
        return findDatosModeloEntities(true, -1, -1);
    }

    public List<DatosModelo> findDatosModeloEntities(int maxResults, int firstResult) {
        return findDatosModeloEntities(false, maxResults, firstResult);
    }

    private List<DatosModelo> findDatosModeloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosModelo.class));
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

    public DatosModelo findDatosModelo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosModelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosModeloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosModelo> rt = cq.from(DatosModelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
