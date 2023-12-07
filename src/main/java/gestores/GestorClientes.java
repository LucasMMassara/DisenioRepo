package gestores;
import daos.DAOCliente;
import logica.Cliente;
import dto.ClienteDTO;
import dto.DomicilioDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import logica.Cuota;
import logica.Domicilio;
import logica.EstadoCliente;
import logica.Poliza;
import util.ConversorEnum;
import util.SubsistemaSiniestros;

public class GestorClientes {
	
    public GestorClientes() {
            super();
            // TODO Auto-generated constructor stub
    }
    
    public Cliente buscarCliente(ClienteDTO cdto){
        
        DAOCliente daocli = new DAOCliente();
        Cliente clienteBBDD = daocli.obtenerClientePorNumCliente(cdto.getNumCliente());
        
        return clienteBBDD;
    }

    
    public List<ClienteDTO> obtenerClientePorParametros(String numCliente,String nombre, String apellido, String tipoDoc, String numDoc){
        
        DAOCliente daocli = new DAOCliente();
        List<Cliente> clientesBBDD = new ArrayList();
        
        //Si hay num cliente como es unico busca por eso unicamente
        
        if(!(numCliente.isEmpty())){
            Cliente clienteBBDD = daocli.obtenerClientePorNumCliente(numCliente);
            clientesBBDD.add(clienteBBDD);
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
        
        int idPais = new GestorPais().obtenerIdPais(cliente.getDomicilioDTO().getLocalidad().getId());
        
        //Atributos de cliente
        cNuevo.setNumCliente(generarNumeroCliente(idPais));
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
        cNuevo.setFechaCreacion(new Date());
        
        //Subir a BBDD
        DAOCliente daocli = new DAOCliente();
        daocli.save(cNuevo);
        
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
        
        return gf.obtenerAniosDesde(fechaNac) >= 18;
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
        return daoc.getAll();
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
    
    public void ActualizarConsideracionCliente(int idCliente){
        
        DAOCliente daocli = new DAOCliente();
        Cliente c = daocli.get(idCliente).get();
        
        if(condicionEstadoPlata(c)){ //Condicion
            c.setEstadoCliente(EstadoCliente.PLATA);
        }
        else{
            c.setEstadoCliente(EstadoCliente.NORMAL);
        }
        
        daocli.update(c);
    }

    private boolean condicionEstadoPlata(Cliente c) {
        return (!tieneSiniestosUltimoAnio(c) && !tieneCuotasImpagas(c) && dosAniosActivo(c));
    }

    private boolean tieneSiniestosUltimoAnio(Cliente c) {
       
        String cantSiniestros = new SubsistemaSiniestros().obtenerCantSiniestros(c.getTipodni().toString(),c.getNumeroDni());
        
        return(cantSiniestros != "CERO");
        
    }

    private boolean tieneCuotasImpagas(Cliente c) {
        
        //obtener polizas cliente
        GestorPoliza gp = new GestorPoliza();
        
        List<Poliza> listaPolizas =  gp.obtenerPolizasCliente(c.getId());
        
        //obtener cuotas
        List<Cuota> cuotasImpagas = new GestorCuotas().obtenerCuotasImpagas(listaPolizas);
        
        //chequear si tiene impagas
        return(!cuotasImpagas.isEmpty());
        
    }

    private boolean dosAniosActivo(Cliente c) {     
       int anios =  new GestorFecha().obtenerAniosDesde(c.getFechaCreacion());
       return anios>=2 && c.getEstadoCliente()!=EstadoCliente.ELIMINADO;
    }
}
