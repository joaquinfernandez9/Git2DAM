package ui.pantallas.reader.addReader;

import lombok.Data;
import model.Reader;

import java.util.List;

@Data
public class AddReaderState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
