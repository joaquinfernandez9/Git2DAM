package ui.pantallas.mazos;


import domain.modelo.cards.CardsList;
import lombok.Data;

@Data
public class VerCartaState {
    private final String error;
    private final boolean cambio;
    private final CardsList cardsList;
}
