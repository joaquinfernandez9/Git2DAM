package ui.pantallas.reader.delete;

import model.Reader;
import lombok.Data;

import java.util.List;

@Data
public class ReaderDeleteState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
