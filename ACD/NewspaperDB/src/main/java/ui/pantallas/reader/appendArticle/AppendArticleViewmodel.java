package ui.pantallas.reader.appendArticle;

import domain.modelo.Reader;
import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AppendArticleViewmodel {
    private final ReaderServ readerServ;
    private final ObjectProperty<AppendArticleState> state;

    @Inject
    public AppendArticleViewmodel(ReaderServ readerServ) {
        this.readerServ = readerServ;
        this.state = new SimpleObjectProperty<>(
                new AppendArticleState(null, false,
                        readerServ.getAll().get()));
    }

    public ReadOnlyObjectProperty<AppendArticleState> getState() {
        return state;
    }


    public void reloadState() {
        state.setValue(new AppendArticleState(
                null, !state.get().isChange(),
                readerServ.getAll().get()
        ));
    }

    public List<Reader> getAll() {
        return readerServ.getAll().get();
    }

    public void appendArticle(int id, int idArticle, int rating) {
        readerServ.appendReadArticle(id, idArticle, rating);
    }

}
