package dao;

import domain.modelo.Usuario;

import java.util.List;

public interface DaoUsuarios {
    boolean añadirCliente(Usuario usuario);

    boolean cambiarEstadoSerie(String nombre, String nombreSerie, String capituloSerie, String nuevoEstado);

    boolean cambiarEstadoPelicula(String nombre, String nombrePelicula,String nuevoEstado);

    boolean ponerRatingPelicula(String nombre, String nombrePelicula, int puntuacion);

    boolean ponerRatingSerie(String nombre, String nombreSerie,String nombreEpisodio, int puntuacion);

    List<Usuario> verUsuarios();

    Usuario verUsuario(String nombre);
}
