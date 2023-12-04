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

import dto.ClienteDTO;
import gestores.GestorClientes;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

public class BuscarCliente extends JPanel {

    private static final long serialVersionUID = 1L;

    private boolean selected = false;
    Boton seleccionar = new Boton("Seleccionar");
    PanelCliente clienteSeleccionado;
    AltaPoliza main;
    
    String nroCli = "";
    String nomb = "";
    String apell = "";
    String tipD = "";
    String nroD = "";

    JPanel resultado = new JPanel();
    ClienteDTO clienteEncontrado = new ClienteDTO();
    int cant = 100;
    
    BuscarCliente(AltaPoliza mainI) {

        main = mainI;
        
        Background fondo = new Background("background.jpg");

        this.setLayout(new GridBagLayout());

        JPanel busqueda = new JPanel();
        JLabel empty = new JLabel("");

        Boton cancelar = new Boton("Cancelar");

        busqueda.setBackground(new Color(240, 240, 240));

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
        fondo.add(busqueda, gbc);

        resultadoConfig(resultado);
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        fondo.add(resultado, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.weighty = 0.05;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(0, 10, 0, 0);
        fondo.add(cancelar, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        gbc.insets = new Insets(0, 0, 0, 0);
        fondo.add(empty, gbc);

        seleccionar.setEnabled(false);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(0, 0, 0, 10);
        fondo.add(seleccionar, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(fondo, gbc);

        cancelar.addActionListener((ActionEvent e) -> {
            main.cambiarPantalla("1");
        });

        seleccionar.addActionListener((ActionEvent e) -> {
            clienteEncontrado = clienteSeleccionado.getClienteDTO();
            main.actualizarPrimera(clienteEncontrado);
            main.cambiarPantalla("1");
        });

    }

    private void busquedaConfig(JPanel busqueda) {

        busqueda.setLayout(new GridBagLayout());
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);
        busqueda.setBorder(border);

        PanelText busquedaCliente = new PanelText("Busqueda cliente", "BOLD", 20, "WEST");
        PanelText nroCliente = new PanelText("Numero cliente", "PLAIN", 18, "SOUTHWEST");
        PanelText apellido = new PanelText("Apellido", "PLAIN", 18, "SOUTHWEST");
        PanelText nombre = new PanelText("Nombre", "PLAIN", 18, "SOUTHWEST");
        PanelText tipoDocumento = new PanelText("Tipo documento", "PLAIN", 18, "SOUTHWEST");
        PanelText nroDocumento = new PanelText("Numero documento", "PLAIN", 18, "SOUTHWEST");
        PanelText cantClientes = new PanelText("Cantidad de clientes a buscar:", "PLAIN", 18, "SOUTHEAST");

        PanelTextInput nroClienteI = new PanelTextInput(16);
        PanelTextInput apellidoI = new PanelTextInput(16);
        PanelTextInput nombreI = new PanelTextInput(16);
        PanelTextInput nroDocumentoI = new PanelTextInput(16);
        PanelTextInput cantClientesI = new PanelTextInput(16);

        //cant = metodo que consigue el numero seteado por el cliente
        cantClientesI.setText(cant+"");

        //configuro inputs
        nroClienteI.restrictToAlphanumerics();
        nombreI.restrictSize(40);
        apellidoI.restrictSize(70);
        nroDocumentoI.restrictToNumbers();
        nroDocumentoI.restrictSize(8);

        String[] items = {"Cualquiera","DNI","CC", "CI", "CIC"};
        PanelDropDown tipoDocumentoI = new PanelDropDown(items);

        tipoDocumentoI.addCustomPanelListener(new CustomPanelListener() {
            @Override
            public void onPanelItemSelected(PanelDropDown source, String selectedItem) {

                switch (selectedItem) {
                    case "CC":
                        nroDocumentoI.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "CI":
                        nroDocumentoI.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "CIC":
                        nroDocumentoI.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "DNI":
                        nroDocumentoI.restrictSize(8);
                        // limitar nroDocumentoI acordemente
                        break;
                }

            }
        });

        Boton limpiar = new Boton("Limpiar");
        Boton buscar = new Boton("Buscar");

        limpiar.addActionListener((ActionEvent e) -> {
            //resetear seleccion de cliente
            setSelected(false);
            if (clienteSeleccionado != null) {
                clienteSeleccionado.setSelected(false);
            }
            //limpiar campos de busqueda
            nroClienteI.setText("");
            apellidoI.setText("");
            nombreI.setText("");
            nroDocumentoI.setText("");
            //limpiarResultados
            resultadoConfig(resultado);
            resultado.revalidate();
            

        });

        buscar.addActionListener((ActionEvent e) -> {

            //obtener valores input
            nroCli = nroClienteI.getText();
            nomb = nombreI.getText();
            apell = apellidoI.getText();
            tipD = tipoDocumentoI.getSelectedItem();
            nroD = nroDocumentoI.getText();

            cant = Integer.parseInt(cantClientesI.getText());
            //actualizar valor cant maxima de clientes a buscar

            GestorClientes gc = new GestorClientes();
            List<ClienteDTO> clientes = gc.obtenerClientePorParametros(nroCli, nomb, apell, tipD, nroD);
                        
            if(clientes.isEmpty()){
                clienteNoEncontrado();
                resultadoConfig(resultado, clientes);
                resultado.revalidate();
            }
            else{
                resultadoConfig(resultado, clientes);
                resultado.revalidate();
            }         

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
        busqueda.add(nroCliente, gbc);

        gbc.gridx = 1;
        busqueda.add(nombre, gbc);

        gbc.gridx = 2;
        busqueda.add(apellido, gbc);

        gbc.gridx = 3;
        busqueda.add(tipoDocumento, gbc);

        gbc.gridx = 4;
        busqueda.add(nroDocumento, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        busqueda.add(nroClienteI, gbc);

        gbc.gridx = 1;
        busqueda.add(nombreI, gbc);

        gbc.gridx = 2;
        busqueda.add(apellidoI, gbc);

        gbc.gridx = 3;
        busqueda.add(tipoDocumentoI, gbc);

        gbc.gridx = 4;
        busqueda.add(nroDocumentoI, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
       // gbc.gridwidth = 2;
        busqueda.add(cantClientes, gbc);
        
        gbc.gridx = 1;
        busqueda.add(cantClientesI, gbc);
        
        gbc.gridx = 3;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 5);
        busqueda.add(limpiar, gbc);

        gbc.insets = new Insets(10, 5, 10, 10);
        gbc.gridx = 4;
        busqueda.add(buscar, gbc);

    }

    private void resultadoConfig(JPanel resultado) {

        resultado.removeAll();
        resultado.revalidate();
        resultado.repaint();
        
        resultado.setBackground(new Color(255, 255, 220));

        JPanel lista = new JPanel(new GridBagLayout());

        resultado.setLayout(new GridBagLayout());
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);
        resultado.setBorder(border);

        PanelText resultadoBusqueda = new PanelText("Resultado busqueda", "BOLD", 20, "WEST");

        lista.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        resultado.add(resultadoBusqueda, gbc);

        JScrollPane jsp = new JScrollPane(lista);

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        listaConfig(lista);

        resultado.add(jsp, gbc);

    }

    private void resultadoConfig(JPanel resultado, List<ClienteDTO> clientes) {

        resultado.removeAll();
        resultado.revalidate();
        resultado.repaint();

        resultado.setBackground(new Color(255, 255, 220));

        JPanel lista = new JPanel(new GridBagLayout());

        resultado.setLayout(new GridBagLayout());
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);
        resultado.setBorder(border);

        PanelText resultadoBusqueda = new PanelText("Resultado busqueda", "BOLD", 20, "WEST");

        lista.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        resultado.add(resultadoBusqueda, gbc);

        JScrollPane jsp = new JScrollPane(lista);

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        listaConfig(lista, clientes);

        resultado.add(jsp, gbc);

    }

    private void listaConfig(JPanel lista, List<ClienteDTO> clientes) {

        lista.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        PanelCliente panel = new PanelCliente();
        gbc.gridy = 0;
        lista.add(panel, gbc);
        
        int size = clientes.size();
        int maximo;
        if(size < cant){
            maximo = size;
        }
        else{
            maximo = cant;
        }
        
        JPanel list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        for (int i = 0; i < maximo; i++) {

            panel = new PanelCliente(this, i, clientes.get(i), lista.getWidth());
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            list.add(panel);

        }

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        lista.add(list, gbc);

    }

    private void listaConfig(JPanel lista) {

        lista.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        PanelCliente panel = new PanelCliente();
        gbc.gridy = 0;
        lista.add(panel, gbc);

        JPanel lista2 = new JPanel(new GridBagLayout());
        lista2.setBackground(Color.WHITE);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 1;
        gbc.weighty = 0.95;
        lista.add(lista2, gbc);
        lista.repaint();

    }

    Boolean isSelected() {
        return selected;
    }

    void setSelected(Boolean bool) {
        selected = bool;

        if (bool) {
            seleccionar.setEnabled(bool);
        } else {
            seleccionar.setEnabled(bool);
        }

    }

    void setCliente(PanelCliente cliente) {

        clienteSeleccionado = cliente;

    }

    private void clienteNoEncontrado() {

        JFrame ventanaClienteNoEncontrado = new JFrame();
        
        //armado ventana
        ventanaClienteNoEncontrado.setTitle("Cliente no encontrado");
        ventanaClienteNoEncontrado.setSize(500, 350); // Set your preferred size
        ventanaClienteNoEncontrado.setLocationRelativeTo(null); // Center the frame on the screen
        ventanaClienteNoEncontrado.setMinimumSize(new Dimension(450, 300));

        //cambio icono ventana
        ImageIcon customIcon = new ImageIcon("logo.png");
        ventanaClienteNoEncontrado.setIconImage(customIcon.getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        Background fondo = new Background("background.jpg");
        
        PanelText errorLabel = new PanelText("Cliente no encontrado, ¿desea crear uno nuevo?",19);
        errorLabel.setBackgroundColor(255,255, 255, 0);
        Boton botonCrear = new Boton("CREAR", 19);
        Boton botonCancelar = new Boton("CANCELAR", 19);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.95;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        fondo.add(errorLabel, gbc);
        
        gbc.gridy= 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.VERTICAL;
        fondo.add(botonCancelar, gbc);
        
        gbc.gridx= 1;
        fondo.add(botonCrear, gbc);
        
        
        botonCancelar.addActionListener((ActionEvent e) -> {
            ventanaClienteNoEncontrado.dispose();
        });
        
        botonCrear.addActionListener((ActionEvent e) -> {
            //cargar pantalla altaCliente
            main.cambiarPantalla("altaCliente");
            ventanaClienteNoEncontrado.dispose();
        });
        
        ventanaClienteNoEncontrado.setContentPane(fondo);
        ventanaClienteNoEncontrado.setVisible(true);

    }

}
