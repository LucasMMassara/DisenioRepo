package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOCliente;
import daos.DAOPais;
import dto.ClienteDTO;
import gestores.GestorClientes;
import gestores.GestorPais;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
import util.CargadorDatosMarca;
import util.CargadorDatosValoresCalculo;
import util.CargadorDeDatosPais;
import util.EntityManagerUtil;
import static util.EntityManagerUtil.getEntityManager;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        /*
        GestorClientes gc = new GestorClientes();
        List<ClienteDTO> c = gc.obtenerClientePorParametros(null,"Jose" , "Sand", null, null);
        for(ClienteDTO cl:c){
            System.out.println(cl.getNombre() + " " + cl.getApellido());
        }*/
        
        /*
        
        CargadorDatosMarca cdm = new CargadorDatosMarca();
        cdm.cargarTodo();
        */
        
        
        
        
        /*
        Date d = new Date();
        System.out.println(d.getYear());
        */
       
        
        /*
        String squery ="SELECT c FROM " + Cliente.class.getName() + " c WHERE c.nombre = 'Sergio'";
        
        System.out.println(squery);
        */
        /*
        Query q = getEntityManager().createQuery(squery);
        List<Cliente> clientes = q.getResultList();*/
        /*
        DAOCliente daocli = new DAOCliente();
        daocli.filtroClientes(null, "Jose", "Sand", "DNI", null);
        */
        //new CargadorDatosCobertura().agregarCoberturas();
        
        
        App aplicacion = new App();
        aplicacion.inicioApp();
        
        
        
    }
}
