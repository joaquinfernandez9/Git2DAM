package domain.servicios;

import domain.modelo.Usuario;

import java.util.List;

public interface ServiciosUsuarios {
    boolean añadirUsuario(Usuario usuario);

    boolean cambiarEstadoSerie(String nombreUsuario, String nombreSerie, String nombreCapitulo, String nuevoEstado);

    boolean cambiarEstadoPelicula(String nombreUsuario, String nombrePelicula, String nuevoEstado);

    boolean ponerRatingPelicula(String nombreUsuario, String nombreSerie, int puntuacion);

    boolean ponerRatingSerie(String nombreUsuario, String nombreSerie, String nombreCapitulo, int puntuacion);

    List<Usuario> verUsuarios();

    Usuario verUser(String nombre);
}
