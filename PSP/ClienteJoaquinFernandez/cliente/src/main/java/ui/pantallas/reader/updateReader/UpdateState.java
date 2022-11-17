package ui.pantallas.reader.updateReader;

import lombok.Data;
import model.Reader;

import java.util.List;

@Data
public class UpdateState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
