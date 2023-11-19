package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOPais;
import logica.EstadoCivil;
import logica.Pais;
import persistenciajpa.PaisJpaController;
import util.CargadorDatosCobertura;
import util.CargadorDatosValoresCalculo;
import util.CargadorDeDatosPais;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        /*App aplicacion = new App();
        aplicacion.inicioApp();*/
        
        PaisJpaController pjpa = new PaisJpaController();
        for(Pais p: pjpa.findPaisEntities()){
            System.out.println(p.toString());
        }
        
    }
}
