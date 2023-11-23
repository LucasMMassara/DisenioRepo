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
public class SubsistemaSiniestros {
    
    public String obtenerCantSiniestros(String tipoDni, String dni, String tipoSexo){
        
        Random rand = new Random();
        
        int valor = rand.nextInt(0, 3);
        String cantSiniestros;
        
        
        cantSiniestros = switch (valor) {
            case (0) -> "CERO";
            case (1) -> "UNO";
            case (2) -> "DOS";
            case (3) -> "MAS_DE_UNO";
            default -> "CERO";
        };
        
        //CERO,UNO,DOS,MAS_DE_UNO
        
        return cantSiniestros;
    }
    
}
