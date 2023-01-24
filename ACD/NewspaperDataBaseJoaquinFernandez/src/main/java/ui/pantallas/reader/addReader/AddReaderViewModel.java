package ui.pantallas.reader.addReader;

import model.Login;
import model.Newspaper;
import model.Reader;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.List;

public class AddReaderViewModel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<AddReaderState> state;

    @Inject
    public AddReaderViewModel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new AddReaderState(null, false,
                        readerServImpl.getAll(new Newspaper(0), null).get()));
    }

    public ReadOnlyObjectProperty<AddReaderState> getState() {
        return state;
    }

    public void reloadState() {
        state.setValue(new AddReaderState(
                null, !state.get().isChange(),
                readerServImpl.getAll(new Newspaper(0), null).get()
        ));
    }

    public List<Reader> getAll() {
        return readerServImpl.getAll(new Newspaper(0), null).get();
    }

    public void addReader(Login log) {
        int result = readerServImpl.add(log);
        if (result == 1) {
            state.setValue(new AddReaderState(
                    "Reader added", true,
                    readerServImpl.getAll(new Newspaper(0), null).get()
            ));
        } else if (result == -1) {
            state.setValue(new AddReaderState(
                    "Reader already exists", true,
                    readerServImpl.getAll(new Newspaper(0), null).get()
            ));
        } else if (result == -2) {
            state.setValue(new AddReaderState(
                    "Reader id already exists", true,
                    readerServImpl.getAll(new Newspaper(0), null).get()
            ));
        } else if (result == -3) {
            state.setValue(new AddReaderState(
                    "Database error", true,
                    readerServImpl.getAll(new Newspaper(0), null).get()
            ));
        }

    }
}
