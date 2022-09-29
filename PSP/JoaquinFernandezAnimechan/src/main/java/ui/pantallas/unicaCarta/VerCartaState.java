package ui.pantallas.unicaCarta;


import domain.modelo.cards.CardsList;

public record VerCartaState(String error, boolean cambio, CardsList cardsList) {}
