package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOPais;
import logica.EstadoCivil;
import util.CargadorDatosCobertura;
import util.CargadorDeDatosPais;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        /*App aplicacion = new App();
        aplicacion.inicioApp();*/
        
        CargadorDatosCobertura cdc = new CargadorDatosCobertura();
        cdc.agregarCoberturas();
        
        }
        
    }
