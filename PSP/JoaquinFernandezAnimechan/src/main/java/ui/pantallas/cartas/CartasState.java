package ui.pantallas.cartas;

import dao.retrofit.cards.CardsList;
import domain.modelo.ListaCartas;

public record CartasState(String error, boolean cambio, ListaCartas cardsList) {
}
