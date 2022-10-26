package ui.pantallas.reader.updateReader;

import model.Reader;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.List;

public class UpdateViewModel {

    private final ReaderServ readerServImpl;
//    private final ObjectProperty<UpdateState> state;

    @Inject
    public UpdateViewModel(ReaderServ readerServ) {
        this.readerServImpl = readerServ;
//        this.state = new SimpleObjectProperty<>(
//                new UpdateState(null, false,
//                        readerServImpl.getAll().get()));
    }

//    public ReadOnlyObjectProperty<UpdateState> getState() {
//        return state;
//    }
//
//    public void reloadState(){
//        state.setValue(new UpdateState(
//                null, !state.get().isChange(),
//                readerServImpl.getAll().get()
//        ));
//    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(-1,-1).get();
    }

    public void updateReader(Reader reader){
        readerServImpl.update(reader);
    }

}
