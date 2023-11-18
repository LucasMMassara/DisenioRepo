/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridBagConstraints;

/**
 *
 * @author valec
 */
public class borrar {
    
    borrar(){
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        Boton cancelar = new Boton("Cancelar",18);
        Boton confirmar = new Boton("Confirmar",18);
        
        PanelText titulo = new PanelText("Alta Cliente", 18);
        //.add(titulo,gbc);
        
        
        
    }

}
