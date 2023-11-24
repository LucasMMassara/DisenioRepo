/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Lucas
 */
public class CuotaDTO {
    
    String importeDescuentos;
    String premio;
    String importeCuota;
    String inicioCuota;
    String ultimoDiaPagoCuota;

    public CuotaDTO(String importeDescuentos, String premio, String importeCuota, String inicioCuota, String ultimoDiaPagoCuota) {
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

    public String getInicioCuota() {
        return inicioCuota;
    }

    public void setInicioCuota(String inicioCuota) {
        this.inicioCuota = inicioCuota;
    }

    public String getUltimoDiaPagoCuota() {
        return ultimoDiaPagoCuota;
    }

    public void setUltimoDiaPagoCuota(String ultimoDiaPagoCuota) {
        this.ultimoDiaPagoCuota = ultimoDiaPagoCuota;
    }
    
    
    
    
    
}
