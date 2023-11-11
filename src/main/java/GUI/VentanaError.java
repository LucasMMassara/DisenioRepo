/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author valec
 */
public class VentanaError extends JFrame{
    
    VentanaError(String mensajeError, String descripcion, String nombreVentana){
        
    //armado ventana
    this.setTitle(nombreVentana);
    this.setSize(500, 350); // Set your preferred size
    this.setLocationRelativeTo(null); // Center the frame on the screen
    this.setMinimumSize(new Dimension(450, 300));

    //cambio icono ventana
    ImageIcon customIcon = new ImageIcon("logo.png");
    this.setIconImage(customIcon.getImage());
    
    JPanel panel = new JPanel(new GridBagLayout());
    Background fondo = new Background("background.jpg");
    PanelText errorLabel = new PanelText(mensajeError,19);
    Boton botonAceptar = new Boton("Aceptar", 19);    
    
    botonAceptar.addActionListener((ActionEvent e) -> {
        dispose();
    });
    
    GridBagConstraints gbc = new GridBagConstraints();
    
    errorLabel.setBackgroundColor(0,0,0,0);
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 0.45;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10);
    panel.add(errorLabel, gbc);

    //descripcion error
    JTextArea textArea = new JTextArea();
    textArea.setText(descripcion);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true); // Wrap at word boundaries
    textArea.setEditable(false); // Make it non-editable
    textArea.setBackground(new Color(255,255,255)); // Make the background transparent
    textArea.setFocusable(false); // Make it not selectable
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 0.45;
    gbc.gridy = 1;
    panel.add(textArea, gbc);
    
    gbc.weighty = 0.1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.gridy = 2;    
    panel.add(botonAceptar, gbc);
    
    panel.setOpaque(false);  
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    fondo.add(panel,gbc);
    this.setContentPane(fondo);
    this.setVisible(true);
    }
    
    VentanaError(String mensajeError, String nombreVentana){
        
    //armado ventana
    this.setTitle(nombreVentana);
    this.setSize(700, 250); // Set your preferred size
    this.setLocationRelativeTo(null); // Center the frame on the screen
    this.setMinimumSize(new Dimension(650, 200));

    JPanel panel = new JPanel(new GridBagLayout());
    Background fondo = new Background("background.jpg");
    PanelText errorLabel = new PanelText(mensajeError,19);
    Boton botonAceptar = new Boton("Aceptar", 19);    
    JPanel empty = new JPanel();
    
    botonAceptar.addActionListener((ActionEvent e) -> {
        dispose();
    });
    
    GridBagConstraints gbc = new GridBagConstraints();
    
    empty.setOpaque(false);
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10);
    panel.add(empty, gbc);
    
    errorLabel.setBackgroundColor(0,0,0,0);
    gbc.gridy = 1;    
    panel.add(errorLabel, gbc);
    
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.gridy = 2;    
    panel.add(botonAceptar, gbc);
    
    panel.setOpaque(false);  
    gbc.gridheight = 1;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    fondo.add(panel,gbc);
    this.setContentPane(fondo);
    this.setVisible(true);
    }
    
}
