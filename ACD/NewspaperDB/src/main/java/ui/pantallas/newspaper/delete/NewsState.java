package ui.pantallas.newspaper.delete;

import domain.modelo.Newspaper;
import lombok.Data;

import java.util.List;

@Data
public class NewsState {
    private final String error;
    private final boolean change;
    private final List<Newspaper> newspaperList;
}
