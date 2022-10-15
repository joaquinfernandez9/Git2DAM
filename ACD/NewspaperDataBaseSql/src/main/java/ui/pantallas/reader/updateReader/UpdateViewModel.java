package ui.pantallas.reader.updateReader;

import domain.modelo.Reader;
import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.reader.delete.ReaderDeleteState;

import java.time.LocalDate;

public class UpdateViewModel {

    private final ReaderServ readerServImpl;
    private final ObjectProperty<UpdateState> state;

    @Inject
    public UpdateViewModel(ReaderServ readerServ) {
        this.readerServImpl = readerServ;
        this.state = new SimpleObjectProperty<>(
                new UpdateState(null, false,
                        readerServImpl.getAll().get()));
    }

    public ReadOnlyObjectProperty<UpdateState> getState() {
        return state;
    }

    public void reloadState(){
        state.setValue(new UpdateState(
                null, !state.get().isChange(),
                readerServImpl.getAll().get()
        ));
    }

    public void updateReader(int id, String name){
        readerServImpl.update(id, name);
    }

}
