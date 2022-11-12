package ui.pantallas.newspaper.add;

import lombok.Data;
import model.Newspaper;

import java.util.List;

@Data
public class NewsAddState {
    private final String error;
    private final boolean change;
    private final List<Newspaper> newspaperList;
}
