package ui.pantallas.pantallaClientePelis;

import domain.modelo.Pelicula;
import domain.modelo.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class ClientePelisState {

    private final Usuario usuario;
    private final List<Pelicula> peliculas;
    private final String error;
    private final boolean cambio;
}
