package ui.pantallas.filtro;

import dao.retrofit.cards.CardsList;

public record FiltroState(String error, boolean cambio, CardsList cardsList) {
}
