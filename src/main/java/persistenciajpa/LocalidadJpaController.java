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
import logica.Provincia;
import logica.IndicadorRiesgo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import logica.Localidad;
import persistenciajpa.exceptions.NonexistentEntityException;
import util.EntityManagerUtil;

/**
 *
 * @author Lucas
 */
public class LocalidadJpaController implements Serializable {

    public LocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public LocalidadJpaController() {
        this.emf = EntityManagerUtil.getEntityManagerFactory();
    }
        
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localidad localidad) {
        if (localidad.getHistorialIndicador() == null) {
            localidad.setHistorialIndicador(new ArrayList<IndicadorRiesgo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia provincia = localidad.getProvincia();
            if (provincia != null) {
                provincia = em.getReference(provincia.getClass(), provincia.getId());
                localidad.setProvincia(provincia);
            }
            IndicadorRiesgo indicadorActual = localidad.getIndicadorActual();
            if (indicadorActual != null) {
                indicadorActual = em.getReference(indicadorActual.getClass(), indicadorActual.getId());
                localidad.setIndicadorActual(indicadorActual);
            }
            List<IndicadorRiesgo> attachedHistorialIndicador = new ArrayList<IndicadorRiesgo>();
            for (IndicadorRiesgo historialIndicadorIndicadorRiesgoToAttach : localidad.getHistorialIndicador()) {
                historialIndicadorIndicadorRiesgoToAttach = em.getReference(historialIndicadorIndicadorRiesgoToAttach.getClass(), historialIndicadorIndicadorRiesgoToAttach.getId());
                attachedHistorialIndicador.add(historialIndicadorIndicadorRiesgoToAttach);
            }
            localidad.setHistorialIndicador(attachedHistorialIndicador);
            em.persist(localidad);
            if (provincia != null) {
                provincia.getLocalidades().add(localidad);
                provincia = em.merge(provincia);
            }
            if (indicadorActual != null) {
                Localidad oldLocalidadOfIndicadorActual = indicadorActual.getLocalidad();
                if (oldLocalidadOfIndicadorActual != null) {
                    oldLocalidadOfIndicadorActual.setIndicadorActual(null);
                    oldLocalidadOfIndicadorActual = em.merge(oldLocalidadOfIndicadorActual);
                }
                indicadorActual.setLocalidad(localidad);
                indicadorActual = em.merge(indicadorActual);
            }
            for (IndicadorRiesgo historialIndicadorIndicadorRiesgo : localidad.getHistorialIndicador()) {
                Localidad oldLocalidadOfHistorialIndicadorIndicadorRiesgo = historialIndicadorIndicadorRiesgo.getLocalidad();
                historialIndicadorIndicadorRiesgo.setLocalidad(localidad);
                historialIndicadorIndicadorRiesgo = em.merge(historialIndicadorIndicadorRiesgo);
                if (oldLocalidadOfHistorialIndicadorIndicadorRiesgo != null) {
                    oldLocalidadOfHistorialIndicadorIndicadorRiesgo.getHistorialIndicador().remove(historialIndicadorIndicadorRiesgo);
                    oldLocalidadOfHistorialIndicadorIndicadorRiesgo = em.merge(oldLocalidadOfHistorialIndicadorIndicadorRiesgo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidad localidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad persistentLocalidad = em.find(Localidad.class, localidad.getId());
            Provincia provinciaOld = persistentLocalidad.getProvincia();
            Provincia provinciaNew = localidad.getProvincia();
            IndicadorRiesgo indicadorActualOld = persistentLocalidad.getIndicadorActual();
            IndicadorRiesgo indicadorActualNew = localidad.getIndicadorActual();
            List<IndicadorRiesgo> historialIndicadorOld = persistentLocalidad.getHistorialIndicador();
            List<IndicadorRiesgo> historialIndicadorNew = localidad.getHistorialIndicador();
            if (provinciaNew != null) {
                provinciaNew = em.getReference(provinciaNew.getClass(), provinciaNew.getId());
                localidad.setProvincia(provinciaNew);
            }
            if (indicadorActualNew != null) {
                indicadorActualNew = em.getReference(indicadorActualNew.getClass(), indicadorActualNew.getId());
                localidad.setIndicadorActual(indicadorActualNew);
            }
            List<IndicadorRiesgo> attachedHistorialIndicadorNew = new ArrayList<IndicadorRiesgo>();
            for (IndicadorRiesgo historialIndicadorNewIndicadorRiesgoToAttach : historialIndicadorNew) {
                historialIndicadorNewIndicadorRiesgoToAttach = em.getReference(historialIndicadorNewIndicadorRiesgoToAttach.getClass(), historialIndicadorNewIndicadorRiesgoToAttach.getId());
                attachedHistorialIndicadorNew.add(historialIndicadorNewIndicadorRiesgoToAttach);
            }
            historialIndicadorNew = attachedHistorialIndicadorNew;
            localidad.setHistorialIndicador(historialIndicadorNew);
            localidad = em.merge(localidad);
            if (provinciaOld != null && !provinciaOld.equals(provinciaNew)) {
                provinciaOld.getLocalidades().remove(localidad);
                provinciaOld = em.merge(provinciaOld);
            }
            if (provinciaNew != null && !provinciaNew.equals(provinciaOld)) {
                provinciaNew.getLocalidades().add(localidad);
                provinciaNew = em.merge(provinciaNew);
            }
            if (indicadorActualOld != null && !indicadorActualOld.equals(indicadorActualNew)) {
                indicadorActualOld.setLocalidad(null);
                indicadorActualOld = em.merge(indicadorActualOld);
            }
            if (indicadorActualNew != null && !indicadorActualNew.equals(indicadorActualOld)) {
                Localidad oldLocalidadOfIndicadorActual = indicadorActualNew.getLocalidad();
                if (oldLocalidadOfIndicadorActual != null) {
                    oldLocalidadOfIndicadorActual.setIndicadorActual(null);
                    oldLocalidadOfIndicadorActual = em.merge(oldLocalidadOfIndicadorActual);
                }
                indicadorActualNew.setLocalidad(localidad);
                indicadorActualNew = em.merge(indicadorActualNew);
            }
            for (IndicadorRiesgo historialIndicadorOldIndicadorRiesgo : historialIndicadorOld) {
                if (!historialIndicadorNew.contains(historialIndicadorOldIndicadorRiesgo)) {
                    historialIndicadorOldIndicadorRiesgo.setLocalidad(null);
                    historialIndicadorOldIndicadorRiesgo = em.merge(historialIndicadorOldIndicadorRiesgo);
                }
            }
            for (IndicadorRiesgo historialIndicadorNewIndicadorRiesgo : historialIndicadorNew) {
                if (!historialIndicadorOld.contains(historialIndicadorNewIndicadorRiesgo)) {
                    Localidad oldLocalidadOfHistorialIndicadorNewIndicadorRiesgo = historialIndicadorNewIndicadorRiesgo.getLocalidad();
                    historialIndicadorNewIndicadorRiesgo.setLocalidad(localidad);
                    historialIndicadorNewIndicadorRiesgo = em.merge(historialIndicadorNewIndicadorRiesgo);
                    if (oldLocalidadOfHistorialIndicadorNewIndicadorRiesgo != null && !oldLocalidadOfHistorialIndicadorNewIndicadorRiesgo.equals(localidad)) {
                        oldLocalidadOfHistorialIndicadorNewIndicadorRiesgo.getHistorialIndicador().remove(historialIndicadorNewIndicadorRiesgo);
                        oldLocalidadOfHistorialIndicadorNewIndicadorRiesgo = em.merge(oldLocalidadOfHistorialIndicadorNewIndicadorRiesgo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = localidad.getId();
                if (findLocalidad(id) == null) {
                    throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.");
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
            Localidad localidad;
            try {
                localidad = em.getReference(Localidad.class, id);
                localidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.", enfe);
            }
            Provincia provincia = localidad.getProvincia();
            if (provincia != null) {
                provincia.getLocalidades().remove(localidad);
                provincia = em.merge(provincia);
            }
            IndicadorRiesgo indicadorActual = localidad.getIndicadorActual();
            if (indicadorActual != null) {
                indicadorActual.setLocalidad(null);
                indicadorActual = em.merge(indicadorActual);
            }
            List<IndicadorRiesgo> historialIndicador = localidad.getHistorialIndicador();
            for (IndicadorRiesgo historialIndicadorIndicadorRiesgo : historialIndicador) {
                historialIndicadorIndicadorRiesgo.setLocalidad(null);
                historialIndicadorIndicadorRiesgo = em.merge(historialIndicadorIndicadorRiesgo);
            }
            em.remove(localidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidad> findLocalidadEntities() {
        return findLocalidadEntities(true, -1, -1);
    }

    public List<Localidad> findLocalidadEntities(int maxResults, int firstResult) {
        return findLocalidadEntities(false, maxResults, firstResult);
    }

    private List<Localidad> findLocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidad.class));
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

    public Localidad findLocalidad(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidad> rt = cq.from(Localidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
