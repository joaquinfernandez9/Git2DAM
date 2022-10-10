package ui.pantallas.filtro;

import dao.retrofit.cards.CardsList;
import domain.modelo.ListaCartas;

public record FiltroState(String error, boolean cambio, ListaCartas cardsList) {
}
