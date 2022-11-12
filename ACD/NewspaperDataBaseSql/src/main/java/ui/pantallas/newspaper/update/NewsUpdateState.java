package ui.pantallas.newspaper.update;

import lombok.Data;
import model.Newspaper;

import java.util.List;

@Data
public class NewsUpdateState {
    private final String error;
    private final boolean change;
    private final List<Newspaper> newspaperList;
}
