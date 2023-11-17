package gestores;
import logica.Cliente;
import dto.ClienteDTO;
import java.util.*;
import daos.DAOCliente;

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
}
