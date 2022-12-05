package dao.impl;

import dao.DaoPeliculas;
import dao.DataBase;
import domain.modelo.Pelicula;
import jakarta.inject.Inject;

import java.util.List;

public class DaoPeliculasImpl implements DaoPeliculas {

    private final DataBase dataBase;

    @Inject
    public DaoPeliculasImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean añadirPelicula(Pelicula p){
        List<Pelicula> peliculas = dataBase.loadPeliculas();
        peliculas.add(p);
        return dataBase.savePeliculas(peliculas);
    }

    @Override
    public List<Pelicula> verPeliculas(){
        return dataBase.loadPeliculas();
    }


}
