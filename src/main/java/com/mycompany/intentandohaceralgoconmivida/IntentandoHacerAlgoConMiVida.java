package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import util.CargadorDatosGeneral;



public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        
        //new CargadorDatosGeneral().cargarDatos();
        /*
        GestorClientes gc = new GestorClientes();
        List<ClienteDTO> c = gc.obtenerClientePorParametros(null,"Jose" , "Sand", null, null);
        for(ClienteDTO cl:c){
            System.out.println(cl.getNombre() + " " + cl.getApellido());
        }*/
        
        /*
        
        CargadorDatosMarca cdm = new CargadorDatosMarca();
        cdm.cargarTodo();
        */
        
        
        
        
        /*
        Date d = new Date();
        System.out.println(d.getYear());
        */
       
        
        /*
        String squery ="SELECT c FROM " + Cliente.class.getName() + " c WHERE c.nombre = 'Sergio'";
        
        System.out.println(squery);
        */
        /*
        Query q = getEntityManager().createQuery(squery);
        List<Cliente> clientes = q.getResultList();*/
        /*
        DAOCliente daocli = new DAOCliente();
        daocli.filtroClientes(null, "Jose", "Sand", "DNI", null);
        */
        //new CargadorDatosCobertura().agregarCoberturas();
        
        new App().inicioApp();
        
        //new App().inicioApp();
 
    }
}
