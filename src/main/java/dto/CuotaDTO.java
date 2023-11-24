/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author Lucas
 */
public class CuotaDTO {
    
    String importeDescuentos;
    String premio;
    String importeCuota;
    Date inicioCuota;
    Date ultimoDiaPagoCuota;

    public CuotaDTO(String importeDescuentos, String premio, String importeCuota, Date inicioCuota, Date ultimoDiaPagoCuota) {
        this.importeDescuentos = importeDescuentos;
        this.premio = premio;
        this.importeCuota = importeCuota;
        this.inicioCuota = inicioCuota;
        this.ultimoDiaPagoCuota = ultimoDiaPagoCuota;
    }

    public String getImporteDescuentos() {
        return importeDescuentos;
    }

    public void setImporteDescuentos(String importeDescuentos) {
        this.importeDescuentos = importeDescuentos;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public String getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(String importeCuota) {
        this.importeCuota = importeCuota;
    }

    public Date getInicioCuota() {
        return inicioCuota;
    }

    public void setInicioCuota(Date inicioCuota) {
        this.inicioCuota = inicioCuota;
    }

    public Date getUltimoDiaPagoCuota() {
        return ultimoDiaPagoCuota;
    }

    public void setUltimoDiaPagoCuota(Date ultimoDiaPagoCuota) {
        this.ultimoDiaPagoCuota = ultimoDiaPagoCuota;
    }
    
}
