/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import logica.EstadoCliente;
import logica.Iva;
import logica.TipoDocumento;

/**
 *
 * @author valec
 */
public class ConversorEnum {
    
    ConversorEnum(){
        
    }
    
    private Iva convertirStringIva(String nombreIva){
        
        Iva iva;
        
        switch(nombreIva){
            case("NO_RESPONSABLE") -> {
                iva = Iva.NO_RESPONSABLE;
            }
            case("RESPONSABLE_MONOTRIBUTISTA") -> {
                iva = Iva.RESPONSABLE_MONOTRIBUTISTA;
            }
            case("RESPONSABLE_IVA") -> {
                iva = Iva.RESPONSABLE_IVA;
            }
            default -> {
                iva = Iva.NO_RESPONSABLE;
            }
        }
        
        return iva;
    }
    
    private TipoDocumento convertirStringTipoDocumento(String tipoDoc){
        
        TipoDocumento tipoDocumento;
        
        switch(tipoDoc){
            case("CC"):{
                tipoDocumento = TipoDocumento.CC;
            }
            case("CI"):{
                tipoDocumento = TipoDocumento.CI;
            }
            case("CIC"):{
                tipoDocumento = TipoDocumento.CIC;
            }
            case("DNI"):{
                tipoDocumento = TipoDocumento.DNI;
            }
            default:{
                tipoDocumento = TipoDocumento.DNI;   
            }
            
        }   
        return tipoDocumento;
    }
    
    private EstadoCliente convertirEstadoCliente(String estado){
        
        EstadoCliente estadoCliente= null;
        
        
        return estadoCliente;
    }
    
    
}
