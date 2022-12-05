package domain.modelo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Usuario {
    private String nombre;
    private List<Pelicula> peliculasUser;
    private List<Serie> seriesUser;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.peliculasUser = new ArrayList<>();
        this.seriesUser = new ArrayList<>();
    }
}
