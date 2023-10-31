package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class BuscarCliente extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean selected = false;
	Boton seleccionar = new Boton("Seleccionar");
	PanelCliente clienteSeleccionado;

	
	BuscarCliente(AltaPoliza main){
		
		Background fondo = new Background("background.jpg");
		
		this.setLayout(new GridBagLayout());
		
		JPanel busqueda = new JPanel();
		JPanel resultado = new JPanel();
		JLabel empty = new JLabel("");
		
		Boton cancelar = new Boton("Cancelar");
		
		busqueda.setBackground(new Color(240,240,240));
		resultado.setBackground(new Color(255,255,220));

		GridBagConstraints gbc = new GridBagConstraints();
		
		busquedaConfig(busqueda);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
        gbc.insets = new Insets(10, 10, 5, 10);
        fondo.add(busqueda,gbc);
		
		resultadoConfig(resultado);
		gbc.gridy = 1;
		gbc.weighty = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        fondo.add(resultado,gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		gbc.weighty = 0.05;
		gbc.weightx = 0.2;
        gbc.insets = new Insets(0, 10, 0, 0);
        fondo.add(cancelar,gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.weightx = 0.6;
        gbc.insets = new Insets(0, 0, 0, 0);
        fondo.add(empty,gbc);
		
    	seleccionar.setEnabled(false);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 2;
		gbc.weightx = 0.2;
        gbc.insets = new Insets(0, 0, 0, 10);
        fondo.add(seleccionar,gbc);
		
        gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
		this.add(fondo,gbc);
		
		cancelar.addActionListener((ActionEvent e) -> {
			main.cambiarPantalla("1");
	    });
		
		seleccionar.addActionListener((ActionEvent e) -> {
			main.cambiarPantalla("1");
	    });
		
		return;
	}
	
	private void busquedaConfig(JPanel busqueda) {
		
		busqueda.setLayout(new GridBagLayout());
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);
		busqueda.setBorder(border);
		
		//String text, String fontType, int fontSize, String position
		PanelText busquedaCliente = new PanelText("Busqueda cliente", "BOLD", 20, "WEST");
		PanelText nroCliente = new PanelText("Numero cliente", "PLAIN", 18, "SOUTHWEST");
		PanelText apellido= new PanelText("Apellido", "PLAIN", 18, "SOUTHWEST");
		PanelText nombre = new PanelText("Nombre", "PLAIN", 18, "SOUTHWEST");
		PanelText tipoDocumento = new PanelText("Tipo documento", "PLAIN", 18, "SOUTHWEST");
		PanelText nroDocumento = new PanelText("Numero documento", "PLAIN", 18, "SOUTHWEST");

		PanelTextInput nroClienteI = new PanelTextInput(16);
		PanelTextInput apellidoI = new PanelTextInput(16);
		PanelTextInput nombreI = new PanelTextInput(16);
		PanelTextInput nroDocumentoI = new PanelTextInput(16);
		
		String[] items = {"Opcion1", "Opcion2", "Opcion3"};
		PanelDropDown tipoDocumentoI = new PanelDropDown(items);

		Boton limpiar = new Boton("Limpiar");
		Boton buscar = new Boton("Buscar");
		
		limpiar.addActionListener((ActionEvent e) -> {
			
        });
        
		buscar.addActionListener((ActionEvent e) -> {
			
        });
		
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
        gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        busqueda.add(busquedaCliente, gbc);
        
        gbc.gridy = 1;
        busqueda.add(nroCliente,gbc);
        
        gbc.gridx = 1;
        busqueda.add(nombre,gbc);
		
        gbc.gridx = 2;
        busqueda.add(apellido,gbc);

        gbc.gridx = 3;
        busqueda.add(tipoDocumento,gbc);
        
        gbc.gridx = 4;
        busqueda.add(nroDocumento,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        busqueda.add(nroClienteI,gbc);

        gbc.gridx = 1;
        busqueda.add(apellidoI,gbc);
        
        gbc.gridx = 2;
        busqueda.add(nombreI,gbc);
        
        gbc.gridx = 3;
        busqueda.add(tipoDocumentoI,gbc);
        
        gbc.gridx = 4;
        busqueda.add(nroDocumentoI,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 5);
        busqueda.add(limpiar,gbc);
        
        gbc.insets = new Insets(10, 5, 10, 10);
        gbc.gridx = 4;
        busqueda.add(buscar,gbc);
        
        
        
		return;
	}
	
	private void resultadoConfig(JPanel resultado) {
		
		resultado.setLayout(new GridBagLayout());		
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);
		resultado.setBorder(border);
		
		PanelText resultadoBusqueda = new PanelText("Resultado busqueda", "BOLD", 20, "WEST");

		JPanel lista = new JPanel(new GridBagLayout());
		lista.setBackground(Color.DARK_GRAY);
		
        GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		resultado.add(resultadoBusqueda,gbc);
		
		
		JScrollPane jsp = new JScrollPane(lista);
		
		gbc.gridy = 1;
        gbc.weighty = 0.95;
        listaConfig(lista);
        
        
        
		resultado.add(jsp,gbc);

		
		return;
	}
	
	private void listaConfig(JPanel lista) {
		
		lista.setLayout(new GridBagLayout());	
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
	
		PanelCliente panel = new PanelCliente();
		gbc.gridy = 0;
		lista.add(panel,gbc);
		
		for(int i=1; i < 30; i++) {
			
			panel = new PanelCliente(this, i);
			gbc.gridy = i;
			lista.add(panel,gbc);
			
		}
		
		
		return;
	}
	
	Boolean isSelected() {
		return selected;
	}
	
	void setSelected(Boolean bool) {
		selected = bool;
		
		if(bool) {
			seleccionar.setEnabled(bool);
		}
		else {
			seleccionar.setEnabled(bool);
		}
		
	}
	
	void setCliente(PanelCliente cliente) {
		
		clienteSeleccionado = cliente;
		
		return;
	}
	
	
}
