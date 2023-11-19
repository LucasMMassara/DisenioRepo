/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author valec
 */
public class Login extends JPanel{
    
    Login(Menu frame){
        
    //panel base
    Background bg = new Background("background.jpg");
    
    PanelText usuario = new PanelText("Usuario: ", "BOLD",18, "SOUTH");
    PanelText contrasenia = new PanelText("Contraseña: ","BOLD",18,"SOUTH");
    PanelTextInput usuarioI = new PanelTextInput("",18);
    PanelTextInput contraseniaI = new PanelTextInput("",18);
    
    usuario.setBackgroundColor(255, 255, 255,0);
    contrasenia.setBackgroundColor(255, 255, 255,0);
    
    Boton ingresar = new Boton("INGRESAR");
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    bg.add(usuario,gbc);
    
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTH;
    bg.add(usuarioI,gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    bg.add(contrasenia, gbc);
    
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTH;
    bg.add(contraseniaI, gbc);
    
    gbc.gridy = 4;
    gbc.gridx = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;
    bg.add(ingresar,gbc);
    
    ingresar.addActionListener((ActionEvent e) -> {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Gestión de Logística");
        frame.setSize(1100, 720); // Set your preferred size
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setMinimumSize(new Dimension(1000, 700));
        frame.cambiarPantalla("productorSeguros");
    });
    
    GridBagConstraints gbc0 = new GridBagConstraints();
    gbc0.weightx = 1;
    gbc0.weighty = 1;
    gbc0.fill = GridBagConstraints.BOTH;
    
    this.setLayout(new GridBagLayout());
    this.add(bg,gbc0);
    
        
        
    }
    
    
    
}
