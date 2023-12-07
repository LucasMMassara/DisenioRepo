package util;

import java.util.Random;

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
        
        return new GestorNumerico().redondearNumero(sumaGuardada);
    }
    
    private static boolean actual(String marca, String modelo, String anio){
        return marcaGuardada==marca && modeloGuardado==modelo &&  anioGuardado==anio;
    }
    
}
