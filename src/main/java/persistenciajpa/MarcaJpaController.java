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
import logica.Modelo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import logica.Marca;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class MarcaJpaController implements Serializable {

    public MarcaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) {
        if (marca.getModelos() == null) {
            marca.setModelos(new ArrayList<Modelo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Modelo> attachedModelos = new ArrayList<Modelo>();
            for (Modelo modelosModeloToAttach : marca.getModelos()) {
                modelosModeloToAttach = em.getReference(modelosModeloToAttach.getClass(), modelosModeloToAttach.getId());
                attachedModelos.add(modelosModeloToAttach);
            }
            marca.setModelos(attachedModelos);
            em.persist(marca);
            for (Modelo modelosModelo : marca.getModelos()) {
                Marca oldMarcaOfModelosModelo = modelosModelo.getMarca();
                modelosModelo.setMarca(marca);
                modelosModelo = em.merge(modelosModelo);
                if (oldMarcaOfModelosModelo != null) {
                    oldMarcaOfModelosModelo.getModelos().remove(modelosModelo);
                    oldMarcaOfModelosModelo = em.merge(oldMarcaOfModelosModelo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marca marca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca persistentMarca = em.find(Marca.class, marca.getId());
            List<Modelo> modelosOld = persistentMarca.getModelos();
            List<Modelo> modelosNew = marca.getModelos();
            List<Modelo> attachedModelosNew = new ArrayList<Modelo>();
            for (Modelo modelosNewModeloToAttach : modelosNew) {
                modelosNewModeloToAttach = em.getReference(modelosNewModeloToAttach.getClass(), modelosNewModeloToAttach.getId());
                attachedModelosNew.add(modelosNewModeloToAttach);
            }
            modelosNew = attachedModelosNew;
            marca.setModelos(modelosNew);
            marca = em.merge(marca);
            for (Modelo modelosOldModelo : modelosOld) {
                if (!modelosNew.contains(modelosOldModelo)) {
                    modelosOldModelo.setMarca(null);
                    modelosOldModelo = em.merge(modelosOldModelo);
                }
            }
            for (Modelo modelosNewModelo : modelosNew) {
                if (!modelosOld.contains(modelosNewModelo)) {
                    Marca oldMarcaOfModelosNewModelo = modelosNewModelo.getMarca();
                    modelosNewModelo.setMarca(marca);
                    modelosNewModelo = em.merge(modelosNewModelo);
                    if (oldMarcaOfModelosNewModelo != null && !oldMarcaOfModelosNewModelo.equals(marca)) {
                        oldMarcaOfModelosNewModelo.getModelos().remove(modelosNewModelo);
                        oldMarcaOfModelosNewModelo = em.merge(oldMarcaOfModelosNewModelo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = marca.getId();
                if (findMarca(id) == null) {
                    throw new NonexistentEntityException("The marca with id " + id + " no longer exists.");
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
            Marca marca;
            try {
                marca = em.getReference(Marca.class, id);
                marca.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marca with id " + id + " no longer exists.", enfe);
            }
            List<Modelo> modelos = marca.getModelos();
            for (Modelo modelosModelo : modelos) {
                modelosModelo.setMarca(null);
                modelosModelo = em.merge(modelosModelo);
            }
            em.remove(marca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marca> findMarcaEntities() {
        return findMarcaEntities(true, -1, -1);
    }

    public List<Marca> findMarcaEntities(int maxResults, int firstResult) {
        return findMarcaEntities(false, maxResults, firstResult);
    }

    private List<Marca> findMarcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marca.class));
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

    public Marca findMarca(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marca> rt = cq.from(Marca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
