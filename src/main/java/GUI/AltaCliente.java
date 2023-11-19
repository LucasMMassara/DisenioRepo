/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author valec
 */
public class AltaCliente extends JPanel  {

    MenuProductorSeguros main;

    CardLayout cl = new CardLayout();
    JPanel containerPanel = new JPanel();
    
    Background primera = new Background("background.jpg");
    Background segunda = new Background("background.jpg");
    
    
    
    AltaCliente(MenuProductorSeguros menu) {

        main = menu;

        primeraConfig();
        segundaConfig();
        /*
        terceraConfig();
        cuartaConfig();
        quintaConfig();
        pdfConfig();
        */

        //configurar panel container
        containerPanel.setLayout(cl);
        containerPanel.setPreferredSize(this.getSize());
        containerPanel.add(primera, "1");
        containerPanel.add(segunda, "2");
        /*
        containerPanel.add(tercera, "3");
        containerPanel.add(cuarta, "4");
        containerPanel.add(quinta, "5");
        containerPanel.add(pdf, "6");
        */
        cambiarPantalla("1");

        //agregar mainPanel
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.weightx = 1;
        gbc0.weighty = 1;
        gbc0.anchor = GridBagConstraints.CENTER;
        gbc0.fill = GridBagConstraints.BOTH;
        this.setLayout(new GridBagLayout());
        this.add(containerPanel, gbc0);

    }
    
    AltaCliente(AltaPoliza menu) {

        primera = new Background("background.jpg");
        AltaClientePrimera primeraPanel = new AltaClientePrimera(menu);
        
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.weightx = 1;
        gbc0.weighty = 1;
        gbc0.fill = GridBagConstraints.BOTH;
        
        primera.add(primeraPanel, gbc0);
        segundaConfig();
        /*
        terceraConfig();
        cuartaConfig();
        quintaConfig();
        pdfConfig();
        */

        //configurar panel container
        containerPanel.setLayout(cl);
        containerPanel.setPreferredSize(this.getSize());
        containerPanel.add(primera, "1");
        containerPanel.add(segunda, "2");
        /*
        containerPanel.add(tercera, "3");
        containerPanel.add(cuarta, "4");
        containerPanel.add(quinta, "5");
        containerPanel.add(pdf, "6");
        */
        cambiarPantalla("1");

        //agregar mainPanel
        gbc0.weightx = 1;
        gbc0.weighty = 1;
        gbc0.anchor = GridBagConstraints.CENTER;
        gbc0.fill = GridBagConstraints.BOTH;
        this.setLayout(new GridBagLayout());
        this.add(containerPanel, gbc0);

    }
    
    private void primeraConfig(){
        primera = new Background("background.jpg");
        AltaClientePrimera primeraPanel = new AltaClientePrimera(main);
        
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.weightx = 1;
        gbc0.weighty = 1;
        gbc0.fill = GridBagConstraints.BOTH;
        
        primera.add(primeraPanel, gbc0);
    }
    
    private void segundaConfig(){
        
    }
    
    void cambiarPantalla(String pantalla) {
        //cambia el panel visible
        cl.show(containerPanel, pantalla);
    }
    
}
