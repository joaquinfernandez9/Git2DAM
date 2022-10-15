package ui.pantallas.reader.addReader;

import domain.modelo.Reader;
import lombok.Data;

import java.util.List;

@Data
public class AddReaderState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
