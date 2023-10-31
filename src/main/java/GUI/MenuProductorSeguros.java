package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuProductorSeguros extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//panel base
	Background bg = new Background("background.jpg");
	
	CardLayout cl = new CardLayout();
	JPanel containerPanel = new JPanel();
	
	Menu menu;
	
	Boton botonAltaCliente = new Boton("DAR ALTA CLIENTE");
	Boton botonAltaPoliza = new Boton("DAR ALTA POLIZA");
	Boton botonConsultarCliente = new Boton("CONSULTAR CLIENTE");
	Boton botonPropuestasRenovacion = new Boton("PROPUESTAS RENOVACION");
	Boton botonFactores = new Boton("ACTUALIZAR FACTORES CARACTERISTICAS");
	JLabel titulo = new JLabel();	

	//pantallas de este menu
	JPanel pantallaAltaPoliza = new AltaPoliza(this);	

	
	MenuProductorSeguros(Menu frame){
		
		menu = frame;
		
		tittleConfig(titulo,"titulo_productorSeguros");
		
		//configuro botones
		botonAltaPoliza.addActionListener((ActionEvent e) -> {
			pantallaAltaPoliza.removeAll();
			pantallaAltaPoliza = new AltaPoliza(this);
		    containerPanel.add(pantallaAltaPoliza, "2");
			cambiarPantalla("2");
        });
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//agrego titulo
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        //titulo.setPreferredSize(new Dimension(200, 50)); 
        bg.add(titulo,gbc);
        
        //agrego botones
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 35, 5, 35);
		gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
		bg.add(botonAltaCliente,gbc);
		
		gbc.gridx = 1;
		bg.add(botonAltaPoliza,gbc);
		
        gbc.insets = new Insets(45, 35, 5, 35);
		gbc.gridy = 2;
		gbc.gridx = 0;
		bg.add(botonConsultarCliente,gbc);
		
		gbc.gridx = 1;
		bg.add(botonPropuestasRenovacion,gbc);
		
		gbc.insets = new Insets(45, 95, 45, 95);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		bg.add(botonFactores,gbc);
		
		//para rezise del titulo
		menu.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the new frame size
                Dimension newSize = e.getComponent().getSize();
                
                // Set the label's size to match the frame size
                titulo.setPreferredSize(newSize);
                
                // Repaint the label
                titulo.revalidate();
            }
        });

		
		//configurar panel container
	    containerPanel.setLayout(cl);
	    containerPanel.setPreferredSize(this.getSize());
	    containerPanel.add(bg, "1");
	    cambiarPantalla("1");
		
		//agregar mainPanel
		GridBagConstraints gbc0 = new GridBagConstraints();
		gbc0.weightx = 1;
	    gbc0.weighty = 1;
	    gbc0.anchor = GridBagConstraints.CENTER;
	    gbc0.fill = GridBagConstraints.BOTH;
		this.setLayout(new GridBagLayout());
		this.add(containerPanel,gbc0);
		
		
	}
		
	private void tittleConfig(JLabel titulo, String path) {
		
		// Load the original image
        BufferedImage imagenOriginal = null;
        try {
        	imagenOriginal = ImageIO.read(new File("titulo_productorSeguros.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create a scaled version of the image
        int newWidth = (int) (imagenOriginal.getWidth()/1.3); // Set the new width
        int newHeight = (int) (imagenOriginal.getHeight()/1.3); // Set the new height
        // Scale the image to the new size
        Image imagenEscalada = imagenOriginal.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        // Create a new ImageIcon from the scaled image
        ImageIcon icon = new ImageIcon(imagenEscalada);
		
		//tittle
		titulo.setIcon(icon);
		
		return;
		
	}
	
	void cambiarPantalla(String pantalla) {
		//cambia el panel visible
		cl.show(containerPanel, pantalla);
	}
}
