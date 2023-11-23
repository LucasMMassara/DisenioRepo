package gestores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestorFecha {
    
    public Date obtenerFechaActual(){
        Date date =  new Date();
        return date;
    }
    
    public int obtenerAnios(Date fechaNacimiento){
        Date fechaActual = new Date();
        // validate inputs ...                                                                               
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
        int d1 = Integer.parseInt(formatter.format(fechaNacimiento));                            
        int d2 = Integer.parseInt(formatter.format(fechaActual));                          
        int age = (d2 - d1) / 10000;                                                       
        return age;  
    }
    
}
