/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package historiales;

import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import logica.Cobertura;
import logica.PorcentajeCobertura;

/**
 *
 * @author Lucas
 */
public class CoberturaHistorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Cobertura cobertura;
    
    @OneToOne
    private PorcentajeCobertura porcentajeActual;
    
    @OneToMany(mappedBy="porcentajeHistorial")
    private List<PorcentajeCobertura> historialPorcentaje;
    
}
