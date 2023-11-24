/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import javax.persistence.Query;
import logica.AnioFabricacion;
import logica.Cliente;
import static util.EntityManagerUtil.getEntityManager;

/**
 *
 * @author Lucas
 */
public class DAOAnioFabricacion extends DAOAbstract<AnioFabricacion> {

    public DAOAnioFabricacion() {
        setClazz(AnioFabricacion.class);
    }
}
