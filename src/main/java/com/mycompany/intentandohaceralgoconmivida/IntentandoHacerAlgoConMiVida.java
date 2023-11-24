package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import gestores.GestorCuotas;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logica.Cuota;
import logica.PremioYDescuentos;
import logica.TipoPago;
import util.CargadorDatosGeneral;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        //new CargadorDatosGeneral().cargarDatos();
        
        //new App().inicioApp();
        
       /*PremioYDescuentos pyd = new PremioYDescuentos(1500,500,250,100);
        
        GestorCuotas gc = new GestorCuotas();
        
        List<Cuota> cuotas = gc.crearCuotas((new Date()), TipoPago.SEMESTRAL, pyd);
        
        for(Cuota c:cuotas){
            System.out.println(c.toString());
        }
        */
    }
}
