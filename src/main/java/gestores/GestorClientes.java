package gestores;
import daos.DAOCliente;
import logica.Cliente;
import dto.ClienteDTO;
import dto.DomicilioDTO;
import java.util.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import logica.Domicilio;
import logica.EstadoCliente;
import persistenciajpa.ClienteJpaController;
import util.ConversorEnum;

public class GestorClientes {
	
    public GestorClientes() {
            super();
            // TODO Auto-generated constructor stub
    }
    
    public Cliente buscarCliente(ClienteDTO cdto){

        List<Cliente> clientesBBDD = new ArrayList<>();
        
        try{
            DAOCliente daocli = new DAOCliente();
            clientesBBDD = daocli.obtenerClientePorNumCliente(cdto.getNumCliente());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
       
        
        return clientesBBDD.get(0);
    }

    
    public List<ClienteDTO> obtenerClientePorParametros(String numCliente,String nombre, String apellido, String tipoDoc, String numDoc){
        
        DAOCliente daocli = new DAOCliente();
        List<Cliente> clientesBBDD;
        
        //Si hay num cliente como es unico busca por eso unicamente
        
        if(!(numCliente.isEmpty())){
            clientesBBDD = daocli.obtenerClientePorNumCliente(numCliente);
            return listaClienteADTO(clientesBBDD);
        }
        
        //Si todo es nulo entonces obtiene todos
 
        if(numCliente.isEmpty() && nombre.isEmpty() && apellido.isEmpty() && numDoc.isEmpty() && tipoDoc.equals("Cualquiera")){
            clientesBBDD = daocli.obtenerAllClientes();
            return listaClienteADTO(clientesBBDD);
        }
        
        
        clientesBBDD = daocli.filtroClientes(numCliente, nombre, apellido, tipoDoc, numDoc);
        
        return listaClienteADTO(clientesBBDD);
        
    }

    public Cliente crearCliente(ClienteDTO cliente) {
        
        GestorDomicilio gesDom = new GestorDomicilio();
        Domicilio domicilio = gesDom.crearDomicilio(cliente.getDomicilioDTO());
        
        //VALIDAR QUE NO EXISTA UN CLIENTE YA (METODO EN PROCESO)
        
        Cliente cNuevo = new Cliente();
        
        //Atributos de cliente
        cNuevo.setNumCliente(generarNumeroCliente(cliente.getDomicilioDTO().getLocalidad().getProvincia().getPais().getId()));
        cNuevo.setCuil(cliente.getNroCuil());
        cNuevo.setCondicionIva(ConversorEnum.convertirStringIva(cliente.getCondicionIva()));
        cNuevo.setEstadoCliente(EstadoCliente.ACTIVO);
        cNuevo.setProfesion(cliente.getProfesion());
        cNuevo.setDomicilio(domicilio);
        
        //Atributos de persona
        
        cNuevo.setNombre(cliente.getNombre());
        cNuevo.setApellido(cliente.getApellido());
        cNuevo.setAnioRegistro(cliente.getAnioRegistro());
        cNuevo.setTipodni(ConversorEnum.convertirStringTipoDocumento(cliente.getTipoDocumento()));
        cNuevo.setNumeroDni(cliente.getNumDocumento());
        cNuevo.setSexo(ConversorEnum.convertirStringSexo(cliente.getSexo()));
        cNuevo.setEstadoCivil(ConversorEnum.convertirStringEstadoCivil(cliente.getEstadoCivil()));
        cNuevo.setEmail(cliente.getCorreoElectronico());
        cNuevo.setFechaNacimiento(cliente.getFechaNacimiento());
        
        //Subir a BBDD
        DAOCliente daocli = new DAOCliente();
        daocli.guardarCliente(cNuevo);
        
        return cNuevo;
    }
    
    private boolean datosCompletos(ClienteDTO cliente){
        //TODO HACER
        return true;
    }
    
    public int validarDatosCliente(ClienteDTO cliente){
        
        //El retorno es 1 cuando hay otro cliente con el dni y tipo dni igual
        if(existeClienteActivo(cliente)){
            return 1;
        }
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
    
    public boolean existeClienteActivo(ClienteDTO cliente){ 
        
        DAOCliente daocli = new DAOCliente();
        
        //Arma la query solo con el numdoc y tipo doc.
        
        List<Cliente> clientesBBDD = daocli.filtroClientes("", "", "", cliente.getTipoDocumento(), cliente.getNumDocumento());
        
        //Retorna --> si es vacio entonces no existe cliente activo.
        
        return !clientesBBDD.isEmpty(); 
    }
    
    public boolean clienteMayorEdad(Date fechaNac){
        
        GestorFecha gf = new GestorFecha();
        
        return gf.obtenerAnios(fechaNac) >= 18;
    }
    
    public boolean emailValido(String email) {
        
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        
        return result;
     }
    
    private String generarNumeroCliente(int idPais){
        
        String numCliente;
        
        DAOCliente dc = new DAOCliente();
        
        //METODO GENERAR NUMERO PROVISORIO
        
        if(idPais>9){
            numCliente = idPais + "-" + generarNumero();
        }
        else{
            numCliente = "0" + idPais + "-" + generarNumero();
        }
        
        return numCliente;
    }
    
    private String generarNumero(){
        
        Random rand = new Random();
        
        String numero = rand.nextInt(10000000, 99999999) +"";
        
        return numero;
    }
    
    public List<Cliente> obtenerClientes(){
        DAOCliente daoc = new DAOCliente();
        return daoc.obtenerAllClientes();
    }
    
    private ClienteDTO clienteADTO(Cliente c){
        
        ClienteDTO cdto = new ClienteDTO();
        
        cdto.setNroCuil(c.getCuil());
        cdto.setNumCliente(c.getNumCliente());
        cdto.setNumDocumento(c.getNumeroDni());
        cdto.setNombre(c.getNombre());
        cdto.setApellido(c.getApellido());
        cdto.setTipoDocumento(c.getTipodni().toString());
        cdto.setDomicilioDTO(domicilioADTO(c.getDomicilio()));
        
        return cdto;
    }
    
    private DomicilioDTO domicilioADTO(Domicilio d){
        
        DomicilioDTO domdto = new DomicilioDTO();
        domdto.setCalle(d.getCalle());
        domdto.setNumero(d.getNumeroCalle());
        return domdto;
        
    }
    
    private List<ClienteDTO> listaClienteADTO(List<Cliente> listaClientes){
        
        List<ClienteDTO> listaDTO =  new ArrayList<>();
        
        for(Cliente c: listaClientes){
            listaDTO.add(clienteADTO(c));
        }
        
        return listaDTO;
        
    }
}
