/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import logica.Cliente;

/**
 *
 * @author Lucas
 */
public class GeneradorQueryCliente {
    
    public String generarQueryFiltroClientes(String numCliente, String nombre, String apellido, String tipoDoc, String numDoc){
        
        String squery = "SELECT c FROM " + Cliente.class.getName() + " c WHERE ";
        
        String cuerpoQuery = filtroAtributoGeneral(numCliente, "numCliente");
        

        cuerpoQuery = cuerpoQuery.concat(cadenaComienzaCon(nombre, "nombre"));
        cuerpoQuery = cuerpoQuery.concat(cadenaComienzaCon(apellido, "apellido"));
        cuerpoQuery = cuerpoQuery.concat(filtroAtributoGeneral(tipoDoc, "tipodni"));
        cuerpoQuery = cuerpoQuery.concat(filtroAtributoGeneral(numDoc, "numeroDni"));
        
        cuerpoQuery = verificarQuery(cuerpoQuery);
        
        squery = squery + cuerpoQuery;
        
        return squery;
    }
    
    private String filtroNumCliente(String numCliente){
        
        if(numCliente == null){
            return "";
        }
        
        return "c.numCliente = '" + numCliente + "' AND ";
    }
    
    private String filtroAtributoGeneral(String valorAtributo, String nombreAtributo){
        
        if(valorAtributo == null){
            return "";
        }
        
        return "c." + nombreAtributo + " = ' " + valorAtributo + " ' AND ";
    }
    
    private String cadenaComienzaCon(String valorAtributo, String nombreAtributo){
    
        if(valorAtributo == null){
            return "";
        }
        
        return "c." + nombreAtributo + " like '" + valorAtributo + "%' AND ";
    }
    
    private String verificarQuery(String cuerpoQuery){
        
        if (cuerpoQuery.endsWith(" AND ")) {
        int lastIndex = cuerpoQuery.lastIndexOf(" AND ");

        // Remove the last " AND" from the string
        cuerpoQuery = cuerpoQuery.substring(0, lastIndex);
        
        return cuerpoQuery;
        
        }
        
        return cuerpoQuery;
    }
    
}
