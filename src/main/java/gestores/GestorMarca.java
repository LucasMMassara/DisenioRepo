package gestores;

import java.util.List;

import daos.DAOMarca;
import logica.AnioFabricacion;
import logica.Marca;
import logica.Modelo;

public class GestorMarca {

    private List<Marca> marcasObtenidas = null;
    private List<Modelo> modelosObtenidos = null;
    private List<AnioFabricacion> aniosObtenidos = null;

    //Capaz hay que traer todo y ir filtrando a medida que elije el usuario y no hacerlo de esta manera.
    public String[] obtenerMarcas() {

        marcasObtenidas = ((new DAOMarca()).obtenerAll());

        return obtenerStringMarcas(marcasObtenidas);
    }

    public String[] obtenerModelos(String marca) {

        modelosObtenidos = obtenerMarcaUnica(marca).getModelos();

        return obtenerStringModelos(modelosObtenidos);
    }

    public String[] obtenerAnios(String modelo) {

        aniosObtenidos = obtenerModeloUnico(modelo).getAnioModelo();

        return obtenerStringAnios(aniosObtenidos);
    }

    private String[] obtenerStringMarcas(List<Marca> marc) {

        String[] nombreMarcas = new String[marc.size()];

        for (int i = 0; i < marc.size(); i++) {
            nombreMarcas[i] = marc.get(i).getNombre();
        }

        return nombreMarcas;
    }

    private Marca obtenerMarcaUnica(String nombreMarca) {

        Marca marcaBuscada = null;

        if (marcasObtenidas == null) {
            marcasObtenidas = ((new DAOMarca()).obtenerAll());
        }

        for (Marca m : marcasObtenidas) {
            if (m.getNombre().equals(nombreMarca)) {
                marcaBuscada = m;
                break;
            }
        }

        return marcaBuscada;
    }

    private String[] obtenerStringModelos(List<Modelo> modelos) {

        String[] nombreModelos = new String[modelos.size()];

        for (int i = 0; i < modelos.size(); i++) {
            nombreModelos[i] = modelos.get(i).getNombre();
        }

        return nombreModelos;
    }

    private Modelo obtenerModeloUnico(String nombreModelo) {

        Modelo modeloBuscado = null;

        for (Modelo m : modelosObtenidos) {
            if (m.getNombre().equals(nombreModelo)) {
                modeloBuscado = m;
                break;
            }
        }

        return modeloBuscado;
    }

    private String[] obtenerStringAnios(List<AnioFabricacion> anios) {

        String[] stringAnios = new String[anios.size()];

        for (int i = 0; i < anios.size(); i++) {
            stringAnios[i] = anios.get(i).getAnio() + "";
        }

        return stringAnios;
    }

    private AnioFabricacion obtenerAnioUnico(String anio) {

        AnioFabricacion anioBuscado = null;

        for (AnioFabricacion a : aniosObtenidos) {
            if ((a.getAnio() + "").equals(anio)) {
                anioBuscado = a;
                break;
            }
        }

        return anioBuscado;
    }
}
