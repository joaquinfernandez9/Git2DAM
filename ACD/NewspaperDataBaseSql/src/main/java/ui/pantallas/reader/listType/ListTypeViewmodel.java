package ui.pantallas.reader.listType;

import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ListTypeViewmodel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<ListTypeState> state;

    @Inject
    public ListTypeViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ListTypeState(null, false,
                        readerServImpl.getAll().get()));
    }

    public ReadOnlyObjectProperty<ListTypeState> getState() {
        return state;
    }

    public void reloadState() {
        state.setValue(new ListTypeState(
                null, !state.get().isChange(),
                readerServImpl.getAll().get()
        ));
    }


//    public void getByDesc(String desc){
//        state.setValue(new ListTypeState(null, !state.get().isChange(), readerServImpl.getReadersFromArticleType(desc)));
//    }
}
