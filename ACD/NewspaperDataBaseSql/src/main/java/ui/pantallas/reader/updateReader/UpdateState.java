package ui.pantallas.reader.updateReader;

import domain.modelo.Reader;
import lombok.Data;

import java.util.List;

@Data
public class UpdateState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
