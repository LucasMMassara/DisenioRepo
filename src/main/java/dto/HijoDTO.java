package dto;

import java.util.Date;

public class HijoDTO {
    
    private Date fechaNacimiento;
    private String sexo;
    private String estadoCivil;

    public HijoDTO(Date fechaNacimiento, String sexo, String estadoCivil) {
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return "HijoDTO{" + "fechaNacimiento=" + fechaNacimiento.toString() + ", sexo=" + sexo + ", estadoCivil=" + estadoCivil + '}';
    }
    
    
    
}
