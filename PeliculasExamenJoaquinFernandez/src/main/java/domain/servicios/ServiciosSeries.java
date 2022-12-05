package domain.servicios;

import domain.modelo.Serie;

import java.util.List;

public interface ServiciosSeries {
    boolean añadirSerie(Serie serie);

    List<Serie> verSeries();

    boolean añadirActor(String nombreSerie, String nombreCapitulo, String nombreActor);
}
