package ui.pantallas.sets;

import domain.modelo.ListaSetsCarta;

import java.util.List;

public record SetsState(String error, boolean cambio,
                        List<ListaSetsCarta> listaSetsCarta) {
}
