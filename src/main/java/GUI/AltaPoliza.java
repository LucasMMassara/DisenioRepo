package GUI;

import dto.ClienteDTO;
import dto.DomicilioDTO;
import dto.HijoDTO;
import dto.VehiculoDTO;
import gestores.GestorCobertura;
import gestores.GestorMarca;
import gestores.GestorPais;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import logica.Localidad;
import logica.Pais;
import logica.Provincia;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.rendering.PDFRenderer;


public class AltaPoliza extends JPanel {

    private static final long serialVersionUID = 1L;

    //pantallas
    Background primera = new Background("background.jpg");
    Background segunda = new Background("background.jpg");
    Background tercera = new Background("background.jpg");
    Background cuarta = new Background("background.jpg");
    Background quinta = new Background("background.jpg");
    Background pdf = new Background("background.jpg");
    Background buscarCliente = new Background("background.jpg");
    AltaCliente altaCliente = new AltaCliente(this);

    CardLayout cl = new CardLayout();
    JPanel containerPanel = new JPanel();

    MenuProductorSeguros main;

    //------------------------------------
    //Cliente
    ClienteDTO clienteDTO = new ClienteDTO("", "", "", "", "", "", "", "", "", "", "", "", null,new DomicilioDTO("", "", "", "", null, ""));
    
    //Hijos
    int clienteCantHijos = 0;
    List<HijoDTO> hijosDTO = new ArrayList<>();
    
    //Vehiculo
    String clienteSumaAsegurada = "";
    Localidad localidadRiesgo = new Localidad();
    VehiculoDTO vehiculoDTO = new VehiculoDTO();
    
    //poliza
    Date clientePolizaInicio = new Date();
    Date clientePolizaFin = new Date();
    Date clienteFechaInicioCuota;
    Date clienteUltimoDiaPagoCuota;
    String clienteFormaPago = "Mensual";
    String clienteTipoCob = "";
    String clienteNumPoliza = "";
    
    String prima = "";
    String derechosEmision = "";
    String descuentos = "";
    String totalAbonar = "";

    AltaPoliza(MenuProductorSeguros menu) {

        main = menu;

        primeraConfig();
        segundaConfig();
        terceraConfig();
        //cuartaConfig();
        quintaConfig();
        pdfConfig();
        buscarClienteConfig();

        //configurar panel container
        containerPanel.setLayout(cl);
        containerPanel.setPreferredSize(this.getSize());
        containerPanel.add(primera, "1");
        containerPanel.add(buscarCliente, "buscarCliente");
        containerPanel.add(segunda, "2");
        containerPanel.add(tercera, "3");
        containerPanel.add(cuarta, "4");
        containerPanel.add(quinta, "5");
        containerPanel.add(pdf, "6");
        containerPanel.add(altaCliente,"altaCliente");
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

    private void primeraConfig() {

        primera = new Background("background.jpg");

        Boton botonCancelar = new Boton("Cancelar");
        Boton botonBuscarCliente = new Boton("Buscar Cliente");
        Boton botonContinuar = new Boton("Continuar");

        JPanel panelCliente = new JPanel();
        JPanel panelHijos = new JPanel();

        JLabel cliente = new JLabel("Cliente");
        JPanel panelClienteLabel = new JPanel();

        JLabel apellido = new JLabel("Apellido");
        JLabel nombre = new JLabel("Nombre");
        JLabel numero = new JLabel("Nro. Cliente");
        JLabel tipoDocumento = new JLabel("Tipo de documento");
        JLabel nroDocumento = new JLabel("Nro. Documento");
        JLabel direccion = new JLabel("Direccion");

        JPanel panelApellido = new JPanel();
        JPanel panelNombre = new JPanel();
        JPanel panelNumero = new JPanel();
        JPanel panelDoc = new JPanel();
        JPanel panelNDoc = new JPanel();
        JPanel panelDireccion = new JPanel();

        JPanel clienteApellidoP = new JPanel();
        JPanel clienteNombreP = new JPanel();
        JPanel clienteNumeroP = new JPanel();
        JPanel clienteTipoDocP = new JPanel();
        JPanel clienteNroDocP = new JPanel();
        JPanel clienteDireccionP = new JPanel();

        JLabel clienteApellidoLabel = new JLabel(clienteDTO.getApellido());
        JLabel clienteNombreLabel = new JLabel(clienteDTO.getNombre());
        JLabel clienteNumeroLabel = new JLabel(clienteDTO.getNumCliente());
        JLabel clienteTipoDocLabel = new JLabel(clienteDTO.getTipoDocumento());
        JLabel clienteNroDocLabel = new JLabel(clienteDTO.getNumDocumento());
        JLabel clienteDireccionLabel = new JLabel("" + clienteDTO.getDomicilioDTO().getCalle() + " " + clienteDTO.getDomicilioDTO().getNumero());

        //config panelCliente
        panelCliente.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 0);

        Font customFont = new Font("Arial", Font.BOLD, 24); // Font name, style, and size
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.weightx = 1;
        gbc1.weighty = 0.2;
        gbc1.gridwidth = 3;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.NORTH;
        gbc1.insets = new Insets(10, 10, 0, 10);
        cliente.setFont(customFont);
        panelClienteLabel.setLayout(new GridBagLayout());
        panelClienteLabel.setBackground(new Color(240, 240, 240));
        panelClienteLabel.add(cliente, gbc2);
        panelCliente.add(panelClienteLabel, gbc1);

        gbc2.anchor = GridBagConstraints.SOUTHWEST;
        gbc2.insets = new Insets(0, 10, 5, 0);
        gbc1.gridy = 1;
        gbc1.weighty = 0.1;
        gbc1.gridwidth = 1;
        gbc1.anchor = GridBagConstraints.WEST;
        gbc1.insets = new Insets(30, 10, 5, 10);
        panelApellido.setLayout(new GridBagLayout());
        panelApellido.setBackground(new Color(250, 250, 250));
        panelApellido.add(apellido, gbc2);
        panelCliente.add(panelApellido, gbc1);

        gbc1.gridx = 1;
        panelNombre.setLayout(new GridBagLayout());
        panelNombre.setBackground(new Color(250, 250, 250));
        panelNombre.add(nombre, gbc2);
        panelCliente.add(panelNombre, gbc1);

        gbc1.gridx = 2;
        panelNumero.setLayout(new GridBagLayout());
        panelNumero.setBackground(new Color(250, 250, 250));
        panelNumero.add(numero, gbc2);
        panelCliente.add(panelNumero, gbc1);

        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.insets = new Insets(0, 10, 0, 0);
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc1.gridy = 2;
        gbc1.gridx = 0;
        gbc1.insets = new Insets(1, 10, 60, 10);
        clienteApellidoP.setLayout(new GridBagLayout());
        clienteApellidoP.setBackground(new Color(255, 255, 255));
        clienteApellidoP.setBorder(border);
        clienteApellidoP.add(clienteApellidoLabel, gbc2);
        panelCliente.add(clienteApellidoP, gbc1);

        gbc1.weighty = 0.2;
        gbc1.gridx = 1;
        clienteNombreP.setLayout(new GridBagLayout());
        clienteNombreP.setBackground(new Color(255, 255, 255));
        clienteNombreP.setBorder(border);
        clienteNombreP.add(clienteNombreLabel, gbc2);
        panelCliente.add(clienteNombreP, gbc1);

        gbc1.gridx = 2;
        clienteNumeroP.setLayout(new GridBagLayout());
        clienteNumeroP.setBackground(new Color(255, 255, 255));
        clienteNumeroP.setBorder(border);
        clienteNumeroP.add(clienteNumeroLabel, gbc2);
        panelCliente.add(clienteNumeroP, gbc1);

        gbc2.anchor = GridBagConstraints.SOUTHWEST;
        gbc2.insets = new Insets(0, 10, 5, 0);
        gbc1.weighty = 0.1;
        gbc1.anchor = GridBagConstraints.WEST;
        gbc1.insets = new Insets(0, 10, 5, 10);
        gbc1.gridy = 3;
        gbc1.gridx = 0;
        panelDoc.setLayout(new GridBagLayout());
        panelDoc.setBackground(new Color(250, 250, 250));
        panelDoc.add(tipoDocumento, gbc2);
        panelCliente.add(panelDoc, gbc1);

        gbc1.gridx = 1;
        panelNDoc.setLayout(new GridBagLayout());
        panelNDoc.setBackground(new Color(250, 250, 250));
        panelNDoc.add(nroDocumento, gbc2);
        panelCliente.add(panelNDoc, gbc1);

        gbc1.gridx = 2;
        panelDireccion.setLayout(new GridBagLayout());
        panelDireccion.setBackground(new Color(250, 250, 250));
        panelDireccion.add(direccion, gbc2);
        panelCliente.add(panelDireccion, gbc1);

        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.insets = new Insets(0, 10, 0, 0);
        gbc1.anchor = GridBagConstraints.CENTER;
        gbc1.weighty = 0.2;
        gbc1.gridy = 4;
        gbc1.gridx = 0;
        gbc1.insets = new Insets(1, 10, 60, 10);
        clienteTipoDocP.setLayout(new GridBagLayout());
        clienteTipoDocP.setBackground(new Color(255, 255, 255));
        clienteTipoDocP.setBorder(border);
        clienteTipoDocP.add(clienteTipoDocLabel, gbc2);
        panelCliente.add(clienteTipoDocP, gbc1);

        gbc1.gridx = 1;
        clienteNroDocP.setLayout(new GridBagLayout());
        clienteNroDocP.setBackground(new Color(255, 255, 255));
        clienteNroDocP.setBorder(border);
        clienteNroDocP.add(clienteNroDocLabel, gbc2);
        panelCliente.add(clienteNroDocP, gbc1);

        gbc1.gridx = 2;
        clienteDireccionP.setLayout(new GridBagLayout());
        clienteDireccionP.setBackground(new Color(255, 255, 255));
        clienteDireccionP.setBorder(border);
        clienteDireccionP.add(clienteDireccionLabel, gbc2);
        panelCliente.add(clienteDireccionP, gbc1);

        //config panelHijos
        JPanel panelcantHijosLabel = new JPanel();
        JPanel paneltextHijos = new JPanel();

        JLabel cantHijosLabel = new JLabel("Cant. Hijos del cliente: ");
        PanelTextInput hijosInput = new PanelTextInput(16);
        hijosInput.restrictToNumbers();
        hijosInput.restrictSize(2);
        //hijosInput.setText("0");

        panelHijos.setLayout(new GridBagLayout());
        panelcantHijosLabel.setLayout(new GridBagLayout());
        paneltextHijos.setLayout(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        GridBagConstraints gbc4 = new GridBagConstraints();

        gbc4.anchor = GridBagConstraints.EAST;
        gbc4.insets = new Insets(5, 0, 5, 5);
        gbc4.weightx = 1;
        gbc4.weighty = 1;
        panelcantHijosLabel.add(cantHijosLabel, gbc4);
        gbc3.fill = GridBagConstraints.BOTH;
        gbc3.weightx = 0.8;
        gbc3.weighty = 1;
        gbc3.gridwidth = 1;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.anchor = GridBagConstraints.EAST;
        gbc3.insets = new Insets(0, 0, 0, 0);
        panelHijos.add(panelcantHijosLabel, gbc3);

        gbc4.anchor = GridBagConstraints.WEST;
        gbc4.insets = new Insets(5, 5, 5, 0);
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        paneltextHijos.add(hijosInput, gbc4);
        gbc3.anchor = GridBagConstraints.WEST;
        gbc3.gridwidth = 1;
        gbc3.weightx = 0.2;
        gbc3.gridx = 1;
        panelHijos.add(paneltextHijos, gbc3);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 0.025;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        primera.add(botonBuscarCliente, gbc);

        panelCliente.setBackground(new Color(230, 230, 230));
        panelCliente.setBorder(border);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        gbc.weighty = 0.75;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 10, 10);
        primera.add(panelCliente, gbc);

        panelHijos.setBackground(new Color(255, 255, 150));
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 10);
        primera.add(panelHijos, gbc);

        gbc.gridwidth = 1;
        gbc.weighty = 0.025;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        primera.add(botonCancelar, gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        primera.add(botonContinuar, gbc);

        botonBuscarCliente.addActionListener((ActionEvent e) -> {
            cambiarPantalla("buscarCliente");
        });
        botonCancelar.addActionListener((ActionEvent e) -> {
            main.cambiarPantalla("1");
        });
        botonContinuar.addActionListener((ActionEvent e) -> {
            //guardarDatos

            if (hijosInput.getText().isEmpty()) {
                clienteCantHijos = 0;
            } else {
                clienteCantHijos = Integer.parseInt(hijosInput.getText());
            }
            
            List<JLabel> listaPaneles = new ArrayList<>();
            
            // Add objects of different types to the list
            listaPaneles.add(clienteApellidoLabel);
            listaPaneles.add(clienteNombreLabel);
            listaPaneles.add(clienteNumeroLabel);
            listaPaneles.add(clienteTipoDocLabel);
            listaPaneles.add(clienteNroDocLabel);
            listaPaneles.add(clienteDireccionLabel);
            
             //chequear inputs
            boolean inputVacio = false;
        
            for(JLabel panel: listaPaneles ){

                if("".equals(panel.getText())){
                    inputVacio = true;
                    break;
                }
            }

            if (inputVacio) {
                VentanaError entradasVaciasError = new VentanaError("Seleccione un cliente", "Entrada incorrecta");       
            }else {
               if (clienteCantHijos != 0) {
                    terceraConfig();
                    containerPanel.add(tercera, "3");
                }
                hijosInput.setCorrectInput();
                cambiarPantalla("2");
            }
        });
    }
 
    List<Pais> listaPaises;
    List<Provincia> listaProvincias;
    List<Localidad> listaLocalidades;
    
    String[] listaMarcas;
    String[] listaModelos;
    String[] listaAnios;
    
    String[] defaults = {""};
    PanelDropDown pais = new PanelDropDown("WEST", defaults);
    PanelDropDown provincia = new PanelDropDown("WEST", defaults);
    PanelDropDown localidad = new PanelDropDown("WEST", defaults);
    
    PanelDropDown dMarca = new PanelDropDown("WEST", defaults);
    PanelDropDown dModelo = new PanelDropDown("WEST", defaults);
    PanelDropDown dAnio = new PanelDropDown("WEST", defaults);
    
    private void segundaConfig() {

        Boton botonVolver = new Boton("Volver");
        Boton botonCancelar = new Boton("Cancelar");
        Boton botonContinuar = new Boton("Continuar");

        JPanel panelVehiculo = new JPanel();
        JPanel panelMedidas = new JPanel();

        try {

            GestorPais gp = new GestorPais();
            listaPaises = gp.ObtenerPaises();
            listaProvincias = listaPaises.get(0).getProvincias();
            listaLocalidades = listaProvincias.get(0).getLocalidades();

            String[] paises = new String[listaPaises.size()];
            for (int i = 0; i < listaPaises.size(); i++) {
                paises[i] = listaPaises.get(i).getNombre();
            }
            pais = new PanelDropDown(paises);

            String[] provincias = new String[listaProvincias.size()];
            for (int i = 0; i < listaProvincias.size(); i++) {
                provincias[i] = listaProvincias.get(i).getNombreProvincia();
            }
            provincia = new PanelDropDown(provincias);

            String[] localidades = new String[listaLocalidades.size()];
            for (int i = 0; i < listaLocalidades.size(); i++) {
                localidades[i] = listaLocalidades.get(i).getNombreLocalidad();
            }
            localidad = new PanelDropDown(localidades);

            pais.addCustomPanelListener(new CustomPanelListener() {
                @Override
                public void onPanelItemSelected(PanelDropDown source, String selectedItem) {
                    for (Pais p : listaPaises) {
                        if (p.getNombre().equals(selectedItem)) {
                            listaProvincias = p.getProvincias();
                            listaLocalidades = listaProvincias.get(0).getLocalidades();
                            actualizarListaProvincias();
                            actualizarListaLocalidades();
                            break;
                        }
                    }
                }
            });

            provincia.addCustomPanelListener(new CustomPanelListener() {
                @Override
                public void onPanelItemSelected(PanelDropDown source, String selectedItem) {
                    for (Provincia p : listaProvincias) {
                        if (p.getNombreProvincia().equals(selectedItem)) {
                            listaLocalidades = p.getLocalidades();
                            actualizarListaLocalidades();
                            break;
                        }
                    }
                }
            });
        } catch (Exception e) {

        }
        
        GestorMarca gm = new GestorMarca();
        
        try {
            
            listaMarcas = gm.obtenerMarcas();
            listaModelos = gm.obtenerModelos(listaMarcas[0]);
            listaAnios = gm.obtenerAnios(listaModelos[0]);

            dMarca = new PanelDropDown(listaMarcas); 
            dModelo = new PanelDropDown(listaModelos); 
            dAnio = new PanelDropDown(listaAnios); 
            
           

        }
        catch(Exception e){
            
        }
        
        dMarca.addCustomPanelListener(new CustomPanelListener() {
            @Override
            public void onPanelItemSelected(PanelDropDown source, String selectedItem) {
                listaModelos = gm.obtenerModelos(selectedItem);
                listaAnios = gm.obtenerAnios(listaModelos[0]);
                dModelo.setItems(listaModelos);
                dAnio.setItems(listaAnios);
            }
        });

        dModelo.addCustomPanelListener(new CustomPanelListener() {
            @Override
            public void onPanelItemSelected(PanelDropDown source, String selectedItem) {
                listaAnios = gm.obtenerAnios(selectedItem);
                dAnio.setItems(listaAnios);
            }
        });

        PanelTextInput tiNroMotor = new PanelTextInput(16);
        PanelTextInput tiNroChasis = new PanelTextInput(16);
        PanelTextInput tiPatente = new PanelTextInput(16);
        PanelTextInput tiKMAnio = new PanelTextInput(16);
        PanelCheckBox garage = new PanelCheckBox("Garage");
        PanelCheckBox alarma = new PanelCheckBox("Alarma");
        PanelCheckBox dispositivo = new PanelCheckBox("Dispositivo antirrobo");
        PanelCheckBox tuercas = new PanelCheckBox("Tuercas antirrobo");
        
        tiPatente.restrictSize(7);
        tiPatente.restrictToAlphanumerics();

        botonVolver.addActionListener((ActionEvent e) -> {
            cambiarPantalla("1");
        });
        botonCancelar.addActionListener((ActionEvent e) -> {
            cambiarPantalla("1");
            main.cambiarPantalla("1");
        });
        botonContinuar.addActionListener((ActionEvent e) -> {

            //buscar suma asegurada
            //clienteSumaAsegurada = 
            
            List<PanelTextInput> listaPaneles = new ArrayList<>();

            // Add objects of different types to the list
            listaPaneles.add(tiNroMotor);
            listaPaneles.add(tiNroChasis);
            listaPaneles.add(tiKMAnio);
            
             //chequear inputs
            boolean inputVacio = false;
        
            for(PanelTextInput panel: listaPaneles ){

                if("".equals(panel.getText())){
                    inputVacio = true;
                    panel.setWrongInput();
                }
                else{
                    panel.setCorrectInput();
                }
            }

            if (inputVacio) {
                VentanaError entradasVaciasError = new VentanaError("Faltan datos obligatorios", "Entrada incorrecta");       
            }else {
                vehiculoDTO.setKmPorAnio(tiKMAnio.getText());
                vehiculoDTO.setMarcaVehiculo(dMarca.getSelectedItem());
                vehiculoDTO.setModeloVehiculo(dModelo.getSelectedItem());
                vehiculoDTO.setAnioVehiculo(dAnio.getSelectedItem());
                vehiculoDTO.setNumMotor(tiNroMotor.getText());
                vehiculoDTO.setNumChasis(tiNroChasis.getText());
                vehiculoDTO.setNumPatente(tiPatente.getText());
                vehiculoDTO.setGuardaEnGarage(garage.isSelected());
                vehiculoDTO.setTieneAlarma(alarma.isSelected());
                vehiculoDTO.setDispositivoRastreo(dispositivo.isSelected());
                vehiculoDTO.setTuercasAntirrobo(tuercas.isSelected());

                if (clienteCantHijos == 0) {
                    cuartaConfig();
                    cambiarPantalla("4");
                } else {
                    cambiarPantalla("3");
                }
            }
            
            

        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 0.025;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        segunda.add(botonVolver, gbc);

        panelVehiculo.setBackground(new Color(240, 240, 240));

        panelVehiculoConfig(panelVehiculo,pais, provincia, localidad, dMarca, dModelo, dAnio, tiNroMotor, tiNroChasis, tiPatente, tiKMAnio);
        panelVehiculo.setBorder(border);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        gbc.weighty = 0.85;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 5, 10);
        segunda.add(panelVehiculo, gbc);

        panelMedidas.setBackground(new Color(240, 240, 240));
        panelMedidasConfig(panelMedidas, garage, alarma, dispositivo, tuercas);
        panelMedidas.setBorder(border);
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.gridy = 2;
        segunda.add(panelMedidas, gbc);

        gbc.gridwidth = 1;
        gbc.weighty = 0.025;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(5, 10, 10, 10);
        segunda.add(botonCancelar, gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        segunda.add(botonContinuar, gbc);

    }

    private void terceraConfig() {

        tercera = new Background("background.jpg");
        
        ArrayList<PanelDropDown> sexoDropDownList = new ArrayList<>();
        ArrayList<PanelDropDown> estadoCivilDropDownList = new ArrayList<>();
        ArrayList<PanelDatePicker> fechaInputList = new ArrayList<>();
        
        Boton botonVolver = new Boton("Volver");
        Boton botonCancelar = new Boton("Cancelar");
        Boton botonConfirmar = new Boton("Confirmar datos");

        JPanel panelHijos = new JPanel();

        botonVolver.addActionListener((ActionEvent e) -> {
            cambiarPantalla("2");
        });
        botonCancelar.addActionListener((ActionEvent e) -> {
            cambiarPantalla("1");
            main.cambiarPantalla("1");
        });
        botonConfirmar.addActionListener((ActionEvent e) -> { 

            boolean entradaIncorrecta = false;
            
            for(PanelDatePicker date: fechaInputList){
                if(date.getDate() == null){
                    entradaIncorrecta = true;
                    break;
                }
            }
            
            if(entradaIncorrecta){
                VentanaError fechaErronea = new VentanaError("Faltan fechas de nacimiento", "Entrada incorrecta");
            }
            else{
                hijosDTO.clear();
                for(int i = 0; i < sexoDropDownList.size(); i++){
                hijosDTO.add(new HijoDTO(fechaInputList.get(i).getDate(),sexoDropDownList.get(i).getSelectedItem(),estadoCivilDropDownList.get(i).getSelectedItem()));
                }
                cuartaConfig();
                cambiarPantalla("4");
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 0.025;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        tercera.add(botonVolver, gbc);

        panelHijos.setBackground(new Color(150, 255, 255));
        panelHijosConfig(panelHijos, clienteCantHijos,sexoDropDownList,estadoCivilDropDownList,fechaInputList);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        gbc.weighty = 0.95;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 5, 10);
        tercera.add(panelHijos, gbc);

        gbc.gridwidth = 1;
        gbc.weighty = 0.025;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(5, 10, 10, 10);
        tercera.add(botonCancelar, gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        tercera.add(botonConfirmar, gbc);

    }

    private void cuartaConfig() {

        String[] formasPago = {"Mensual", "Semestral"};
        
        GestorCobertura gcb = new GestorCobertura();
        
        String[] tiposCobertura = gcb.obtenerCoberturas(vehiculoDTO.getAnioVehiculo());

        PanelDatePicker fechaInput = new PanelDatePicker();
        PanelDropDown tipoCoberturaDropDown = new PanelDropDown("CENTER", tiposCobertura);
        PanelDropDown formaPagoDropDown = new PanelDropDown("CENTER", formasPago);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        fechaInput.setMinSelectableDate(calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        fechaInput.setMaxSelectableDate(calendar.getTime());
        
        Boton botonVolver = new Boton("Volver");
        Boton botonCancelar = new Boton("Cancelar");
        Boton botonGenerar = new Boton("Generar poliza");

        JPanel panelCobertura = new JPanel();

        botonVolver.addActionListener((ActionEvent e) -> {

            if (clienteCantHijos == 0) {
                cambiarPantalla("2");
            } else {
                cambiarPantalla("3");
            }

        });
        botonCancelar.addActionListener((ActionEvent e) -> {
            cambiarPantalla("1");
            main.cambiarPantalla("1");
        });
        botonGenerar.addActionListener((ActionEvent e) -> {
            
            
            
            if(fechaInput.getDate() == null){
                VentanaError fechaErronea = new VentanaError("Falta fecha de inicio", "Entrada incorrecta");
            }
            else{
                clientePolizaInicio = fechaInput.getDate();
                
                LocalDate localOriginalDate = clientePolizaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate sixMonthsAfter = localOriginalDate.plusMonths(6);
                clientePolizaFin = Date.from(sixMonthsAfter.atStartOfDay(ZoneId.systemDefault()).toInstant());
                
                clienteFormaPago = tipoCoberturaDropDown.getSelectedItem();
                if ("Semestral".equals(formaPagoDropDown.getSelectedItem())) {
                    clienteFormaPago = "Semestral";
                } else {
                    clienteFormaPago = "Mensual";
                }
                clienteTipoCob = tipoCoberturaDropDown.getSelectedItem();
                
                quintaConfig();
                containerPanel.add(quinta, "5");
                cambiarPantalla("5");   
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 0.025;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        cuarta.add(botonVolver, gbc);

        panelCobertura.setBackground(new Color(240, 240, 240));
        panelCoberturaConfig(panelCobertura, fechaInput, tipoCoberturaDropDown, formaPagoDropDown);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        gbc.weighty = 0.95;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 5, 10);
        cuarta.add(panelCobertura, gbc);

        gbc.gridwidth = 1;
        gbc.weighty = 0.025;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(5, 10, 10, 10);
        cuarta.add(botonCancelar, gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        cuarta.add(botonGenerar, gbc);

    }

    private void quintaConfig() {

        quinta = new Background("background.jpg");

        Boton botonVolver = new Boton("Volver");
        Boton botonCancelar = new Boton("Cancelar");
        Boton botonConfirmar = new Boton("Confirmar poliza");

        JPanel panelResumen = new JPanel();
        JPanel panelPoliza = new JPanel();
        JPanel panelCuotas = new JPanel();

        botonVolver.addActionListener((ActionEvent e) -> {
            cambiarPantalla("4");
        });
        botonCancelar.addActionListener((ActionEvent e) -> {
            cambiarPantalla("1");
            main.cambiarPantalla("1");
        });
        botonConfirmar.addActionListener((ActionEvent e) -> {
            //crear numPoliza
            cambiarPantalla("6");
        });

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 0.025;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        quinta.add(botonVolver, gbc);

        panelResumen.setBackground(new Color(240, 240, 240));
        panelResumenConfig(panelResumen);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = 2;
        gbc.weighty = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 5, 0);
        quinta.add(panelResumen, gbc);

        panelPoliza.setBackground(new Color(240, 240, 240));
        panelPolizaConfig(panelPoliza);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = 1;
        //gbc.weighty = 0.35;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 0, 10);
        quinta.add(panelPoliza, gbc);

        panelCuotas.setBackground(new Color(255, 255, 150));

        int cuotas;
        if (clienteFormaPago.equals("Mensual")) {
            cuotas = 6;
        } else {
            cuotas = 1;
        }

        panelCuotasConfig(panelCuotas, cuotas);
        gbc.fill = GridBagConstraints.BOTH;
        //gbc.weighty = 0.35;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 5, 10);
        quinta.add(panelCuotas, gbc);

        gbc.gridwidth = 1;
        gbc.weighty = 0.025;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(5, 10, 10, 10);
        quinta.add(botonCancelar, gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        quinta.add(botonConfirmar, gbc);

    }

    private double zoomFactor = 1;
    private void pdfConfig() {
        
        pdf.setLayout(new GridBagLayout());
        JPanel pdfPanel  = new JPanel();
        
      
        Boton zoomInButton = new Boton("+");
        Boton zoomOutButton = new Boton("-");
        Boton confirmar = new Boton("Confirmar");
        
        displayPDF("CU_11_form.pdf", zoomFactor, pdfPanel);
        
        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(zoomFactor < 2.5){
                    zoomFactor *= 1.2;
                    updateZoom(pdfPanel, (float) (zoomFactor));
                }
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(zoomFactor > 0.4){
                    zoomFactor /= 1.2;
                    updateZoom(pdfPanel, (float) (zoomFactor));
                }
            }
        });
        
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.cambiarPantalla("1");
            }
        });
        
        pdfPanel.setLayout(new BoxLayout(pdfPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(pdfPanel);
           
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.99;
        gbc.weightx = 0.98;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        pdf.add(scrollPane,gbc);
        
        gbc.gridheight = 1;
        gbc.weightx = 0.02;
        gbc.gridx = 1;
        pdf.add(zoomInButton, gbc);
        
        gbc.gridy = 1;
        pdf.add(zoomOutButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        pdf.add(confirmar,gbc);

    }
    
    private void displayPDF(String pdfFilePath, double zoomFactor, JPanel pdfPanel) {
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFRenderer renderer = new PDFRenderer(document);
            
            try{
            llenarPDF(document);
            }
            catch(Exception e){
                VentanaError errorCargarDoc = new VentanaError("No se puede completar el cargado del PDF", "Error impresion");
            }
            
            int pageCount = document.getNumberOfPages();
            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                BufferedImage image = renderer.renderImageWithDPI(pageIndex, (float) (100 * zoomFactor));

                ImageIcon imageIcon = new ImageIcon(image);
                JLabel label = new JLabel(imageIcon);
                pdfPanel.add(label);
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }

    private void updateZoom(JPanel pdfPanel,  double zoomFactor) {
        pdfPanel.removeAll();
        displayPDF("CU_11_form.pdf", zoomFactor, pdfPanel);
        revalidate();
        repaint();
    }

    private void buscarClienteConfig() {

        BuscarCliente panel = new BuscarCliente(this);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        buscarCliente.add(panel, gbc);

    }

    private void panelVehiculoConfig(JPanel panelVehiculo, PanelDropDown dPais, PanelDropDown dProvincia, PanelDropDown dLocalidad, PanelDropDown dMarca, PanelDropDown dModelo, PanelDropDown dAnio, PanelTextInput tiNroMotor, PanelTextInput tiNroChasis, PanelTextInput tiPatente, PanelTextInput tiKMAnio) {

        PanelText tVehiculo = new PanelText("Vehiculo", "BOLD", 24, "WEST");

        panelVehiculo.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelVehiculo.add(tVehiculo, gbc);

        //domicilio ---------------------------
        PanelText tDomicilioRiesgo = new PanelText("Domicilio de riesgo", "BOLD", 16, "SOUTHWEST");
        PanelText tProvincia = new PanelText("Provincia", "PLAIN", 16, "SOUTHWEST");
        PanelText tPais = new PanelText("Pais", "PLAIN", 16, "SOUTHWEST");
        PanelText tLocalidad = new PanelText("Localidad", "PLAIN", 16, "SOUTHWEST");

        gbc.gridy = 1;
        panelVehiculo.add(tDomicilioRiesgo, gbc);

        gbc.gridy = 2;
        panelVehiculo.add(tPais, gbc);

        gbc.gridx = 1;
        panelVehiculo.add(tProvincia, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(tLocalidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelVehiculo.add(dPais, gbc);

        gbc.gridx = 1;
        panelVehiculo.add(dProvincia, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(dLocalidad, gbc);

        //modelo ---------------------------
        PanelText tModeloVehiculo = new PanelText("Modelo del vehiculo", "BOLD", 16, "SOUTHWEST");

        PanelText tMarca = new PanelText("Marca", "PLAIN", 16, "SOUTHWEST");
        PanelText tModelo = new PanelText("Modelo", "PLAIN", 16, "SOUTHWEST");
        PanelText tAnio = new PanelText("Año", "PLAIN", 16, "SOUTHWEST");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        panelVehiculo.add(tModeloVehiculo, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 5;
        panelVehiculo.add(tMarca, gbc);

        gbc.gridx = 1;
        panelVehiculo.add(tModelo, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(tAnio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panelVehiculo.add(dMarca, gbc);

        gbc.gridx = 1;
        panelVehiculo.add(dModelo, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(dAnio, gbc);

        //suma asegurada ---------------------------
        //TO DO recuperar suma asegurada
        //clienteSumaAsegurada = result;
        PanelText tSumaAsegurada = new PanelText("Suma aseguradora en pesos: ", "ITALIC", 16, "WEST");
        PanelTextInput tSuma = new PanelTextInput(clienteSumaAsegurada, 16);
        tSuma.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(13, 0, 0, 0);
        panelVehiculo.add(tSumaAsegurada, gbc);

        gbc.gridx = 1;
        panelVehiculo.add(tSuma, gbc);

        //datos vehiculo ---------------------------
        PanelText tDatosVehiculo = new PanelText("Datos vehiculo", "BOLD", 16, "SOUTHWEST");

        PanelText tNroMotor = new PanelText("Nro. Motor", "PLAIN", 16, "SOUTHWEST");
        PanelText tNroChasis = new PanelText("Nro. Chasis", "PLAIN", 16, "SOUTHWEST");
        PanelText tPatente = new PanelText("Patente", "PLAIN", 16, "SOUTHWEST");

        PanelText tKMAnio = new PanelText("KM. por año", "PLAIN", 16, "SOUTHWEST");
        tiKMAnio.restrictToNumbers();
        PanelText tCantidadSin = new PanelText("Cantidad de siniestros en el ultimo año", "PLAIN", 16, "SOUTHWEST");
        PanelTextInput tiCantidadSin = new PanelTextInput("placeholder", 16);

        tiCantidadSin.setEditable(false);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panelVehiculo.add(tDatosVehiculo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panelVehiculo.add(tNroMotor, gbc);
        gbc.gridx = 1;
        panelVehiculo.add(tNroChasis, gbc);
        gbc.gridx = 2;
        panelVehiculo.add(tPatente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panelVehiculo.add(tiNroMotor, gbc);
        gbc.gridx = 1;
        panelVehiculo.add(tiNroChasis, gbc);
        gbc.gridx = 2;
        panelVehiculo.add(tiPatente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        panelVehiculo.add(tKMAnio, gbc);
        gbc.gridx = 1;
        panelVehiculo.add(tCantidadSin, gbc);

        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 12;
        panelVehiculo.add(tiKMAnio, gbc);
        gbc.gridx = 1;
        panelVehiculo.add(tiCantidadSin, gbc);

    }

    private void panelMedidasConfig(JPanel panelMedidas, PanelCheckBox garage, PanelCheckBox alarma, PanelCheckBox dispositivo, PanelCheckBox tuercas) {

        panelMedidas.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        //titulo
        PanelText medidas = new PanelText("Medidas de seguridad", "BOLD", 16, "WEST");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 0);
        panelMedidas.add(medidas, gbc);

        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridy = 1;
        panelMedidas.add(garage, gbc);

        gbc.gridx = 1;
        panelMedidas.add(alarma, gbc);

        gbc.gridx = 2;
        panelMedidas.add(dispositivo, gbc);

        gbc.gridx = 3;
        panelMedidas.add(tuercas, gbc);

    }

    private void panelHijosConfig(JPanel panelHijos, int cantidadHijos, ArrayList<PanelDropDown> sexoDropDownList, ArrayList<PanelDropDown> estadoCivilDropDownList, ArrayList<PanelDatePicker> fechaInputList) {

        CardLayout cl1 = new CardLayout();
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        //configurar panel container
        panelHijos.setLayout(cl1);
        panelHijos.setBorder(border);
        
        //panelHijos.add(menuProductorSeguros, "1");
        for (int i = 1; i <= cantidadHijos; i++) {

            JPanel panel = new JPanel();

            panel.setLayout(new GridBagLayout());

            PanelText titulo = new PanelText("Declaracion de hijos", "BOLD", 26, "SOUTHWEST");
            PanelText hijo = new PanelText("HIJO " + i, "PLAIN", 20, "WEST");
            titulo.setBackgroundColor(255, 255, 255);
            hijo.setBackgroundColor(255, 255, 255);

            PanelText fechaDeNacimiento = new PanelText("Fecha de nacimiento", "PLAIN", 18, "SOUTHWEST");
            PanelText sexo = new PanelText("Sexo", "PLAIN", 18, "SOUTHWEST");
            PanelText estadoCivil = new PanelText("Estado civil", "PLAIN", 18, "SOUTHWEST");

            PanelText numero = new PanelText("" + i, "PLAIN", 18, "CENTER");

            String[] generos = {"Femenino", "Masculino"};
            String[] estados = {"casado/a", "soltero/a", "viudo/a"};

            
            //PanelTextInput fechaInput = new PanelTextInput(18);
            PanelDropDown sexoDropDown = new PanelDropDown("CENTER", generos);
            PanelDropDown estadoCivilDropDown = new PanelDropDown("CENTER", estados);
            PanelDatePicker fechaInput = new PanelDatePicker();
            fechaInput.setMinSelectableDate("1920-01-01");
            fechaInput.setMaxSelectableDate(Calendar.getInstance().getTime());
            
            Boton boton0 = new Boton("🡰");
            Boton boton1 = new Boton("🡲");

            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.fill = GridBagConstraints.BOTH;
            gbc2.gridwidth = 3;
            gbc2.weightx = 1;
            gbc2.weighty = 1;
            // gbc2.insets = new Insets(10, 10, 10, 10);

            gbc2.gridx = 0;
            gbc2.gridy = 0;
            panel.add(titulo, gbc2);

            gbc2.anchor = GridBagConstraints.NORTHWEST;
            //gbc2.fill = GridBagConstraints.NONE;
            gbc2.gridy = 1;
            gbc2.weighty = 0.1;
            panel.add(hijo, gbc2);

            gbc2.gridwidth = 1;
            gbc2.anchor = GridBagConstraints.SOUTHWEST;
            gbc2.weighty = 1;
            gbc2.gridy = 2;
            panel.add(fechaDeNacimiento, gbc2);

            gbc2.gridx = 1;
            panel.add(sexo, gbc2);

            gbc2.gridx = 2;
            panel.add(estadoCivil, gbc2);

            gbc2.fill = GridBagConstraints.HORIZONTAL;
            //gbc2.insets = new Insets(20, 20, 20, 20);
            gbc2.anchor = GridBagConstraints.NORTHWEST;
            gbc2.gridx = 0;
            gbc2.gridy = 3;
            panel.add(fechaInput, gbc2);

            gbc2.gridx = 1;
            panel.add(sexoDropDown, gbc2);

            gbc2.gridx = 2;
            panel.add(estadoCivilDropDown, gbc2);

            gbc2.fill = GridBagConstraints.NONE;
            //gbc2.insets = new Insets(20, 20, 20, 20);
            gbc2.anchor = GridBagConstraints.EAST;
            gbc2.gridwidth = 1;
            gbc2.gridx = 0;
            gbc2.gridy = 4;
            gbc2.insets = new Insets(5, 5, 5, 5);
            panel.add(boton0, gbc2);

            gbc2.anchor = GridBagConstraints.CENTER;
            gbc2.gridx = 1;
            panel.add(numero, gbc2);

            gbc2.anchor = GridBagConstraints.WEST;
            gbc2.gridx = 2;
            panel.add(boton1, gbc2);

            panelHijos.add(panel, "" + i);

            sexoDropDownList.add(sexoDropDown);
            estadoCivilDropDownList.add(estadoCivilDropDown);
            fechaInputList.add(fechaInput);
            
            int a = i;

            boton0.addActionListener((ActionEvent e) -> {
                cl1.show(panelHijos, "" + (a - 1));
            });

            boton1.addActionListener((ActionEvent e) -> {
                cl1.show(panelHijos, "" + (a + 1));
            });

        }
        cl1.show(panelHijos, "" + 1);
    }

    private void panelCoberturaConfig(JPanel panelCobertura, PanelDatePicker fechaInput, PanelDropDown tipoCoberturaDropDown, PanelDropDown formaPagoDropDown) {

        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        //configurar panel container
        panelCobertura.setLayout(new GridBagLayout());
        panelCobertura.setBorder(border);

        PanelText titulo = new PanelText("Seleccion de cobertura", "BOLD", 26, "SOUTHWEST");
        titulo.setBackgroundColor(255, 255, 255);

        PanelText tipoCobertura = new PanelText("Tipo de cobertura", "PLAIN", 18, "SOUTHWEST");
        PanelText fechaInicio = new PanelText("Fecha de inicio", "PLAIN", 18, "SOUTHWEST");
        PanelText formaPago = new PanelText("Forma de pago", "PLAIN", 18, "SOUTHWEST");

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.gridwidth = 3;
        gbc2.weightx = 1;
        gbc2.weighty = 1;

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        panelCobertura.add(titulo, gbc2);

        gbc2.gridwidth = 1;
        gbc2.anchor = GridBagConstraints.SOUTHWEST;
        gbc2.weighty = 1;
        gbc2.gridy = 1;
        panelCobertura.add(tipoCobertura, gbc2);

        gbc2.gridx = 1;
        panelCobertura.add(fechaInicio, gbc2);

        gbc2.gridx = 2;
        panelCobertura.add(formaPago, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        //gbc2.insets = new Insets(20, 20, 20, 20);
        gbc2.anchor = GridBagConstraints.NORTHWEST;
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        panelCobertura.add(tipoCoberturaDropDown, gbc2);

        gbc2.gridx = 1;
        panelCobertura.add(fechaInput, gbc2);

        gbc2.gridx = 2;
        panelCobertura.add(formaPagoDropDown, gbc2);

    }

    private void panelResumenConfig(JPanel panelResumen) {

        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        panelResumen.setLayout(new GridBagLayout());
        panelResumen.setBorder(border);

        PanelText titulo = new PanelText("Resumen de datos", "BOLD", 21, "WEST");

        PanelText titularSeguro = new PanelText("Titular del vehiculo", "BOLD", 18, "WEST");
        PanelText vehiculo = new PanelText("Vehiculo", "BOLD", 18, "WEST");

        PanelText modelo = new PanelText("Modelo", "PLAIN", 16, "WEST");
        PanelText patente = new PanelText("Patente", "PLAIN", 16, "WEST");
        PanelText suma = new PanelText("Suma asegurada", "PLAIN", 16, "WEST");
        PanelText chasis = new PanelText("Chasis", "PLAIN", 16, "WEST");
        PanelText motor = new PanelText("Motor", "PLAIN", 16, "WEST");

        PanelTextInput tituloI = new PanelTextInput(clienteDTO.getApellido() + " " + clienteDTO.getNombre(), 16);
        tituloI.setEditable(false);
        PanelTextInput modeloI = new PanelTextInput(vehiculoDTO.getMarcaVehiculo() + ", " + vehiculoDTO.getModeloVehiculo() + ", año " + vehiculoDTO.getAnioVehiculo(), 16);
        modeloI.setEditable(false);
        PanelTextInput patenteI = new PanelTextInput(vehiculoDTO.getNumPatente(), 16);
        patenteI.setEditable(false);
        PanelTextInput sumaI = new PanelTextInput(clienteSumaAsegurada, 16);
        sumaI.setEditable(false);
        PanelTextInput chasisI = new PanelTextInput(vehiculoDTO.getNumChasis(), 16);
        chasisI.setEditable(false);
        PanelTextInput motorI = new PanelTextInput(vehiculoDTO.getNumMotor(), 16);
        motorI.setEditable(false);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.gridwidth = 3;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        //gbc2.insets = new Insets(10, 10, 10, 10);
        panelResumen.add(titulo, gbc2);

        gbc2.gridwidth = 1;
        gbc2.gridy = 1;
        panelResumen.add(titularSeguro, gbc2);

        gbc2.gridwidth = 2;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTH;
        gbc2.gridy = 2;
        panelResumen.add(tituloI, gbc2);

        gbc2.gridwidth = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridy = 3;
        panelResumen.add(vehiculo, gbc2);

        gbc2.gridy = 4;
        panelResumen.add(modelo, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTH;
        gbc2.gridwidth = 2;
        gbc2.gridx = 0;
        gbc2.gridy = 5;
        panelResumen.add(modeloI, gbc2);

        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridwidth = 1;
        gbc2.gridy = 6;
        panelResumen.add(patente, gbc2);

        gbc2.gridx = 1;
        panelResumen.add(suma, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTH;
        gbc2.gridy = 7;
        gbc2.gridx = 0;
        panelResumen.add(patenteI, gbc2);

        gbc2.gridx = 1;
        panelResumen.add(sumaI, gbc2);

        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = 8;
        panelResumen.add(chasis, gbc2);

        gbc2.gridx = 1;
        panelResumen.add(motor, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTH;
        gbc2.gridx = 0;
        gbc2.gridy = 9;
        panelResumen.add(chasisI, gbc2);

        gbc2.gridx = 1;
        panelResumen.add(motorI, gbc2);

    }

    private void panelPolizaConfig(JPanel panelPoliza) {

        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        panelPoliza.setLayout(new GridBagLayout());
        panelPoliza.setBorder(border);

        PanelText poliza = new PanelText("Poliza", "BOLD", 18, "WEST");
        PanelText vigencia = new PanelText("Vigencia", "PLAIN", 18, "WEST");

        PanelText fechaInicio = new PanelText("Fecha inicio", "PLAIN", 16, "WEST");
        PanelText fechaFin = new PanelText("Fecha fin", "PLAIN", 16, "WEST");
        PanelText tipoPago = new PanelText("Tipo pago", "PLAIN", 16, "WEST");
        PanelText monto = new PanelText("Monto", "PLAIN", 16, "WEST");

        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd-MMMM-yyyy");
        
        PanelTextInput fechaInicioI = new PanelTextInput(sdf.format(clientePolizaInicio), 16);
        fechaInicioI.setEditable(false);
        PanelTextInput fechaFinI = new PanelTextInput(sdf.format(clientePolizaFin), 16);
        fechaFinI.setEditable(false);
        PanelTextInput tipoPagoI = new PanelTextInput(clienteFormaPago, 16);
        tipoPagoI.setEditable(false);
        PanelTextInput montoI = new PanelTextInput(totalAbonar, 16);
        montoI.setEditable(false);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.gridwidth = 3;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        //gbc2.insets = new Insets(10, 10, 10, 10);
        panelPoliza.add(poliza, gbc2);

        gbc2.gridwidth = 1;
        gbc2.gridy = 1;
        panelPoliza.add(vigencia, gbc2);

        gbc2.gridy = 2;
        panelPoliza.add(fechaInicio, gbc2);

        gbc2.gridx = 1;
        panelPoliza.add(fechaFin, gbc2);

        gbc2.gridx = 2;
        panelPoliza.add(tipoPago, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTH;
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        panelPoliza.add(fechaInicioI, gbc2);

        gbc2.gridx = 1;
        panelPoliza.add(fechaFinI, gbc2);

        gbc2.gridx = 2;
        panelPoliza.add(tipoPagoI, gbc2);

        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = 4;
        panelPoliza.add(monto, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTH;
        gbc2.gridy = 5;
        panelPoliza.add(montoI, gbc2);

    }

    private void panelCuotasConfig(JPanel panelCuotas, int cantidadCuotas) {

        CardLayout cl1 = new CardLayout();
        LineBorder border = new LineBorder(Color.LIGHT_GRAY, 2);

        //configurar panel container
        panelCuotas.setLayout(cl1);
        panelCuotas.setBorder(border);

        for (int i = 1; i <= cantidadCuotas; i++) {

            JPanel panel = new JPanel();

            panel.setLayout(new GridBagLayout());

            PanelText titulo = new PanelText("Cuota " + i, "BOLD", 20, "WEST");

            PanelText premio = new PanelText("Premio", "PLAIN", 18, "SOUTHWEST");
            PanelText importeDescuento = new PanelText("Importe por descuentos", "PLAIN", 18, "SOUTHWEST");
            PanelText importe = new PanelText("Importe cuota", "PLAIN", 18, "SOUTHWEST");
            PanelText inicioCuota = new PanelText("Inicio cuota", "PLAIN", 18, "SOUTHWEST");
            PanelText ultimoDia = new PanelText("Ultimo dia de pago", "PLAIN", 18, "SOUTHWEST");

            PanelText numero = new PanelText("" + i, "PLAIN", 18, "CENTER");

            
            PanelTextInput premioI = new PanelTextInput("", 18);
            PanelTextInput importeDescuentoI = new PanelTextInput("", 18);
            PanelTextInput importeI = new PanelTextInput("", 18);
            PanelTextInput inicioCuotaI = new PanelTextInput("", 18);
            PanelTextInput ultimoDiaI = new PanelTextInput("", 18);
            
            //CU16
            
            if(cantidadCuotas == 1){
                configurarCuotaSemestral(premioI,importeDescuentoI,importeI,inicioCuotaI,ultimoDiaI);
            }
            else{
                configurarCuotaMensual(i,premioI,importeDescuentoI,importeI,inicioCuotaI,ultimoDiaI);
            }

            Boton boton0 = new Boton("🡰");
            Boton boton1 = new Boton("🡲");

            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.fill = GridBagConstraints.BOTH;
            gbc2.gridwidth = 3;
            gbc2.weightx = 1;
            gbc2.weighty = 1;
            // gbc2.insets = new Insets(10, 10, 10, 10);

            gbc2.gridx = 0;
            gbc2.gridy = 0;
            panel.add(titulo, gbc2);

            gbc2.gridwidth = 1;
            gbc2.anchor = GridBagConstraints.SOUTHWEST;
            gbc2.gridy = 1;
            panel.add(premio, gbc2);

            gbc2.gridx = 1;
            panel.add(importeDescuento, gbc2);

            gbc2.gridx = 2;
            panel.add(importe, gbc2);

            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.anchor = GridBagConstraints.NORTHWEST;
            gbc2.gridx = 0;
            gbc2.gridy = 2;
            panel.add(premioI, gbc2);

            gbc2.gridx = 1;
            panel.add(importeDescuentoI, gbc2);

            gbc2.gridx = 2;
            panel.add(importeI, gbc2);

            gbc2.anchor = GridBagConstraints.SOUTHWEST;
            gbc2.gridx = 0;
            gbc2.gridy = 3;
            panel.add(inicioCuota, gbc2);

            gbc2.gridx = 1;
            panel.add(ultimoDia, gbc2);

            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.anchor = GridBagConstraints.NORTHWEST;
            gbc2.gridx = 0;
            gbc2.gridy = 4;
            panel.add(inicioCuotaI, gbc2);

            gbc2.gridx = 1;
            panel.add(ultimoDiaI, gbc2);

            JPanel panelBoton = new JPanel(new GridBagLayout());

            gbc2.fill = GridBagConstraints.NONE;
            gbc2.anchor = GridBagConstraints.CENTER;
            gbc2.gridx = 0;
            gbc2.gridy = 0;
            gbc2.insets = new Insets(0, 10, 0, 0);
            panelBoton.add(boton0, gbc2);

            gbc2.insets = new Insets(10, 5, 10, 15);
            gbc2.gridx = 1;
            panelBoton.add(numero, gbc2);

            gbc2.gridx = 2;
            panelBoton.add(boton1, gbc2);

            gbc2.gridheight = 2;
            gbc2.gridx = 2;
            gbc2.gridy = 3;
            panel.add(panelBoton, gbc2);

            panelCuotas.add(panel, "" + i);

            int a = i;

            boton0.addActionListener((ActionEvent e) -> {
                cl1.show(panelCuotas, "" + (a - 1));
            });

            boton1.addActionListener((ActionEvent e) -> {
                cl1.show(panelCuotas, "" + (a + 1));
            });

        }

        cl1.show(panelCuotas, "" + 1);

    }
    
    private void configurarCuotaMensual(int i, PanelTextInput premioI,PanelTextInput importeDescuentoI,PanelTextInput importeI,PanelTextInput inicioCuotaI,PanelTextInput ultimoDiaI){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        premioI.setText(prima);
        premioI.setEditable(false);
        importeDescuentoI.setText(descuentos);
        importeDescuentoI.setEditable(false);
        importeI.setText("CALCULAR");
        importeI.setEditable(false);
        
        Calendar calendar = Calendar.getInstance();
        if(i == 1){
            clienteFechaInicioCuota = calendar.getTime();
        }
        else{
            calendar.setTime(clientePolizaInicio);
            calendar.add(Calendar.MONTH, +i-2);  
            clienteFechaInicioCuota = calendar.getTime();  
        }
        
        calendar.setTime(clientePolizaInicio);
        calendar.add(Calendar.DAY_OF_MONTH, -1); 
        calendar.add(Calendar.MONTH, i-1); 
        clienteUltimoDiaPagoCuota = calendar.getTime();
        
        /*
        LocalDate inicioPoliza = clientePolizaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate inicioCuota = inicioPoliza.plusMonths(i-1);
        clienteFechaInicioCuota = Date.from(inicioCuota.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        inicioPoliza = clienteFechaInicioCuota.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        inicioCuota = inicioPoliza.plusMonths(1);
        clienteUltimoDiaPagoCuota = Date.from(inicioCuota.atStartOfDay(ZoneId.systemDefault()).toInstant());
              */  
        inicioCuotaI.setText(sdf.format(clienteFechaInicioCuota));
        inicioCuotaI.setEditable(false);
        ultimoDiaI.setText(sdf.format(clienteUltimoDiaPagoCuota));
        ultimoDiaI.setEditable(false);
        
    }
    
    private void configurarCuotaSemestral(PanelTextInput premioI,PanelTextInput importeDescuentoI,PanelTextInput importeI,PanelTextInput inicioCuotaI,PanelTextInput ultimoDiaI){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        premioI.setText(prima);
        premioI.setEditable(false);
        importeDescuentoI.setText(descuentos);
        importeDescuentoI.setEditable(false);
        importeI.setText("CALCULAR");
        importeI.setEditable(false);
        
        Calendar calendar = Calendar.getInstance();
        clienteFechaInicioCuota = calendar.getTime();
        calendar.setTime(clientePolizaInicio);
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        clienteUltimoDiaPagoCuota = calendar.getTime();
        
        inicioCuotaI.setText(sdf.format(clienteFechaInicioCuota));
        inicioCuotaI.setEditable(false);
        ultimoDiaI.setText(sdf.format(clienteUltimoDiaPagoCuota));
        ultimoDiaI.setEditable(false);
        
    }

    private void actualizarListaProvincias(){
       String[] provincias = new String[listaProvincias.size()];
        for (int i = 0; i < listaProvincias.size(); i++) {
            provincias[i] = listaProvincias.get(i).getNombreProvincia();
        }
        provincia.setItems(provincias); 
    }
    
    private void  actualizarListaLocalidades(){
        String[] localidades = new String[listaLocalidades.size()];
        for (int i = 0; i < listaLocalidades.size(); i++) {
            localidades[i] = listaLocalidades.get(i).getNombreLocalidad();
        }
        localidad.setItems(localidades); 
    }

    void cambiarPantalla(String pantalla) {
        //cambia el panel visible
        cl.show(containerPanel, pantalla);
    }

    void actualizarPrimera(ClienteDTO cliente) {
        clienteDTO = cliente;
        primeraConfig();
        containerPanel.add(primera, "1");

    }

    void llenarPDF(PDDocument document) throws IOException{
              
            PDAcroForm pDAcroForm = document.getDocumentCatalog().getAcroForm();
            PDField field = pDAcroForm.getField("Nombre");
            field.setValue(clienteDTO.getNombre());
            field = pDAcroForm.getField("NroCliente");
            field.setValue(clienteDTO.getNumCliente());
            field = pDAcroForm.getField("DomicilioRiesgo");
            field.setValue(clienteDTO.getDomicilioDTO().getCalle() + clienteDTO.getDomicilioDTO().getNumero());
            field = pDAcroForm.getField("TipoDocumento");
            field.setValue(clienteDTO.getTipoDocumento());
            field = pDAcroForm.getField("NroDocumento");
            field.setValue(clienteDTO.getNumDocumento());
            field = pDAcroForm.getField("Marca");
            field.setValue(vehiculoDTO.getMarcaVehiculo());
            field = pDAcroForm.getField("Modelo");
            field.setValue(vehiculoDTO.getModeloVehiculo());
            field = pDAcroForm.getField("Patente");
            field.setValue(vehiculoDTO.getNumPatente());
            field = pDAcroForm.getField("AnioFabricacion");
            field.setValue(vehiculoDTO.getAnioVehiculo());
            field = pDAcroForm.getField("Motor");
            field.setValue(vehiculoDTO.getNumMotor());
            field = pDAcroForm.getField("Chasis");
            field.setValue(vehiculoDTO.getNumChasis());
            field = pDAcroForm.getField("NroPoliza");
            field.setValue(clienteNumPoliza);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
            field = pDAcroForm.getField("InicioVigencia");
            field.setValue(sdf.format(clientePolizaInicio));
            field = pDAcroForm.getField("FinVigencia");
            field.setValue(sdf.format(clientePolizaFin));
            field = pDAcroForm.getField("TipoCobertura");
            field.setValue(clienteTipoCob);
            field = pDAcroForm.getField("SumaAsegurada");
            field.setValue(clienteSumaAsegurada);
            field = pDAcroForm.getField("FormaPago");
            field.setValue(clienteFormaPago);
            field = pDAcroForm.getField("UltimoDiaPago");
            field.setValue(sdf.format(clienteUltimoDiaPagoCuota));
            field = pDAcroForm.getField("Prima");
            field.setValue(prima);
            field = pDAcroForm.getField("DerechosEmision");
            field.setValue(derechosEmision);
            field = pDAcroForm.getField("Descuentos");
            field.setValue(descuentos);
            field = pDAcroForm.getField("TotalAbonar");
            field.setValue(totalAbonar);
            
            Calendar calendar = Calendar.getInstance();
            field = pDAcroForm.getField("Fecha");
            field.setValue(sdf.format(calendar.getTime()));
            field = pDAcroForm.getField("Agente");
            field.setValue("TO DO");
            field = pDAcroForm.getField("AgenteCorreo");
            field.setValue("TO DO");
            field = pDAcroForm.getField("AgenteTelefono");
            field.setValue("TO DO");
            
        
    }
}
