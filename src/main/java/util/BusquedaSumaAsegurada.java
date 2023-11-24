/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Random;

/**
 *
 * @author Lucas
 */
public class BusquedaSumaAsegurada{
    
    private static String marcaGuardada = null;
    private static String modeloGuardado = null;
    private static String anioGuardado = null;
    private static double sumaGuardada  = 0.0;
    
    public static double buscarSuma(String marca,String modelo, String anio){
        
        if(!actual(marca,modelo,anio)){
            marcaGuardada = marca;
            modeloGuardado = modelo;
            anioGuardado = anio;
            
            Random rand = new Random(); 
            sumaGuardada = rand.nextDouble(5000, 20000);
        }
        
        return sumaGuardada;
    }
    
    private static boolean actual(String marca, String modelo, String anio){
        return (marcaGuardada == marca && modeloGuardado == modelo &&  anioGuardado == anio);
    }
    
}
