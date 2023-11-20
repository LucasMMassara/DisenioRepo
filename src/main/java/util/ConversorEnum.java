/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import logica.EstadoCivil;
import logica.EstadoCliente;
import logica.Iva;
import logica.TipoDocumento;
import logica.TipoSexo;

/**
 *
 * @author valec
 */
public class ConversorEnum {

    public static TipoSexo convertirStringSexo(String sexo) {
        
        TipoSexo tsexo;
        
        switch(sexo){
            case("MASCULINO") -> {
                tsexo = TipoSexo.MASCULINO;
            }
            case("FEMENINO") -> {
                tsexo = TipoSexo.FEMENINO;
            }
            default -> {
                tsexo = TipoSexo.FEMENINO;   
            }
        }
        
        return tsexo;
        
    }
    
    public ConversorEnum(){
        super();
    }
    
    public static Iva convertirStringIva(String nombreIva){
        
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
    
    public static TipoDocumento convertirStringTipoDocumento(String tipoDoc){
        
        TipoDocumento tipoDocumento;
        
        switch(tipoDoc){
            case("CC") -> {
                tipoDocumento = TipoDocumento.CC;
            }
            case("CI") -> {
                tipoDocumento = TipoDocumento.CI;
            }
            case("CIC") -> {
                tipoDocumento = TipoDocumento.CIC;
            }
            case("DNI") -> {
                tipoDocumento = TipoDocumento.DNI;
            }
            default -> {
                tipoDocumento = TipoDocumento.DNI;   
            }
            
        }   
        return tipoDocumento;
    }
    
    public static EstadoCivil convertirStringEstadoCivil(String estado){

        EstadoCivil estadoCivil;

        switch(estado){
            case("CASADO") -> {
                estadoCivil = EstadoCivil.CASADO;
            }
            case("SOLTERO") -> {
                estadoCivil = EstadoCivil.SOLTERO;
            }
            case("VIUDO") -> {
                estadoCivil = EstadoCivil.VIUDO;
            }
            case("DIVORCIADO") -> {
                estadoCivil = EstadoCivil.DIVORCIADO;
            }
            default -> {
                estadoCivil = EstadoCivil.SOLTERO;
            }

        }
        return estadoCivil;
    }
    
    public static EstadoCliente convertirEstadoCliente(String estado){
        
        EstadoCliente estadoCliente= null;
        
        
        return estadoCliente;
    }
    
    
}
