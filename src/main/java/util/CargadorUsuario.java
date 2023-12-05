/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import daos.DAOSucursal;
import daos.DAOUsuario;
import logica.ConfiguracionesUsuario;
import logica.RolUsuario;
import logica.Sucursal;
import logica.Usuario;

/**
 *
 * @author Lucas
 */
public class CargadorUsuario {
    
    public static void cargar(){
        Usuario u = new Usuario();
        //Productor seguros
        u.setRol(RolUsuario.PRODUCTORSEGUROS);
        u.setUsuario("Luki2000");
        u.setContrasenia("123456aA");
        u.setCu(generarConfig());
        u.setSucursal(cargarSucursal());
        (new DAOUsuario()).save(u);
    }
    
    private static ConfiguracionesUsuario generarConfig(){
        
        ConfiguracionesUsuario cu = new ConfiguracionesUsuario(10);
        
        return cu;
    }
    
    private static Sucursal cargarSucursal(){
        Sucursal suc = new Sucursal("Casa central","01");
        (new DAOSucursal()).save(suc);
        return (new DAOSucursal()).get(1).get();
    }
    
}
