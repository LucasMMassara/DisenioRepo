package gestores;
import dto.VehiculoDTO;
import logica.Vehiculo;

public class GestorVehiculo {
	
    Vehiculo DTOaVehiculo(VehiculoDTO vehiculo) {
        
        //Se pasa a dto y se guarda en la BBDD TODO
        Vehiculo v = new Vehiculo();
        v.setTuercasAntirobo(vdto.getTuercasAntirrobo());
        v.setAlarma(vdto.getTieneAlarma());
        v.setDispositivoRastreo(vdto.getDispositivoRastreo());
        v.setGuardaEnGarage(vdto.getGuardaEnGarage());
        v.setAnioFabricacionVehiculo(DAOAnioFabricacion.);
        v.setKmPorAnio();
        v.setNumChasis();
        v.setNumMotor();
        v.setPatente();

    }
}
