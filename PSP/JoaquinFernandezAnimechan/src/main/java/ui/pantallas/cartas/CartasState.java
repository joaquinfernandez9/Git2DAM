package ui.pantallas.cartas;

import domain.modelo.cards.CardsList;

public record CartasState(String error, boolean cambio, CardsList cardsList) {
}
