package daos;

import logica.Usuario;

public class DAOUsuario extends DAOAbstract<Usuario> {
    public DAOUsuario(){
        setClazz(Usuario.class);
    }
}
