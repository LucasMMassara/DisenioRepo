package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="valores_actuales_calculo")

public class ValoresActualesCalculo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    private ValorMedidaSeguridad valorSeg;
    
    @OneToOne
    private ValorSiniestro valorSin;
    
    @OneToOne
    private ValoresGenerales valorGen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ValorMedidaSeguridad getValorSeg() {
        return valorSeg;
    }

    public void setValorSeg(ValorMedidaSeguridad valorSeg) {
        this.valorSeg = valorSeg;
    }

    public ValorSiniestro getValorSin() {
        return valorSin;
    }

    public void setValorSin(ValorSiniestro valorSin) {
        this.valorSin = valorSin;
    }

    public ValoresGenerales getValorGen() {
        return valorGen;
    }

    public void setValorGen(ValoresGenerales valorGen) {
        this.valorGen = valorGen;
    }
    
    
    
}
