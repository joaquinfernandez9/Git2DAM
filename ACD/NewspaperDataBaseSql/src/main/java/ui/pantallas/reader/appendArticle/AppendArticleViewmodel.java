package ui.pantallas.reader.appendArticle;

import model.Article;
import model.Reader;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AppendArticleViewmodel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<AppendArticleState> state;

    @Inject
    public AppendArticleViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new AppendArticleState(null, false,
                        readerServImpl.getAll(-1,-1,null).get()));
    }

    public ReadOnlyObjectProperty<AppendArticleState> getState() {
        return state;
    }


    public void reloadState() {
        state.setValue(new AppendArticleState(
                null, !state.get().isChange(),
                readerServImpl.getAll(-1,-1,null).get()
        ));
    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(-1,-1, null).get();
    }

    public int appendArticle(Reader r, int article, int rating) {
        return readerServImpl.appendReadArticle(r, article, rating);
    }

}
