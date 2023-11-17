/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.ArrayList;
import java.util.List;
import logica.Pais;
import persistencia.ProvinciaJpaController;
import logica.Provincia;

public class DAOProvincia extends DAOAbstract<Provincia> {
    
    public DAOProvincia() {
        setClazz(Provincia.class);
    }
}
