package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOPais;
import gestores.GestorPais;
import logica.EstadoCivil;
import logica.Pais;
import persistenciajpa.PaisJpaController;
import util.CargadorDatosCobertura;
import util.CargadorDatosValoresCalculo;
import util.CargadorDeDatosPais;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        App aplicacion = new App();
        aplicacion.inicioApp();
        
        /*
        CargadorDeDatosPais cddp = new CargadorDeDatosPais();
        cddp.cargarPackPaisesLocIndic();
        
        GestorPais gp = new GestorPais();
        for (Pais p: gp.ObtenerPaises()){
            System.out.println(p.toString());
        }
        */
        
    }
}
