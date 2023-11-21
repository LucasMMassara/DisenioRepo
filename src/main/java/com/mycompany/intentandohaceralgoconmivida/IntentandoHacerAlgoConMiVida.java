package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOCliente;
import daos.DAOPais;
import gestores.GestorPais;
import java.util.List;
import javax.persistence.Query;
import logica.Cliente;
import logica.Domicilio;
import logica.EstadoCivil;
import logica.Localidad;
import logica.Pais;
import persistenciajpa.DomicilioJpaController;
import persistenciajpa.LocalidadJpaController;
import persistenciajpa.PaisJpaController;
import util.CargadorDatosCobertura;
import util.CargadorDatosValoresCalculo;
import util.CargadorDeDatosPais;
import static util.EntityManagerUtil.getEntityManager;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        /*
        CargadorDeDatosPais cddp = new CargadorDeDatosPais();
        cddp.cargarPackPaisesLocIndic();
        */
        
        String squery ="SELECT c FROM " + Cliente.class.getName() + " c WHERE c.nombre = 'Sergio'";
        Query q = getEntityManager().createQuery(squery);
        List<Cliente> clientes = q.getResultList();

        
        for(Cliente c:clientes){
            System.out.println(c.toString());
        }
        
        /*
        App aplicacion = new App();
        aplicacion.inicioApp();
        */
        
    }
}
