/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import GUI.*;
import dto.ClienteDTO;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import gestores.GestorClientes;
import gestores.GestorLocalidad;
import gestores.GestorPais;
import gestores.GestorProvincias;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import logica.Cliente;
import logica.Domicilio;
import logica.EstadoCivil;
import logica.Iva;
import logica.Localidad;
import logica.Pais;
import logica.Provincia;
import logica.TipoSexo;

/**
 *
 * @author valec
 */
public class AltaClientePrimera extends javax.swing.JPanel {

    /**
     * Creates new form primeraAltaCliente
     */
    
    String nombreD = "";
    String apellidoD = "";
    String tipoDocumentoD = "";
    String nroDocumentoD = "";
    String nroCuilD = "";
    String sexoD = "";
    Date fechaNacimientoD;
    String condicionIvaD = "";
    String correoElectronicoD = "";
    String estadoCivilD = "";
    String profesionD = "";
    String anioRegistroD = "";
    String calleD = "";
    String numeroD = "";
    String pisoD = "";
    String dptoD = "";
    LocalidadDTO localidadD;
    String codigoPostalD = "";
    String numClienteD = "";
    
    ClienteDTO cliente = new ClienteDTO();
    DomicilioDTO domicilio = new DomicilioDTO();
    
    List<PaisDTO> listaPaises;
    List<ProvinciaDTO> listaProvincias;
    List<LocalidadDTO> listaLocalidades;
    
    String[] defaults = {""};
    PanelDropDown pais = new PanelDropDown(defaults);
    PanelDropDown provincia = new PanelDropDown(defaults);
    PanelDropDown localidad = new PanelDropDown(defaults);
    
    public AltaClientePrimera(MenuProductorSeguros main) {
        initComponents();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        
        Boton volver = new Boton("VOLVER",16, false);
        jPanel20.add(volver,gbc);
        Boton confirmar = new Boton("CONFIRMAR",16, false);
        jPanel21.add(confirmar,gbc);
        
        PanelTextInput nombre = new PanelTextInput("nombre",16,0,0,0,0);
        nombre.restrictToLettersPlusSpace();
        
        PanelTextInput apellido = new PanelTextInput("apellido",16,0,0,0,0);
        apellido.restrictToLettersPlusSpace();
        
        String[] tipos = {"DNI","CC", "CI", "CIC"};
        PanelDropDown tipoDocumento = new PanelDropDown(tipos);
        
        PanelTextInput nroDocumento = new PanelTextInput("nroDocumento",16,0,0,0,0);
        nroDocumento.restrictToNumbers();
        nroDocumento.restrictSize(8);
        
        tipoDocumento.addCustomPanelListener(new CustomPanelListener() {
            @Override
            public void onPanelItemSelected(PanelDropDown source, String selectedItem) {

                switch (selectedItem) {
                    case "CC":
                        nroDocumento.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "CI":
                        nroDocumento.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "CIC":
                        nroDocumento.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "DNI":
                        nroDocumento.restrictSize(8);
                        // limitar nroDocumentoI acordemente
                        break;
                }
            }
        });
        
        PanelTextInputCUIL nroCuil = new PanelTextInputCUIL(16);
        
        List<String> listaSexos = Stream.of(TipoSexo.values())
                               .map(Enum::name)
                               .collect(Collectors.toList());
        String[] sexos = listaSexos.toArray(new String[0]);
        
        PanelDropDown sexo = new PanelDropDown(sexos);
        
        PanelDatePicker fechaNacimiento = new PanelDatePicker();
        Calendar calendar = Calendar.getInstance();
        fechaNacimiento.setMaxSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, -120);
        fechaNacimiento.setMinSelectableDate(calendar.getTime());
        
        PanelTextInput calle = new PanelTextInput("calle",16,0,0,0,0);
        calle.restrictToLettersPlusSpace();
        
        PanelTextInput numero = new PanelTextInput("numero",16,0,0,0,0);
        numero.restrictSize(5);
        numero.restrictToNumbers();
        
        PanelTextInput piso = new PanelTextInput("piso",16,0,0,0,0);
        piso.restrictToNumbers();
        piso.restrictSize(2);
        
        PanelTextInput dpto = new PanelTextInput("dpto",16,0,0,0,0);
        dpto.restrictToNumbers();
        dpto.restrictSize(3);
        
        try{
        GestorPais gp = new GestorPais();
        GestorProvincias gpr = new GestorProvincias();
        GestorLocalidad gl = new GestorLocalidad();
        listaPaises = gp.getPaisesDTO();
        listaProvincias = gpr.getProvinciasDTO(listaPaises.get(0).getId());
        listaLocalidades = gl.getLocalidadesDTO(listaProvincias.get(0).getId());
        
        
        String[] paises = new String[listaPaises.size()];
        for (int i = 0; i < listaPaises.size(); i++) {
            paises[i] = listaPaises.get(i).getNombre();
        }
        pais = new PanelDropDown(paises);
        
        String[] provincias = new String[listaProvincias.size()];
        for (int i = 0; i < listaProvincias.size(); i++) {
            provincias[i] = listaProvincias.get(i).getNombre();
        }
        provincia = new PanelDropDown(provincias);
        
        String[] localidades = new String[listaLocalidades.size()];
        for (int i = 0; i < listaLocalidades.size(); i++) {
            localidades[i] = listaLocalidades.get(i).getNombre();
        }
        localidad = new PanelDropDown(localidades);
        
        pais.addCustomPanelListener(new CustomPanelListener() {
                @Override
                public void onPanelItemSelected(PanelDropDown source, String selectedItem) {
                    for (PaisDTO p : listaPaises) {
                        if (p.getNombre().equals(selectedItem)) {
                            listaProvincias = gpr.getProvinciasDTO(p.getId());
                            listaLocalidades = gl.getLocalidadesDTO(listaProvincias.get(0).getId());
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
                    for (ProvinciaDTO p : listaProvincias) {
                        if (p.getNombre().equals(selectedItem)) {
                            listaLocalidades = gl.getLocalidadesDTO(p.getId());
                            actualizarListaLocalidades();
                            break;
                        }
                    }
                }
            });
        }
        catch(Exception e){
            
        }
        
        
        PanelTextInput codigoPostal = new PanelTextInput("codigoPostal",16,0,0,0,0);
        codigoPostal.restrictToNumbers();
        codigoPostal.restrictSize(5);
        
        List<String> listaCondiciones = Stream.of(Iva.values())
                               .map(Enum::name)
                               .collect(Collectors.toList());
       
        String[] condiciones = listaCondiciones.toArray(new String[0]);
        
        PanelDropDown condicionIva = new PanelDropDown(condiciones);
        
        PanelTextInput correoElectronico = new PanelTextInput("",16,0,0,0,0);
        
        List<String> listaEstados = Stream.of(EstadoCivil.values())
                               .map(Enum::name)
                               .collect(Collectors.toList());
       
        String[] estados = listaEstados.toArray(new String[0]);
        
        PanelDropDown estadoCivil = new PanelDropDown(estados);
        
        PanelTextInput profesion = new PanelTextInput("profesion",16,0,0,0,0);
        profesion.restrictToLettersPlusSpace();
        
        PanelTextInput anioRegistro = new PanelTextInput("anioRegistro",16,0,0,0,0);
        anioRegistro.restrictSize(4);
        anioRegistro.restrictToNumbers();
        
        //TO DO CONFIGURAR
        PanelTextInput nroCliente = new PanelTextInput("",16,0,0,0,0);
        nroCliente.setEditable(false);
        
        volver.addActionListener((ActionEvent e) -> {
            main.cambiarPantalla("1");
        });
        
        List<PanelTextInput> listaPaneles = new ArrayList<>();

        // Add objects of different types to the list
        listaPaneles.add(nombre);
        listaPaneles.add(apellido);
        listaPaneles.add(nroDocumento);
        listaPaneles.add(correoElectronico);
        listaPaneles.add(profesion);
        listaPaneles.add(anioRegistro);
        listaPaneles.add(calle);
        listaPaneles.add(numero);
        listaPaneles.add(codigoPostal);

        confirmar.addActionListener((ActionEvent e) -> {

            GestorClientes gc = new GestorClientes();
            
            //chequear inputs
            boolean inputVacio = false;
        
            for(PanelTextInput panel: listaPaneles ){

                if(panel.getText().trim().isEmpty()){
                    inputVacio = true;
                    panel.setWrongInput();
                }
                else{
                    panel.setCorrectInput();
                }
            }
                        
            if("00-00000000-0".equals(nroCuil.getText())){
                inputVacio = true;
                nroCuil.setWrongInput();
            }
            else{
                nroCuil.setCorrectInput();
            }
            
            boolean emailvalido = gc.emailValido(correoElectronico.getText());
            
            if(!(emailvalido)){
                correoElectronico.setWrongInput();
            }
            else{
                correoElectronico.setCorrectInput();
            }
            

            fechaNacimientoD = fechaNacimiento.getDate();
            Boolean clienteMayor = true;
            if(fechaNacimientoD != null){
                clienteMayor = gc.clienteMayorEdad(fechaNacimientoD);
            }
        
            
            if (inputVacio || fechaNacimientoD == null || emailvalido == false || !clienteMayor) {
                
                if(inputVacio){
                    VentanaError entradasVaciasError = new VentanaError("Faltan datos obligatorios", "Entrada incorrecta");
                }
                else if(emailvalido == false){
                    VentanaError fechaVaciaError = new VentanaError("El email ingresado es incorrecto","Entrada incorrecta");
                }
                else if(fechaNacimientoD == null){
                    VentanaError fechaVaciaError = new VentanaError("Debe seleccionar una fecha de nacimiento","Entrada incorrecta");
                }
                else if(!clienteMayor){
                    VentanaError clienteMenor = new VentanaError("El cliente debe ser mayor de edad","Entrada incorrecta");
                }
            }
            else {        
                
                nombreD = nombre.getText().replaceFirst("^\\s+", "");
                apellidoD = apellido.getText().replaceFirst("^\\s+", "");
                tipoDocumentoD = tipoDocumento.getSelectedItem();
                nroDocumentoD = nroDocumento.getText();
                nroCuilD = nroCuil.getText();
                sexoD = sexo.getSelectedItem();
                condicionIvaD = condicionIva.getSelectedItem();
                correoElectronicoD = correoElectronico.getText();
                estadoCivilD = estadoCivil.getSelectedItem();
                profesionD = profesion.getText().replaceFirst("^\\s+", "");
                anioRegistroD = anioRegistro.getText();
                calleD = calle.getText().replaceFirst("^\\s+", "");
                numeroD = numero.getText();
                pisoD = piso.getText();
                dptoD = dpto.getText();
                codigoPostalD = codigoPostal.getText(); 

                for(LocalidadDTO loc: listaLocalidades){
                    if(localidad.getSelectedItem().equals(loc.getNombre())){
                        localidadD = loc;
                    }
                }

                armarDTO();
                Cliente cli;
                
                if(gc.existeClienteActivo(cliente)){
                    VentanaError existeCliente = new VentanaError("Ya existe un cliente activo con este CUIL/DNI","Cliente existente");
                }
                else{
                    cli = gc.crearCliente(cliente);
                    
                    //presentar resultado
                    String nroEncontrado = cli.getNumCliente();
                    nroCliente.setText(nroEncontrado);
                    VentanaError confirmacion = new VentanaError("Numero de cliente encontrado: " + nroEncontrado, "Numero Cliente");
                }
                
            }
            
        });
        
        jPanel5.add(nombre,gbc);
        jPanel6.add(apellido,gbc);
        jPanel9.add(tipoDocumento,gbc);
        jPanel31.add(nroDocumento,gbc);
        jPanel10.add(nroCuil,gbc);
        jPanel11.add(sexo,gbc);
        jPanel12.add(fechaNacimiento,gbc);
        jPanel32.add(condicionIva,gbc);
        jPanel13.add(correoElectronico,gbc);
        jPanel14.add(estadoCivil,gbc);
        jPanel27.add(profesion,gbc);
        jPanel33.add(anioRegistro,gbc);
        jPanel23.add(calle,gbc);
        jPanel24.add(numero,gbc);
        jPanel25.add(piso,gbc);
        jPanel26.add(dpto,gbc);
        jPanel15.add(pais,gbc);
        jPanel16.add(provincia,gbc);
        jPanel22.add(localidad,gbc);
        jPanel17.add(codigoPostal,gbc);
        jPanel30.add(nroCliente,gbc);     
    }
    
     public AltaClientePrimera(AltaPoliza main) {
        initComponents();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        
        Boton volver = new Boton("VOLVER",16, false);
        jPanel20.add(volver,gbc);
        Boton confirmar = new Boton("CONFIRMAR",16, false);
        jPanel21.add(confirmar,gbc);
        
        PanelTextInput nombre = new PanelTextInput("nombre",16,0,0,0,0);
        nombre.restrictToLettersPlusSpace();
        
        PanelTextInput apellido = new PanelTextInput("apellido",16,0,0,0,0);
        apellido.restrictToLettersPlusSpace();
        
        String[] tipos = {"DNI","CC", "CI", "CIC"};
        PanelDropDown tipoDocumento = new PanelDropDown(tipos);
        
        PanelTextInput nroDocumento = new PanelTextInput("nroDocumento",16,0,0,0,0);
        nroDocumento.restrictToNumbers();
        nroDocumento.restrictSize(8);
        
        tipoDocumento.addCustomPanelListener(new CustomPanelListener() {
            @Override
            public void onPanelItemSelected(PanelDropDown source, String selectedItem) {

                switch (selectedItem) {
                    case "CC":
                        nroDocumento.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "CI":
                        nroDocumento.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "CIC":
                        nroDocumento.restrictSize(4);
                        // limitar nroDocumentoI acordemente
                        break;
                    case "DNI":
                        nroDocumento.restrictSize(8);
                        // limitar nroDocumentoI acordemente
                        break;
                }
            }
        });
        
        PanelTextInputCUIL nroCuil = new PanelTextInputCUIL(16);
        
        List<String> listaSexos = Stream.of(TipoSexo.values())
                               .map(Enum::name)
                               .collect(Collectors.toList());
        String[] sexos = listaSexos.toArray(new String[0]);
        
        PanelDropDown sexo = new PanelDropDown(sexos);
        
        PanelDatePicker fechaNacimiento = new PanelDatePicker();
        Calendar calendar = Calendar.getInstance();
        fechaNacimiento.setMaxSelectableDate(calendar.getTime());
        calendar.add(Calendar.YEAR, -120);
        fechaNacimiento.setMinSelectableDate(calendar.getTime());
        
        PanelTextInput calle = new PanelTextInput("calle",16,0,0,0,0);
        calle.restrictToLettersPlusSpace();
        
        PanelTextInput numero = new PanelTextInput("numero",16,0,0,0,0);
        numero.restrictSize(5);
        numero.restrictToNumbers();
        
        PanelTextInput piso = new PanelTextInput("piso",16,0,0,0,0);
        piso.restrictToNumbers();
        piso.restrictSize(2);
        
        PanelTextInput dpto = new PanelTextInput("dpto",16,0,0,0,0);
        dpto.restrictToNumbers();
        dpto.restrictSize(3);
        
        try{
        GestorPais gp = new GestorPais();
        GestorProvincias gpr = new GestorProvincias();
        GestorLocalidad gl = new GestorLocalidad();
        listaPaises = gp.getPaisesDTO();
        listaProvincias = gpr.getProvinciasDTO(listaPaises.get(0).getId());
        listaLocalidades = gl.getLocalidadesDTO(listaProvincias.get(0).getId());
        
        
        String[] paises = new String[listaPaises.size()];
        for (int i = 0; i < listaPaises.size(); i++) {
            paises[i] = listaPaises.get(i).getNombre();
        }
        pais = new PanelDropDown(paises);
        
        String[] provincias = new String[listaProvincias.size()];
        for (int i = 0; i < listaProvincias.size(); i++) {
            provincias[i] = listaProvincias.get(i).getNombre();
        }
        provincia = new PanelDropDown(provincias);
        
        String[] localidades = new String[listaLocalidades.size()];
        for (int i = 0; i < listaLocalidades.size(); i++) {
            localidades[i] = listaLocalidades.get(i).getNombre();
        }
        localidad = new PanelDropDown(localidades);
        
        pais.addCustomPanelListener(new CustomPanelListener() {
                @Override
                public void onPanelItemSelected(PanelDropDown source, String selectedItem) {
                    for (PaisDTO p : listaPaises) {
                        if (p.getNombre().equals(selectedItem)) {
                            listaProvincias = gpr.getProvinciasDTO(p.getId());
                            listaLocalidades = gl.getLocalidadesDTO(listaProvincias.get(0).getId());
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
                    for (ProvinciaDTO p : listaProvincias) {
                        if (p.getNombre().equals(selectedItem)) {
                            listaLocalidades = gl.getLocalidadesDTO(p.getId());
                            actualizarListaLocalidades();
                            break;
                        }
                    }
                }
            });
        }
        catch(Exception e){
            
        }
        
        
        PanelTextInput codigoPostal = new PanelTextInput("codigoPostal",16,0,0,0,0);
        codigoPostal.restrictToNumbers();
        codigoPostal.restrictSize(5);
        
        List<String> listaCondiciones = Stream.of(Iva.values())
                               .map(Enum::name)
                               .collect(Collectors.toList());
       
        String[] condiciones = listaCondiciones.toArray(new String[0]);
        
        PanelDropDown condicionIva = new PanelDropDown(condiciones);
        
        PanelTextInput correoElectronico = new PanelTextInput("",16,0,0,0,0);
        
        List<String> listaEstados = Stream.of(EstadoCivil.values())
                               .map(Enum::name)
                               .collect(Collectors.toList());
       
        String[] estados = listaEstados.toArray(new String[0]);
        
        PanelDropDown estadoCivil = new PanelDropDown(estados);
        
        PanelTextInput profesion = new PanelTextInput("profesion",16,0,0,0,0);
        profesion.restrictToLettersPlusSpace();
        
        PanelTextInput anioRegistro = new PanelTextInput("anioRegistro",16,0,0,0,0);
        anioRegistro.restrictSize(4);
        anioRegistro.restrictToNumbers();
        
        //TO DO CONFIGURAR
        PanelTextInput nroCliente = new PanelTextInput("",16,0,0,0,0);
        nroCliente.setEditable(false);
        
        volver.addActionListener((ActionEvent e) -> {
            main.cambiarPantalla("1");
        });
        
        List<PanelTextInput> listaPaneles = new ArrayList<>();

        // Add objects of different types to the list
        listaPaneles.add(nombre);
        listaPaneles.add(apellido);
        listaPaneles.add(nroDocumento);
        listaPaneles.add(correoElectronico);
        listaPaneles.add(profesion);
        listaPaneles.add(anioRegistro);
        listaPaneles.add(calle);
        listaPaneles.add(numero);
        listaPaneles.add(codigoPostal);

        confirmar.addActionListener((ActionEvent e) -> {

            GestorClientes gc = new GestorClientes();
            
            //chequear inputs
            boolean inputVacio = false;
        
            for(PanelTextInput panel: listaPaneles ){

                if(panel.getText().trim().isEmpty()){
                    inputVacio = true;
                    panel.setWrongInput();
                }
                else{
                    panel.setCorrectInput();
                }
            }
                        
            if("00-00000000-0".equals(nroCuil.getText())){
                inputVacio = true;
                nroCuil.setWrongInput();
            }
            else{
                nroCuil.setCorrectInput();
            }
            
            boolean emailvalido = gc.emailValido(correoElectronico.getText());
            
            if(!(emailvalido)){
                correoElectronico.setWrongInput();
            }
            else{
                correoElectronico.setCorrectInput();
            }
            

            fechaNacimientoD = fechaNacimiento.getDate();
            Boolean clienteMayor = true;
            if(fechaNacimientoD != null){
                clienteMayor = gc.clienteMayorEdad(fechaNacimientoD);
            }
        
            
            if (inputVacio || fechaNacimientoD == null || emailvalido == false || !clienteMayor) {
                
                if(inputVacio){
                    VentanaError entradasVaciasError = new VentanaError("Faltan datos obligatorios", "Entrada incorrecta");
                }
                else if(emailvalido == false){
                    VentanaError fechaVaciaError = new VentanaError("El email ingresado es incorrecto","Entrada incorrecta");
                }
                else if(fechaNacimientoD == null){
                    VentanaError fechaVaciaError = new VentanaError("Debe seleccionar una fecha de nacimiento","Entrada incorrecta");
                }
                else if(!clienteMayor){
                    VentanaError clienteMenor = new VentanaError("El cliente debe ser mayor de edad","Entrada incorrecta");
                }
            }
            else {        
                
                nombreD = nombre.getText().replaceFirst("^\\s+", "");
                apellidoD = apellido.getText().replaceFirst("^\\s+", "");
                tipoDocumentoD = tipoDocumento.getSelectedItem();
                nroDocumentoD = nroDocumento.getText();
                nroCuilD = nroCuil.getText();
                sexoD = sexo.getSelectedItem();
                condicionIvaD = condicionIva.getSelectedItem();
                correoElectronicoD = correoElectronico.getText();
                estadoCivilD = estadoCivil.getSelectedItem();
                profesionD = profesion.getText().replaceFirst("^\\s+", "");
                anioRegistroD = anioRegistro.getText();
                calleD = calle.getText().replaceFirst("^\\s+", "");
                numeroD = numero.getText();
                pisoD = piso.getText();
                dptoD = dpto.getText();
                codigoPostalD = codigoPostal.getText(); 

                for(LocalidadDTO loc: listaLocalidades){
                    if(localidad.getSelectedItem().equals(loc.getNombre())){
                        localidadD = loc;
                    }
                }

                armarDTO();
                Cliente cli;
                
                if(gc.existeClienteActivo(cliente)){
                    VentanaError existeCliente = new VentanaError("Ya existe un cliente activo con este CUIL/DNI","Cliente existente");
                }
                else{
                    cli = gc.crearCliente(cliente);
                    
                    //presentar resultado
                    String nroEncontrado = cli.getNumCliente();
                    nroCliente.setText(nroEncontrado);
                    VentanaError confirmacion = new VentanaError("Numero de cliente encontrado: " + nroEncontrado, "Numero Cliente");
                }
                
            }
            
        });
        
        jPanel5.add(nombre,gbc);
        jPanel6.add(apellido,gbc);
        jPanel9.add(tipoDocumento,gbc);
        jPanel31.add(nroDocumento,gbc);
        jPanel10.add(nroCuil,gbc);
        jPanel11.add(sexo,gbc);
        jPanel12.add(fechaNacimiento,gbc);
        jPanel32.add(condicionIva,gbc);
        jPanel13.add(correoElectronico,gbc);
        jPanel14.add(estadoCivil,gbc);
        jPanel27.add(profesion,gbc);
        jPanel33.add(anioRegistro,gbc);
        jPanel23.add(calle,gbc);
        jPanel24.add(numero,gbc);
        jPanel25.add(piso,gbc);
        jPanel26.add(dpto,gbc);
        jPanel15.add(pais,gbc);
        jPanel16.add(provincia,gbc);
        jPanel22.add(localidad,gbc);
        jPanel17.add(codigoPostal,gbc);
        jPanel30.add(nroCliente,gbc);     
    }
    
    private void actualizarListaProvincias(){
       String[] provincias = new String[listaProvincias.size()];
        for (int i = 0; i < listaProvincias.size(); i++) {
            provincias[i] = listaProvincias.get(i).getNombre();
        }
        provincia.setItems(provincias); 
    }
    
    private void actualizarListaLocalidades(){
        String[] localidades = new String[listaLocalidades.size()];
        for (int i = 0; i < listaLocalidades.size(); i++) {
            localidades[i] = listaLocalidades.get(i).getNombre();
        }
        localidad.setItems(localidades); 
    }

    private void armarDTO(){
        
        cliente = new ClienteDTO(nombreD,apellidoD,nroDocumentoD,nroCuilD,tipoDocumentoD,sexoD,condicionIvaD,estadoCivilD,correoElectronicoD,profesionD,anioRegistroD,numClienteD,fechaNacimientoD);
        domicilio = new DomicilioDTO(calleD,numeroD,pisoD,dptoD,localidadD,codigoPostalD);
        cliente.setDomicilioDTO(domicilio);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel11.setText("jLabel11");

        jLabel12.setText("jLabel12");

        setBackground(new java.awt.Color(0, 255, 51));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(153, 255, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel2, gridBagConstraints);

        jPanel18.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel18.setOpaque(false);
        jPanel18.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabel22.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Alta cliente");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jLabel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 786;
        gridBagConstraints.ipady = 66;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 11);
        add(jPanel18, gridBagConstraints);
        /*
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        PanelText titulo = new PanelText("Alta Cliente","BOLD", 20,"SOUTHWEST");
        titulo.setBackgroundColor(255,255,255,0);
        jPanel18.add(titulo,gbc);*/

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel19.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel28.setPreferredSize(new java.awt.Dimension(300, 130));
        jPanel28.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        jPanel28.add(jPanel8, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 5);
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel6.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel1.add(jPanel6, gridBagConstraints);

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel9.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel1.add(jPanel9, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nombre");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Apellido");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Tipo documento");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel4, gridBagConstraints);

        jPanel31.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 10);
        jPanel1.add(jPanel31, gridBagConstraints);

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Nro.Documento");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel13.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        jPanel1.add(jLabel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel1, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel10.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 5);
        jPanel3.add(jPanel10, gridBagConstraints);

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel11.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel3.add(jPanel11, gridBagConstraints);

        jPanel12.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel12.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel3.add(jPanel12, gridBagConstraints);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Nro.Cuil");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Sexo");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Fecha nacimiento");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        jPanel32.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 10);
        jPanel3.add(jPanel32, gridBagConstraints);

        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Condición IVA");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel23.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        jPanel3.add(jLabel23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel13.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel13.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 5);
        jPanel4.add(jPanel13, gridBagConstraints);

        jPanel14.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel14.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel4.add(jPanel14, gridBagConstraints);

        jPanel27.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel27.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel4.add(jPanel27, gridBagConstraints);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Correo electrónico");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel8.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel4.add(jLabel8, gridBagConstraints);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Estado civil");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel9.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel4.add(jLabel9, gridBagConstraints);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Profesión");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel4.add(jLabel10, gridBagConstraints);

        jPanel33.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 10);
        jPanel4.add(jPanel33, gridBagConstraints);

        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Año registro");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel24.setMinimumSize(new java.awt.Dimension(0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        jPanel4.add(jLabel24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 780;
        gridBagConstraints.ipady = 190;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel19.add(jPanel28, gridBagConstraints);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel29.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel15, gridBagConstraints);

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel16, gridBagConstraints);

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));
        jPanel17.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel17, gridBagConstraints);

        jPanel22.setBackground(new java.awt.Color(204, 204, 204));
        jPanel22.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel22, gridBagConstraints);

        jPanel23.setBackground(new java.awt.Color(204, 204, 204));
        jPanel23.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel23, gridBagConstraints);

        jPanel24.setBackground(new java.awt.Color(204, 204, 204));
        jPanel24.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel24, gridBagConstraints);

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));
        jPanel25.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel25, gridBagConstraints);

        jPanel26.setBackground(new java.awt.Color(204, 204, 204));
        jPanel26.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel29.add(jPanel26, gridBagConstraints);

        jLabel14.setText("Calle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel14, gridBagConstraints);

        jLabel15.setText("Numero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel15, gridBagConstraints);

        jLabel16.setText("Piso");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel16, gridBagConstraints);

        jLabel17.setText("Dpto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel17, gridBagConstraints);

        jLabel18.setText("Pais");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel18, gridBagConstraints);

        jLabel19.setText("Provincia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel19, gridBagConstraints);

        jLabel20.setText("Localidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel20, gridBagConstraints);

        jLabel21.setText("Codigo postal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 780;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel19.add(jPanel29, gridBagConstraints);

        jPanel30.setBackground(new java.awt.Color(204, 204, 204));
        jPanel30.setMaximumSize(new java.awt.Dimension(100, 15));
        jPanel30.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.08;
        gridBagConstraints.insets = new java.awt.Insets(4, 580, 0, 6);
        jPanel19.add(jPanel30, gridBagConstraints);

        jLabel1.setText("Nro. Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.02;
        gridBagConstraints.insets = new java.awt.Insets(10, 650, 0, 0);
        jPanel19.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 786;
        gridBagConstraints.ipady = 350;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 11);
        add(jPanel19, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 213;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 213;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(10, 360, 10, 11);
        add(jPanel21, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
