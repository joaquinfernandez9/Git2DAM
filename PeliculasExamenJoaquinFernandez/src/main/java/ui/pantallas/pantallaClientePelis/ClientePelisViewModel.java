package ui.pantallas.pantallaClientePelis;

import domain.modelo.Usuario;
import domain.servicios.ServiciosPeliculas;
import domain.servicios.ServiciosUsuarios;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ClientePelisViewModel {

    private final ServiciosPeliculas serviciosPeliculas;
    private final ServiciosUsuarios serviciosUsuarios;
    private final ObjectProperty<ClientePelisState> state;

    @Inject

    public ClientePelisViewModel(ServiciosPeliculas serviciosPeliculas, ServiciosUsuarios serviciosUsuarios) {
        this.serviciosPeliculas = serviciosPeliculas;
        this.serviciosUsuarios = serviciosUsuarios;
        state = new SimpleObjectProperty<>(new ClientePelisState(null, null, null, false));
    }

    public ReadOnlyObjectProperty<ClientePelisState> getState(){
        return state;
    }

    public void loadCliente(Usuario usuario){
        state.set(new ClientePelisState(usuario, serviciosPeliculas.verPeliculas(), null, !getState().get().isCambio()));
    }

    public void moverPelicula(Usuario nombreUsuario, String nombrePelicula, String nuevoEstado){
        serviciosUsuarios.cambiarEstadoPelicula(nombreUsuario.getNombre(), nombrePelicula, nuevoEstado);
        state.setValue(new ClientePelisState(nombreUsuario, serviciosPeliculas.verPeliculas(), null, !getState().get().isCambio()));
    }

    public void ponerPuntuacionPelicula(Usuario usurio, String nombrePelicula, int puntuacion){
        serviciosUsuarios.ponerRatingPelicula(usurio.getNombre(), nombrePelicula, puntuacion);
        state.setValue(new ClientePelisState(usurio, serviciosPeliculas.verPeliculas(), null, !getState().get().isCambio()));
    }

    public Usuario getCliente(String nombreUsuario){
        return serviciosUsuarios.verUser(nombreUsuario);
    }
}
