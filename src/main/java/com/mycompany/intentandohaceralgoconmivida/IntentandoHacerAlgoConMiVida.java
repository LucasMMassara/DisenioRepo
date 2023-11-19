package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOPais;
import logica.EstadoCivil;
import util.CargadorDatosCobertura;
import util.CargadorDatosValoresCalculo;
import util.CargadorDeDatosPais;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        /*App aplicacion = new App();
        aplicacion.inicioApp();*/
        
        CargadorDatosValoresCalculo cdvc = new CargadorDatosValoresCalculo();
        cdvc.cargarDatosValoresCalculo();
        }
        
    }
