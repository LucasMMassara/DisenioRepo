/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Lucas
 */
public class CargadorDatosGeneral {
    
    public void cargarDatos(){
        
        new CargadorDatosMarca().cargarTodo();
        new CargadorDatosPais().cargarPackPaisesLocIndic();
        new CargadorDatosValoresCalculo().cargarDatosValoresCalculo();
        new CargadorDatosCobertura().agregarCoberturas();
        
    }
    
}
