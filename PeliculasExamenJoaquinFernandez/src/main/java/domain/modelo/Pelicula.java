package domain.modelo;

import lombok.Data;

import java.util.List;

@Data
public class Pelicula {
    private String nombre;
    private int año;
    private List<Actor> actores;
    private int puntuacion;
    private String estado;

    public Pelicula(String nombre, int año, List<Actor> actores) {
        this.nombre = nombre;
        this.año = año;
        this.actores = actores;
    }

    public Pelicula(String nombre, int año, int puntuacion){
        this.nombre = nombre;
        this.año = año;
        this.puntuacion = puntuacion;
    }
}
