package ui.pantallas.filtro;

import domain.modelo.cards.CardsList;
import lombok.Data;

@Data
public class FiltroState {
    private final String error;
    private final boolean cambio;
    private final CardsList cardsList;
}
