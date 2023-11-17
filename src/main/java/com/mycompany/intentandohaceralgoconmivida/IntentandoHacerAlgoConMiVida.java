package com.mycompany.intentandohaceralgoconmivida;

import GUI.App;
import daos.DAOPais;
import java.util.Optional;
import logica.Pais;
import logica.Provincia;
import util.EntityManagerUtil;

public class IntentandoHacerAlgoConMiVida {
    
    public static void main(String[] args){
        /*App aplicacion = new App();
        aplicacion.inicioApp();*/
        
        DAOPais daop = new DAOPais();
        
        Optional<Pais> p = daop.get(1);
        System.out.println(p.get().toString());
        

    }  
}
