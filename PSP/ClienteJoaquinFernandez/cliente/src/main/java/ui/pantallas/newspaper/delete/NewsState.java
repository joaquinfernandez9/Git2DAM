package ui.pantallas.newspaper.delete;

import lombok.Data;
import model.Newspaper;

import java.util.List;

@Data
public class NewsState {
    private final String error;
    private final boolean change;
    private final List<Newspaper> newspaperList;
}