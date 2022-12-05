package domain.servicios.impl;

import dao.DaoUsuarios;
import domain.modelo.Usuario;
import domain.servicios.ServiciosUsuarios;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosUsuariosImpl implements ServiciosUsuarios {

    private final DaoUsuarios daoUsuarios;

    @Inject
    public ServiciosUsuariosImpl(DaoUsuarios daoUsuarios){
        this.daoUsuarios = daoUsuarios;
    }

    @Override
    public boolean añadirUsuario(Usuario usuario){
        return daoUsuarios.añadirCliente(usuario);
    }

    @Override
    public boolean cambiarEstadoSerie(String nombreUsuario, String nombreSerie,String nombreCapitulo, String nuevoEstado){
        return daoUsuarios.cambiarEstadoSerie(nombreUsuario, nombreSerie, nombreCapitulo, nuevoEstado);
    }

    @Override
    public boolean cambiarEstadoPelicula(String nombreUsuario, String nombrePelicula, String nuevoEstado){
        return daoUsuarios.cambiarEstadoPelicula(nombreUsuario, nombrePelicula, nuevoEstado);
    }

    @Override
    public boolean ponerRatingPelicula(String nombreUsuario, String nombreSerie, int puntuacion){
        return daoUsuarios.ponerRatingPelicula(nombreUsuario, nombreSerie, puntuacion);
    }
    @Override
    public boolean ponerRatingSerie(String nombreUsuario, String nombreSerie,String nombreCapitulo, int puntuacion){
        return  daoUsuarios.ponerRatingSerie(nombreUsuario, nombreSerie, nombreCapitulo, puntuacion);
    }

    @Override
    public List<Usuario> verUsuarios(){
        return daoUsuarios.verUsuarios();
    }


    @Override
    public Usuario verUser(String nombre){
        return daoUsuarios.verUsuario(nombre);
    }
}
