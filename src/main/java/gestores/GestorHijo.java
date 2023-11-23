/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import dto.HijoDTO;
import logica.Hijo;

/**
 *
 * @author Lucas
 */
public class GestorHijo {
    
    //HijoDTO? O solo hijo?
    
    public boolean edadHijoValida(HijoDTO hijo){
        
        int aniosHijo = new GestorFecha().obtenerAnios(hijo.getFechaNacimiento());
        
        return ((aniosHijo>=18)&&(aniosHijo<=30));
        
    }
    
}
