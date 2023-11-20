package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOPais;
import gestores.GestorPais;
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



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        /*
        CargadorDeDatosPais cddp = new CargadorDeDatosPais();
        cddp.cargarPackPaisesLocIndic();
        */
        
        App aplicacion = new App();
        aplicacion.inicioApp();
        
        
        //String calle, String numero, String depto, String piso
        /*Domicilio dom = new Domicilio("San Martin","3584","0","0");
        
        DomicilioJpaController domjpa = new DomicilioJpaController();
        
        LocalidadJpaController locjpa = new LocalidadJpaController();
        
        Localidad loc = locjpa.findLocalidad(3);
        System.out.println(loc.toString());
        
        dom.setLocalidad(loc);
        
        try{
            domjpa.create(dom);
        }
        catch(Exception e){
            System.out.println("Error al cargar en la BBDD");
        }
        
        /*
        GestorPais gp = new GestorPais();
        for (Pais p: gp.ObtenerPaises()){
            System.out.println(p.toString());
        }
        */
        
    }
}
