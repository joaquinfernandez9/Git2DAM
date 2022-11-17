package ui.pantallas.reader.listType;

import lombok.Data;
import model.Reader;

import java.util.List;

@Data
public class ListTypeState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
