package ui.pantallas.pantallaAdmin;

import domain.modelo.Pelicula;
import domain.modelo.Serie;
import domain.modelo.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class AdminState {
    private final List<Usuario> usuarios;
    private final List<Serie> series;
    private final List<Pelicula> peliculas;
    private final String error;
    private final boolean cambio;
}
