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
import logica.Marca;
import logica.EstadisticaRoboVehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import logica.DatosModelo;
import logica.Modelo;
import persistenciajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Lucas
 */
public class ModeloJpaController implements Serializable {

    public ModeloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modelo modelo) {
        if (modelo.getHistorial() == null) {
            modelo.setHistorial(new ArrayList<EstadisticaRoboVehiculo>());
        }
        if (modelo.getDatosModelo() == null) {
            modelo.setDatosModelo(new ArrayList<DatosModelo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca marca = modelo.getMarca();
            if (marca != null) {
                marca = em.getReference(marca.getClass(), marca.getId());
                modelo.setMarca(marca);
            }
            EstadisticaRoboVehiculo estadisticaActual = modelo.getEstadisticaActual();
            if (estadisticaActual != null) {
                estadisticaActual = em.getReference(estadisticaActual.getClass(), estadisticaActual.getId());
                modelo.setEstadisticaActual(estadisticaActual);
            }
            List<EstadisticaRoboVehiculo> attachedHistorial = new ArrayList<EstadisticaRoboVehiculo>();
            for (EstadisticaRoboVehiculo historialEstadisticaRoboVehiculoToAttach : modelo.getHistorial()) {
                historialEstadisticaRoboVehiculoToAttach = em.getReference(historialEstadisticaRoboVehiculoToAttach.getClass(), historialEstadisticaRoboVehiculoToAttach.getId());
                attachedHistorial.add(historialEstadisticaRoboVehiculoToAttach);
            }
            modelo.setHistorial(attachedHistorial);
            List<DatosModelo> attachedDatosModelo = new ArrayList<DatosModelo>();
            for (DatosModelo datosModeloDatosModeloToAttach : modelo.getDatosModelo()) {
                datosModeloDatosModeloToAttach = em.getReference(datosModeloDatosModeloToAttach.getClass(), datosModeloDatosModeloToAttach.getId());
                attachedDatosModelo.add(datosModeloDatosModeloToAttach);
            }
            modelo.setDatosModelo(attachedDatosModelo);
            em.persist(modelo);
            if (marca != null) {
                marca.getModelos().add(modelo);
                marca = em.merge(marca);
            }
            if (estadisticaActual != null) {
                Modelo oldModeloOfEstadisticaActual = estadisticaActual.getModelo();
                if (oldModeloOfEstadisticaActual != null) {
                    oldModeloOfEstadisticaActual.setEstadisticaActual(null);
                    oldModeloOfEstadisticaActual = em.merge(oldModeloOfEstadisticaActual);
                }
                estadisticaActual.setModelo(modelo);
                estadisticaActual = em.merge(estadisticaActual);
            }
            for (EstadisticaRoboVehiculo historialEstadisticaRoboVehiculo : modelo.getHistorial()) {
                Modelo oldModeloOfHistorialEstadisticaRoboVehiculo = historialEstadisticaRoboVehiculo.getModelo();
                historialEstadisticaRoboVehiculo.setModelo(modelo);
                historialEstadisticaRoboVehiculo = em.merge(historialEstadisticaRoboVehiculo);
                if (oldModeloOfHistorialEstadisticaRoboVehiculo != null) {
                    oldModeloOfHistorialEstadisticaRoboVehiculo.getHistorial().remove(historialEstadisticaRoboVehiculo);
                    oldModeloOfHistorialEstadisticaRoboVehiculo = em.merge(oldModeloOfHistorialEstadisticaRoboVehiculo);
                }
            }
            for (DatosModelo datosModeloDatosModelo : modelo.getDatosModelo()) {
                Modelo oldModeloOfDatosModeloDatosModelo = datosModeloDatosModelo.getModelo();
                datosModeloDatosModelo.setModelo(modelo);
                datosModeloDatosModelo = em.merge(datosModeloDatosModelo);
                if (oldModeloOfDatosModeloDatosModelo != null) {
                    oldModeloOfDatosModeloDatosModelo.getDatosModelo().remove(datosModeloDatosModelo);
                    oldModeloOfDatosModeloDatosModelo = em.merge(oldModeloOfDatosModeloDatosModelo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modelo modelo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modelo persistentModelo = em.find(Modelo.class, modelo.getId());
            Marca marcaOld = persistentModelo.getMarca();
            Marca marcaNew = modelo.getMarca();
            EstadisticaRoboVehiculo estadisticaActualOld = persistentModelo.getEstadisticaActual();
            EstadisticaRoboVehiculo estadisticaActualNew = modelo.getEstadisticaActual();
            List<EstadisticaRoboVehiculo> historialOld = persistentModelo.getHistorial();
            List<EstadisticaRoboVehiculo> historialNew = modelo.getHistorial();
            List<DatosModelo> datosModeloOld = persistentModelo.getDatosModelo();
            List<DatosModelo> datosModeloNew = modelo.getDatosModelo();
            if (marcaNew != null) {
                marcaNew = em.getReference(marcaNew.getClass(), marcaNew.getId());
                modelo.setMarca(marcaNew);
            }
            if (estadisticaActualNew != null) {
                estadisticaActualNew = em.getReference(estadisticaActualNew.getClass(), estadisticaActualNew.getId());
                modelo.setEstadisticaActual(estadisticaActualNew);
            }
            List<EstadisticaRoboVehiculo> attachedHistorialNew = new ArrayList<EstadisticaRoboVehiculo>();
            for (EstadisticaRoboVehiculo historialNewEstadisticaRoboVehiculoToAttach : historialNew) {
                historialNewEstadisticaRoboVehiculoToAttach = em.getReference(historialNewEstadisticaRoboVehiculoToAttach.getClass(), historialNewEstadisticaRoboVehiculoToAttach.getId());
                attachedHistorialNew.add(historialNewEstadisticaRoboVehiculoToAttach);
            }
            historialNew = attachedHistorialNew;
            modelo.setHistorial(historialNew);
            List<DatosModelo> attachedDatosModeloNew = new ArrayList<DatosModelo>();
            for (DatosModelo datosModeloNewDatosModeloToAttach : datosModeloNew) {
                datosModeloNewDatosModeloToAttach = em.getReference(datosModeloNewDatosModeloToAttach.getClass(), datosModeloNewDatosModeloToAttach.getId());
                attachedDatosModeloNew.add(datosModeloNewDatosModeloToAttach);
            }
            datosModeloNew = attachedDatosModeloNew;
            modelo.setDatosModelo(datosModeloNew);
            modelo = em.merge(modelo);
            if (marcaOld != null && !marcaOld.equals(marcaNew)) {
                marcaOld.getModelos().remove(modelo);
                marcaOld = em.merge(marcaOld);
            }
            if (marcaNew != null && !marcaNew.equals(marcaOld)) {
                marcaNew.getModelos().add(modelo);
                marcaNew = em.merge(marcaNew);
            }
            if (estadisticaActualOld != null && !estadisticaActualOld.equals(estadisticaActualNew)) {
                estadisticaActualOld.setModelo(null);
                estadisticaActualOld = em.merge(estadisticaActualOld);
            }
            if (estadisticaActualNew != null && !estadisticaActualNew.equals(estadisticaActualOld)) {
                Modelo oldModeloOfEstadisticaActual = estadisticaActualNew.getModelo();
                if (oldModeloOfEstadisticaActual != null) {
                    oldModeloOfEstadisticaActual.setEstadisticaActual(null);
                    oldModeloOfEstadisticaActual = em.merge(oldModeloOfEstadisticaActual);
                }
                estadisticaActualNew.setModelo(modelo);
                estadisticaActualNew = em.merge(estadisticaActualNew);
            }
            for (EstadisticaRoboVehiculo historialOldEstadisticaRoboVehiculo : historialOld) {
                if (!historialNew.contains(historialOldEstadisticaRoboVehiculo)) {
                    historialOldEstadisticaRoboVehiculo.setModelo(null);
                    historialOldEstadisticaRoboVehiculo = em.merge(historialOldEstadisticaRoboVehiculo);
                }
            }
            for (EstadisticaRoboVehiculo historialNewEstadisticaRoboVehiculo : historialNew) {
                if (!historialOld.contains(historialNewEstadisticaRoboVehiculo)) {
                    Modelo oldModeloOfHistorialNewEstadisticaRoboVehiculo = historialNewEstadisticaRoboVehiculo.getModelo();
                    historialNewEstadisticaRoboVehiculo.setModelo(modelo);
                    historialNewEstadisticaRoboVehiculo = em.merge(historialNewEstadisticaRoboVehiculo);
                    if (oldModeloOfHistorialNewEstadisticaRoboVehiculo != null && !oldModeloOfHistorialNewEstadisticaRoboVehiculo.equals(modelo)) {
                        oldModeloOfHistorialNewEstadisticaRoboVehiculo.getHistorial().remove(historialNewEstadisticaRoboVehiculo);
                        oldModeloOfHistorialNewEstadisticaRoboVehiculo = em.merge(oldModeloOfHistorialNewEstadisticaRoboVehiculo);
                    }
                }
            }
            for (DatosModelo datosModeloOldDatosModelo : datosModeloOld) {
                if (!datosModeloNew.contains(datosModeloOldDatosModelo)) {
                    datosModeloOldDatosModelo.setModelo(null);
                    datosModeloOldDatosModelo = em.merge(datosModeloOldDatosModelo);
                }
            }
            for (DatosModelo datosModeloNewDatosModelo : datosModeloNew) {
                if (!datosModeloOld.contains(datosModeloNewDatosModelo)) {
                    Modelo oldModeloOfDatosModeloNewDatosModelo = datosModeloNewDatosModelo.getModelo();
                    datosModeloNewDatosModelo.setModelo(modelo);
                    datosModeloNewDatosModelo = em.merge(datosModeloNewDatosModelo);
                    if (oldModeloOfDatosModeloNewDatosModelo != null && !oldModeloOfDatosModeloNewDatosModelo.equals(modelo)) {
                        oldModeloOfDatosModeloNewDatosModelo.getDatosModelo().remove(datosModeloNewDatosModelo);
                        oldModeloOfDatosModeloNewDatosModelo = em.merge(oldModeloOfDatosModeloNewDatosModelo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = modelo.getId();
                if (findModelo(id) == null) {
                    throw new NonexistentEntityException("The modelo with id " + id + " no longer exists.");
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
            Modelo modelo;
            try {
                modelo = em.getReference(Modelo.class, id);
                modelo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modelo with id " + id + " no longer exists.", enfe);
            }
            Marca marca = modelo.getMarca();
            if (marca != null) {
                marca.getModelos().remove(modelo);
                marca = em.merge(marca);
            }
            EstadisticaRoboVehiculo estadisticaActual = modelo.getEstadisticaActual();
            if (estadisticaActual != null) {
                estadisticaActual.setModelo(null);
                estadisticaActual = em.merge(estadisticaActual);
            }
            List<EstadisticaRoboVehiculo> historial = modelo.getHistorial();
            for (EstadisticaRoboVehiculo historialEstadisticaRoboVehiculo : historial) {
                historialEstadisticaRoboVehiculo.setModelo(null);
                historialEstadisticaRoboVehiculo = em.merge(historialEstadisticaRoboVehiculo);
            }
            List<DatosModelo> datosModelo = modelo.getDatosModelo();
            for (DatosModelo datosModeloDatosModelo : datosModelo) {
                datosModeloDatosModelo.setModelo(null);
                datosModeloDatosModelo = em.merge(datosModeloDatosModelo);
            }
            em.remove(modelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modelo> findModeloEntities() {
        return findModeloEntities(true, -1, -1);
    }

    public List<Modelo> findModeloEntities(int maxResults, int firstResult) {
        return findModeloEntities(false, maxResults, firstResult);
    }

    private List<Modelo> findModeloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modelo.class));
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

    public Modelo findModelo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getModeloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modelo> rt = cq.from(Modelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
