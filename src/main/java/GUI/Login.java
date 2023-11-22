/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
    //PanelTextInput contraseniaI = new PanelTextInput("",18);
    
    JPasswordField contraseniaI = new JPasswordField();
    contraseniaI.setEchoChar('●');
    Font font = new Font("Arial", Font.BOLD, 16);
    contraseniaI.setFont(font);
    Dimension preferredSize = new Dimension(30, 32);
    contraseniaI.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
    contraseniaI.setPreferredSize(preferredSize);

    JButton showButton = new JButton("○");
    showButton.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
    showButton.setBackground(Color.WHITE);
    showButton.setForeground(Color.GRAY);
    showButton.setFocusPainted(false);
    preferredSize = new Dimension(10, 32);
    showButton.setPreferredSize(preferredSize);
    
    showButton.addActionListener(new ActionListener() {
                private boolean passwordVisible = false;

                @Override
                public void actionPerformed(ActionEvent e) {
                    passwordVisible = !passwordVisible;
                    if (passwordVisible) {
                        contraseniaI.setEchoChar((char) 0); // Display characters as plain text
                    } else {
                        contraseniaI.setEchoChar('●'); // Mask characters with '*'
                    }
                }
            });
    
    usuario.setBackgroundColor(255, 255, 255,0);
    contrasenia.setBackgroundColor(255, 255, 255,0);
    
    Boton ingresar = new Boton("INGRESAR");
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.gridwidth = 2;
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
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.8;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.insets = new Insets(0,10,0,5);
    bg.add(contraseniaI, gbc);
    
    gbc.gridx = 1;
    gbc.insets = new Insets(0,5,0,10);
    gbc.weightx = 0.2;
    bg.add(showButton, gbc);
    
    gbc.gridwidth = 2;
    gbc.gridy = 4;
    gbc.weightx = 1;
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
