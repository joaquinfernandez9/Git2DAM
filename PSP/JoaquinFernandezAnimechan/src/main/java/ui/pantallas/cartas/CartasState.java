package ui.pantallas.cartas;

import domain.modelo.cards.CardsList;
import lombok.Data;

@Data
public class CartasState {
    private final String error;
    private final boolean cambio;
    private final CardsList cardsList;
}
