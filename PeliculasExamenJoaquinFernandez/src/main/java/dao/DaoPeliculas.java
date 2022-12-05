package dao;

import domain.modelo.Pelicula;

import java.util.List;

public interface DaoPeliculas {
    boolean añadirPelicula(Pelicula p);

    List<Pelicula> verPeliculas();
}
