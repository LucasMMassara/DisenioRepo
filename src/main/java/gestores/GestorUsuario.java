/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOUsuario;
import logica.Usuario;

/**
 *
 * @author Lucas
 */
public class GestorUsuario {
    
    private static Usuario usuarioLogueado = null;

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    private static void setUsuarioLogueado(Usuario usuarioLogueado) {
        GestorUsuario.usuarioLogueado = usuarioLogueado;
    }
    
    public GestorUsuario(Usuario usuarioLogueado){
        super();
        if(GestorUsuario.getUsuarioLogueado() == null){
            setUsuarioLogueado(usuarioLogueado);
            
        }
    }
    
    public void setUsuario(String usuario, String contra){
        Usuario usuarioLogeado = new DAOUsuario().get(1).get();
        setUsuarioLogueado(usuarioLogeado);
    }

    public GestorUsuario() {
    }

    public void actualizarCantidadClientesBusqueda(int cantidad) {
        
        usuarioLogueado.getCu().setCantClientesBusqueda(cantidad);
        new DAOUsuario().update(usuarioLogueado);
        
    }
    
    
    
    
    
    
}
