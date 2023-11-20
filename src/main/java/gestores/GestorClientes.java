package gestores;
import logica.Cliente;
import dto.ClienteDTO;
import java.util.*;
import daos.DAOCliente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class GestorClientes {
	
    public GestorClientes() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Boolean validarFormato(ClienteDTO cliente) {
            //Validamos formatos
            //Nombre y apellido hace falta?
            //Nro cliente que tenga el formato, lo podemos hacer desde la entrada tambien
            //Num doc, es dificil, no todos los tipos de documentos son iguales.
            
            
            //si el formato es correcto, pasamos a crear el cliente y consultar a la base de datos
            
            //Si el formato es incorrecto devolvemos un mensaje de error
            
            return true;
    }

    public Cliente newCliente(ClienteDTO cliente) {
        
        Cliente clienteNuevo = new Cliente();
        
        clienteNuevo.setNombre(cliente.getNombre());
        clienteNuevo.setApellido(cliente.getApellido());
        clienteNuevo.setNumeroDni(cliente.getNumDocumento());
        clienteNuevo.setTipodni(cliente.getTipoDocumento());
        clienteNuevo.setNumCliente(cliente.getNumCliente());

        return clienteNuevo;
    }
    
    
    
    public void validarDatosCliente(ClienteDTO cliente){
        
        
        
    }
    
    private boolean existeClienteActivo(ClienteDTO cliente){
        
        DAOCliente daocli = new DAOCliente();
        
        Cliente cli = daocli.buscarCliente(cliente.getTipoDocumento(),cliente.getNumDocumento());
        
        if(cli == null){
            return false;
        }
        
        return true; 
    }
    
    private boolean clienteMayorEdad(Date fechaNac){
        
        GestorFecha gf = new GestorFecha();
        
        if(gf.obtenerAniosCliente(fechaNac)<18){
            return false;
        }
        
        return true;
    }
    
    private boolean emailValido(String email) {
        
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        
        return result;
     }
}
