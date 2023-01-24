package ui.pantallas.reader.listType;

import model.ArticleType;
import model.Newspaper;
import model.Reader;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ListTypeViewmodel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<ListTypeState> state;

    @Inject
    public ListTypeViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ListTypeState(null, false,
                        readerServImpl.getAll(new Newspaper(0),null).get()));
    }

    public ReadOnlyObjectProperty<ListTypeState> getState() {
        return state;
    }

    public void reloadState() {
        state.setValue(new ListTypeState(
                null, !state.get().isChange(),
                readerServImpl.getAll(new Newspaper(0),null).get()
        ));
    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(new Newspaper(0), null).get();
    }


    public void getByDesc(String desc){
        state.setValue(new ListTypeState(null, !state.get().isChange(), readerServImpl.getAll(new Newspaper(0), new ArticleType(desc)).get()));
    }
}
