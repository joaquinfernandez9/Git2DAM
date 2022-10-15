package domain.modelo;

import lombok.Data;

import java.util.List;

@Data
public class ListaCartas{
    private List<Carta> cartas;

    public ListaCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
}
