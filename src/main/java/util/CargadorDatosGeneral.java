package util;

public class CargadorDatosGeneral {
    
    public void cargarDatos(){
        
        new CargadorDatosMarca().cargarTodo();
        new CargadorDatosPais().cargarPackPaisesLocIndic();
        new CargadorDatosValoresCalculo().cargarDatosValoresCalculo();
        new CargadorDatosCobertura().agregarCoberturas();
        CargadorUsuario.cargar();
        
    }
    
}
