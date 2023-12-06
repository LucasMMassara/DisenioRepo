/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Lucas
 */
public class GestorNumerico {
    
    public Double redondearNumero(Double numero){
        return Math.round(numero * 100.0) / 100.0;
    }
}
