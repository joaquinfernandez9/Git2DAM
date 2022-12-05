package dao.impl;

import dao.DaoUsuarios;
import dao.DataBase;
import domain.modelo.Capitulo;
import domain.modelo.Pelicula;
import domain.modelo.Serie;
import domain.modelo.Usuario;
import jakarta.inject.Inject;

import java.util.List;


public class DaoUsuariosImpl implements DaoUsuarios {

    private final DataBase dataBase;

    @Inject
    public DaoUsuariosImpl(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @Override
    public boolean añadirCliente(Usuario usuario){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        usuarios.add(usuario);
        return dataBase.saveUsuarios(usuarios);
    }


    @Override
    public boolean cambiarEstadoSerie(String nombre, String nombreSerie, String nombreCapitulo,String nuevoEstado){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        Usuario user = usuarios.stream()
                .filter(usuarioslist -> usuarioslist.getNombre().equals(nombre))
                .findFirst().orElse(null);
        if (user != null){
            Serie serie = user.getSeriesUser().stream()
                    .filter(series -> series.getNombre().equals(nombreSerie))
                            .findFirst().orElse(null);
            if (serie != null){
                Capitulo cap = serie.getEpisodios().stream()
                        .filter(capitulo -> capitulo.getNombre().equals(nombreCapitulo))
                        .findFirst().orElse(null);
                if (cap!= null){
                    cap.setEstado(nuevoEstado);
                    dataBase.saveUsuarios(usuarios);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean cambiarEstadoPelicula(String nombre, String nombrePelicula, String nuevoEstado){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        Usuario user = usuarios.stream()
                .filter(usuarioslist -> usuarioslist.getNombre().equals(nombre))
                .findFirst().orElse(null);
        if (user != null){
            Pelicula pelicula = user.getPeliculasUser().stream()
                    .filter(series -> series.getNombre().equals(nombrePelicula))
                    .findFirst().orElse(null);
            if (pelicula != null){
                pelicula.setEstado(nuevoEstado);
                dataBase.saveUsuarios(usuarios);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean ponerRatingPelicula(String nombre, String nombrePelicula, int puntuacion){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        Usuario user = usuarios.stream()
                .filter(usuarioslist -> usuarioslist.getNombre().equals(nombre))
                .findFirst().orElse(null);
        if (user != null){
            Pelicula pelicula = user.getPeliculasUser().stream()
                    .filter(series -> series.getNombre().equals(nombrePelicula))
                    .findFirst().orElse(null);
            if (pelicula != null){
                pelicula.setPuntuacion(puntuacion);
                dataBase.saveUsuarios(usuarios);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean ponerRatingSerie(String nombre, String nombreSerie,String nombreEpisodio, int puntuacion){
        List<Usuario> usuarios = dataBase.loadUsuarios();
        Usuario user = usuarios.stream()
                .filter(usuarioslist -> usuarioslist.getNombre().equals(nombre))
                .findFirst().orElse(null);
        if (user != null){
            Serie serie = user.getSeriesUser().stream()
                    .filter(series -> series.getNombre().equals(nombreSerie))
                    .findFirst().orElse(null);
            if (serie != null){
                Capitulo cap = serie.getEpisodios().stream()
                        .filter(capitulo -> capitulo.getNombre().equals(nombreEpisodio))
                        .findFirst().orElse(null);
                if (cap!= null){
                    cap.setPuntuacion(puntuacion);
                    dataBase.saveUsuarios(usuarios);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<Usuario> verUsuarios(){
        return dataBase.loadUsuarios();
    }

    @Override
    public Usuario verUsuario(String nombreUsuario){
        return dataBase.loadUsuarios().stream().filter(usuario -> usuario.getNombre().equals(nombreUsuario))
                .findFirst().orElse(null);
    }


}
