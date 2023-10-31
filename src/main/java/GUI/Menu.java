package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//panel container de los demas paneles
	JPanel containerPanel = new JPanel();
	CardLayout cl = new CardLayout();
	
	//paneles
	JPanel menuProductorSeguros = new MenuProductorSeguros(this);	
	
	Menu(){
			
			//armado ventana
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Gestión de Logística");
		    this.setSize(1100, 720); // Set your preferred size
		    this.setLocationRelativeTo(null); // Center the frame on the screen
		    this.setMinimumSize(new Dimension(1000, 700));
		    
		    //cambio icono ventana
		    //ImageIcon customIcon = new ImageIcon("LogoApp.png");
		    //this.setIconImage(customIcon.getImage());
		    
		    //configurar panel container
		    containerPanel.setLayout(cl);
		    containerPanel.setPreferredSize(this.getSize());
		    containerPanel.add(menuProductorSeguros, "1");
		    cambiarPantalla("1");

		    this.setContentPane(containerPanel);
			//this.pack();
			this.setVisible(true);
		    //this.revalidate();
		    
	}

	void cambiarPantalla(String pantalla) {
		//cambia el panel visible
		cl.show(containerPanel, pantalla);
	}
	
	
}

