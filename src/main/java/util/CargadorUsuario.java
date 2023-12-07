package util;

import daos.DAOSucursal;
import daos.DAOUsuario;
import logica.ConfiguracionesUsuario;
import logica.RolUsuario;
import logica.Sucursal;
import logica.Usuario;

public class CargadorUsuario {
    
    public static void cargar(){
        Usuario u = new Usuario();
        //Productor seguros
        u.setRol(RolUsuario.PRODUCTORSEGUROS);
        u.setUsuario("Luki2000");
        u.setContrasenia("123456aA");
        u.setCu(generarConfig());
        u.setSucursal(cargarSucursal());
        u.setNombre("James");
        u.setApellido("Bond");
        u.setEmail("jb007@gmail.com");
        u.setNumTelefono("34250073821");
        (new DAOUsuario()).save(u);
    }
    
    private static ConfiguracionesUsuario generarConfig(){
        
        ConfiguracionesUsuario cu = new ConfiguracionesUsuario(10);
        
        return cu;
    }
    
    private static Sucursal cargarSucursal(){
        Sucursal suc = new Sucursal("Casa central","0001");
        (new DAOSucursal()).save(suc);
        return (new DAOSucursal()).get(1).get();
    }
    
}
