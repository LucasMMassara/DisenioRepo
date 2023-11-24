package gestores;
import dto.VehiculoDTO;
import logica.Vehiculo;

public class GestorVehiculo {
	
    Vehiculo DTOaVehiculo(VehiculoDTO vehiculo) {
        
        //Se pasa a dto y se guarda en la BBDD TODO
        Vehiculo v = new Vehiculo();
        v.setTuercasAntirobo(vehiculo.getTuercasAntirrobo());
        v.setAlarma(vehiculo.getTieneAlarma());
        v.setDispositivoRastreo(vehiculo.getDispositivoRastreo());
        v.setGuardaEnGarage(vehiculo.getGuardaEnGarage());
        v.setAnioFabricacionVehiculo(new GestorMarca().buscarAnioFabricacion(vehiculo.getAnioVehiculo()));
        v.setKmPorAnio(Integer.parseInt(vehiculo.getKmPorAnio()));
        v.setNumChasis(vehiculo.getNumChasis());
        v.setNumMotor(vehiculo.getNumMotor());
        v.setPatente(vehiculo.getNumPatente());
        v.setEstRobo(vehiculo.getEstadisticaRobo());
        
        return v;
    }
}
