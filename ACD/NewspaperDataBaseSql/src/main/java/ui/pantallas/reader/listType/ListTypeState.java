package ui.pantallas.reader.listType;

import model.Reader;
import lombok.Data;

import java.util.List;

@Data
public class ListTypeState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
