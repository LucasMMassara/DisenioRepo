package gestores;
import daos.DAOVehiculo;
import dto.VehiculoDTO;
import logica.Vehiculo;

public class GestorVehiculo {
	
    public Vehiculo DTOaVehiculo(VehiculoDTO vehiculo) {
        
        //Se pasa a dto y se guarda en la BBDD TODO
        
        DAOVehiculo daov = new DAOVehiculo();
        
        Vehiculo v = new Vehiculo();
        
        v.setTuercasAntirobo(vehiculo.getTuercasAntirrobo());
        v.setAlarma(vehiculo.getTieneAlarma());
        v.setDispositivoRastreo(vehiculo.getDispositivoRastreo());
        v.setGuardaEnGarage(vehiculo.getGuardaEnGarage());
        
        v.setAnioFabricacionVehiculo(new GestorModelo().obtenerAnioFabricacion(vehiculo.getAnioVehiculo()));
        
        v.setKmPorAnio(Integer.parseInt(vehiculo.getKmPorAnio()));
        v.setNumChasis(vehiculo.getNumChasis());
        v.setNumMotor(vehiculo.getNumMotor());
        v.setPatente(vehiculo.getNumPatente());
        
        v.setEstRobo(new GestorModelo().obtenerEstadisticaRobo(vehiculo.getModelo().getId()));
        
        return v;
    }
}
