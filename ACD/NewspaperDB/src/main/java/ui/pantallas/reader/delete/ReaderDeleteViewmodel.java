package ui.pantallas.reader.delete;

import domain.modelo.Reader;
import domain.modelo.Readers;
import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.article.add.ArticleAddState;

import java.util.List;

public class ReaderDeleteViewmodel {

    private final ReaderServ readerServ;
    private final ObjectProperty<ReaderDeleteState> state;

    @Inject
    public ReaderDeleteViewmodel(ReaderServ readerServ) {
        this.readerServ = readerServ;
        this.state = new SimpleObjectProperty<>(
                new ReaderDeleteState(null, false,
                        readerServ.getAll().get()));
    }

    public ReadOnlyObjectProperty<ReaderDeleteState> getState() {
        return state;
    }

    public List<Reader> getAll(){
        return readerServ.getAll().get();
    }

    public void reloadState(){
        state.setValue(new ReaderDeleteState(
                null, !state.get().isChange(),
                readerServ.getAll().get()
        ));
    }

    public void deleteReader(int idReader){
        readerServ.deleteReader(idReader);
    }


}
