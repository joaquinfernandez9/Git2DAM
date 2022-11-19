package ui.pantallas.reader.delete;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import domain.services.ReaderServ;

import java.util.List;

public class ReaderDeleteViewmodel {

    private final ReaderServ readerServImpl;
    private final ObjectProperty<ReaderDeleteState> state;

    @Inject
    public ReaderDeleteViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ReaderDeleteState(null, false,
                        readerServImpl.getAll(-1, null).get()));
    }

    public ReadOnlyObjectProperty<ReaderDeleteState> getState() {
        return state;
    }

    public List<Reader> getAll() {
        return readerServImpl.getAll(-1, null).get();
    }

    public void reloadState() {
        state.setValue(new ReaderDeleteState(
                null, !state.get().isChange(),
                readerServImpl.getAll(-1, null).get()
        ));
    }

    public void deleteReader(int idReader) {
        int response = readerServImpl.deleteReader(idReader);
        if (response == 1) {
            state.setValue(new ReaderDeleteState(
                    "Reader deleted", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (response == -1) {
            state.setValue(new ReaderDeleteState(
                    "Reader not found", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (response == -2) {
            state.setValue(new ReaderDeleteState(
                    "Database error", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        }
    }


}
