package ui.pantallas.reader.addReader;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import domain.services.ReaderServ;

import java.util.List;

public class AddReaderViewModel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<AddReaderState> state;

    @Inject
    public AddReaderViewModel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new AddReaderState(null, false,
                        readerServImpl.getAll(-1, null).get()));
    }

    public ReadOnlyObjectProperty<AddReaderState> getState() {
        return state;
    }

    public void reloadState() {
        state.setValue(new AddReaderState(
                null, !state.get().isChange(),
                readerServImpl.getAll(-1, null).get()
        ));
    }

    public List<Reader> getAll() {
        return readerServImpl.getAll(-1, null).get();
    }

    public void addReader(Reader reader) {
        int result = readerServImpl.add(reader);
        if (result == 1) {
            state.setValue(new AddReaderState(
                    "Reader added", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (result == -1) {
            state.setValue(new AddReaderState(
                    "Reader already exists", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (result == -2) {
            state.setValue(new AddReaderState(
                    "Reader id already exists", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (result == -3) {
            state.setValue(new AddReaderState(
                    "Database error", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        }

    }
}
