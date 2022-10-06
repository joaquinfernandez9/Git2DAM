package ui.pantallas.reader.delete;

import domain.modelo.Reader;
import domain.modelo.Readers;
import lombok.Data;

import java.util.List;

@Data
public class ReaderDeleteState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
