package domain.modelo;

import lombok.Data;

import java.util.List;

@Data
public class Capitulo {
    private String nombre;
    private List<Actor> actores;
    private String estado;
    private int puntuacion;

    public Capitulo(String nombre){
        this.nombre = nombre;
    }
}
