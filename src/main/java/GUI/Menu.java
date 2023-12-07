package GUI;

import gestores.GestorUsuario;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;

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
        JPanel login = new Login(this);
        
        GestorUsuario gestorUsuario = new GestorUsuario();
	
	Menu(){

		    //cambio icono ventana
		    ImageIcon customIcon = new ImageIcon("logo.png");
		    this.setIconImage(customIcon.getImage());
		    
		    //configurar panel container
		    containerPanel.setLayout(cl);
		    containerPanel.setPreferredSize(this.getSize());
                    
                    containerPanel.add(login, "1");
		    containerPanel.add(menuProductorSeguros, "productorSeguros");
                    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    this.setTitle("Inicio de sesion");
                    this.setMinimumSize(new Dimension(300, 400));   
                    this.setSize(300, 400); // Set your preferred size
                    this.setLocationRelativeTo(null); // Center the frame on the screen
		    cambiarPantalla("1");

		    this.setContentPane(containerPanel);
                    this.setVisible(true);
		    
	}

	void cambiarPantalla(String pantalla) {
		//cambia el panel visible
		cl.show(containerPanel, pantalla);
	}
	
        void setUsuario(String usuario, String con){
            gestorUsuario.setUsuario(usuario, con);
        }
        
        int getCantidadClientesBusqueda(){
            
            if(gestorUsuario.getUsuarioLogueado().getCu() == null){
                return -1;
            }
            else{
               return gestorUsuario.getUsuarioLogueado().getCu().getCantClientesBusqueda();
            }
        }
        
        void actualizarCantidadClientesBusqueda(int cantidad){
            
            gestorUsuario.actualizarCantidadClientesBusqueda( cantidad);
            
        }
        
        public String obtenerNombreAgente(){
            return "TO DO";
        }
        public String obtenerCorreoElectronico(){
            return "TO DO";
        }
        public String obtenerTelefono(){
            return "TO DO";
        }
        
        public String obtenerNroSucursal(){
            return gestorUsuario.getUsuarioLogueado().getSucursal().getNumeroSucursal();
        }
        
}

