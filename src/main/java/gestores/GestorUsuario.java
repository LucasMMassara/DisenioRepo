/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

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
    
    
}
