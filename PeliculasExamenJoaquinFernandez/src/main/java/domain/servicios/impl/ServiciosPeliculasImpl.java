package domain.servicios.impl;

import dao.DaoPeliculas;
import domain.modelo.Pelicula;
import domain.servicios.ServiciosPeliculas;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosPeliculasImpl implements ServiciosPeliculas {

    private final DaoPeliculas daoPeliculas;

    @Inject
    public ServiciosPeliculasImpl(DaoPeliculas daoPeliculas){
        this.daoPeliculas = daoPeliculas;
    }

    @Override
    public boolean añadirPelicula(Pelicula p){
        return daoPeliculas.añadirPelicula(p);
    }

    @Override
    public List<Pelicula> verPeliculas(){
        return daoPeliculas.verPeliculas();
    }

}
