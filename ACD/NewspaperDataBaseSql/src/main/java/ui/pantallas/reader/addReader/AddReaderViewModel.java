package ui.pantallas.reader.addReader;

import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.reader.delete.ReaderDeleteState;

import java.time.LocalDate;

public class AddReaderViewModel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<AddReaderState> state;

    @Inject
    public AddReaderViewModel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new AddReaderState(null, false,
                        readerServImpl.getAll().get()));
    }

    public ReadOnlyObjectProperty<AddReaderState> getState() {
        return state;
    }

    public void reloadState(){
        state.setValue(new AddReaderState(
                null, !state.get().isChange(),
                readerServImpl.getAll().get()
        ));
    }

    public void addReader(int id, String name, LocalDate date){
        readerServImpl.add(id, name, date);
    }
}
