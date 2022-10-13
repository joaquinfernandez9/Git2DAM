package ui.pantallas.unicaCarta;


import domain.modelo.Carta;

public record VerCartaState(String error, boolean cambio, Carta carta) {}
