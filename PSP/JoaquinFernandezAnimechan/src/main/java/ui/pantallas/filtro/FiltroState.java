package ui.pantallas.filtro;

import domain.modelo.cards.CardsList;

public record FiltroState(String error, boolean cambio, CardsList cardsList) {
}
