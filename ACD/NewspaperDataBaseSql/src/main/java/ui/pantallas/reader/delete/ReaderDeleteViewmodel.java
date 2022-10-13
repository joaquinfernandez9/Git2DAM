package ui.pantallas.reader.delete;

import domain.modelo.Reader;
import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ReaderDeleteViewmodel {

    private final ReaderServ readerServImpl;
    private final ObjectProperty<ReaderDeleteState> state;

    @Inject
    public ReaderDeleteViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ReaderDeleteState(null, false,
                        readerServImpl.getAll().get()));
    }

    public ReadOnlyObjectProperty<ReaderDeleteState> getState() {
        return state;
    }

    public List<Reader> getAll(){
        return readerServImpl.getAll().get();
    }

    public void reloadState(){
        state.setValue(new ReaderDeleteState(
                null, !state.get().isChange(),
                readerServImpl.getAll().get()
        ));
    }

    public void deleteReader(int idReader){
        readerServImpl.ñdeleteReader(idReader);
    }


}
