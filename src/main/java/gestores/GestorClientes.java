package gestores;
import logica.Cliente;
import dto.ClienteDTO;
import java.util.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import logica.TipoDocumento;

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

        return clienteNuevo;
    }
    
    private boolean datosCompletos(ClienteDTO cliente){
        return true;
    }
    
    public int validarDatosCliente(ClienteDTO cliente){
        //El retorno es 1 cuando hay otro cliente con el dni y tipo dni igual
        
        //El retorno es 2 cuando no coincide el cuil con el dni
        if(!validarCuilDNI(cliente.getNroCuil(), cliente.getNumDocumento())){
            return 2;
        }
        if(!clienteMayorEdad(cliente.getFechaNacimiento())){
            return 3;
        }
        if(!emailValido(cliente.getCorreoElectronico())){
            return 4;
        }
        //el retorno 0 significa ausencia de error
        return 0;
    }
    
    private boolean validarCuilDNI(String cuil, String dni){
        return cuil.contains(dni);
    }
    
    private boolean existeClienteActivo(ClienteDTO cliente){
        
        //TODO Provisorio 
        
        GestorPersona gp = new GestorPersona();
        
        if(gp.buscarPersonas(cliente.getTipoDocumento(), cliente.getNumDocumento()).isEmpty()){
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
    
    /*public List<Persona> filterClientesByNumeroDNI(List<Persona> personaList, String numDNI) {
        return clienteList.stream()
                .filter(cliente -> cliente.getNumeroDni().equals(numDNI))
                .toList();
    }
    
    public List<Cliente> filterClientesByTipoDNI(List<Cliente> clienteList, TipoDocumento tipoDoc) {
        return clienteList.stream()
                .filter(cliente -> cliente.getTipodni().equals(tipoDoc))
                .toList();
    }*/
}
