package ui.pantallas.unicaCarta;


import dao.retrofit.cards.CardsList;

public record VerCartaState(String error, boolean cambio, CardsList cardsList) {}
