package ui.pantallas.pantallaAdmin;

import domain.modelo.Capitulo;
import domain.modelo.Serie;
import domain.modelo.Usuario;
import domain.servicios.ServiciosPeliculas;
import domain.servicios.ServiciosSeries;
import domain.servicios.ServiciosUsuarios;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AdminViewModel {

    private final ServiciosSeries serviciosSeries;
    private final ServiciosUsuarios serviciosUsuarios;
    private final ServiciosPeliculas serviciosPeliculas;
    private final ObjectProperty<AdminState> state;

    @Inject
    public AdminViewModel(ServiciosUsuarios serviciosUsuarios, ServiciosPeliculas serviciosPeliculas, ServiciosSeries serviciosSeries){
        this.serviciosSeries = serviciosSeries;
        this.serviciosPeliculas = serviciosPeliculas;
        this.serviciosUsuarios = serviciosUsuarios;

        state = new SimpleObjectProperty<>(new AdminState(null, null,null, null, false));
    }

    public ReadOnlyObjectProperty<AdminState> getState() {
        return state;
    }

    public void load(){
        state.setValue(new AdminState(serviciosUsuarios.verUsuarios(), serviciosSeries.verSeries(), serviciosPeliculas.verPeliculas(), null, false));
    }

    public void  añadirUsuario(String nombre){
        Usuario usuario =  new Usuario(nombre);
        serviciosUsuarios.añadirUsuario(usuario);
        state.setValue(new AdminState(serviciosUsuarios.verUsuarios(), serviciosSeries.verSeries(), serviciosPeliculas.verPeliculas(), null, !getState().get().isCambio()));
    }

    public void añadirSerie(String nombre, List<Capitulo> caps){
        Serie serie = new Serie(nombre,caps);
        serviciosSeries.añadirSerie(serie);
        state.setValue(new AdminState(serviciosUsuarios.verUsuarios(), serviciosSeries.verSeries(), serviciosPeliculas.verPeliculas(), null, !getState().get().isCambio()));
    }

    public void añadirActorCapitulo(String nombreSerie, String nombreCapitulo, String nombreActor){
        serviciosSeries.añadirActor(nombreSerie, nombreCapitulo, nombreActor);
        state.setValue(new AdminState(serviciosUsuarios.verUsuarios(), serviciosSeries.verSeries(), serviciosPeliculas.verPeliculas(), null, !getState().get().isCambio()));
    }



}
