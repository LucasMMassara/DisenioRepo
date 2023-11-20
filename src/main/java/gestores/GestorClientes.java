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

    public Boolean validarFormato(ClienteDTO cliente) {
            //Validamos formatos
            //Nombre y apellido hace falta?
            //Nro cliente que tenga el formato, lo podemos hacer desde la entrada tambien
            //Num doc, es dificil, no todos los tipos de documentos son iguales.
            
            
            //si el formato es correcto, pasamos a crear el cliente y consultar a la base de datos
            
            //Si el formato es incorrecto devolvemos un mensaje de error
            
            return true;
    }
    
    public List<ClienteDTO> obtenerClientePorParametros(String numCliente,String nombre, String apellido, String tipoDoc, String numDoc){
        
        List<Cliente> clientesBBDD = obtenerClientes();
        
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
        ClienteJpaController cjpa = new ClienteJpaController();
        return cjpa.findClienteEntities();
    }
    
    private ClienteDTO clienteADTO(Cliente c){
        
        ClienteDTO cdto = new ClienteDTO();
        
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
