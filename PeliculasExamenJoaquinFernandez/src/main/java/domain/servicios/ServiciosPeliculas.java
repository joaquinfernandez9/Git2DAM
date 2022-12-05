package domain.servicios;

import domain.modelo.Pelicula;

import java.util.List;

public interface ServiciosPeliculas {
    boolean añadirPelicula(Pelicula p);

    List<Pelicula> verPeliculas();
}
