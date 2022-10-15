package ui.pantallas.cartas;


import domain.modelo.ListaCartas;

public record CartasState(String error, boolean cambio, ListaCartas cardsList) {
}
