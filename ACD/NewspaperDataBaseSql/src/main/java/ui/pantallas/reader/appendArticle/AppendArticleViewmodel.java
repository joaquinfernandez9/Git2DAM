package ui.pantallas.reader.appendArticle;

import model.Reader;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AppendArticleViewmodel {
    private final ReaderServ readerServImpl;
//    private final ObjectProperty<AppendArticleState> state;

    @Inject
    public AppendArticleViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
//        this.state = new SimpleObjectProperty<>(
//                new AppendArticleState(null, false,
//                        readerServImpl.getAll().get()));
    }

//    public ReadOnlyObjectProperty<AppendArticleState> getState() {
//        return state;
//    }
//
//
//    public void reloadState() {
//        state.setValue(new AppendArticleState(
//                null, !state.get().isChange(),
//                readerServImpl.getAll().get()
//        ));
//    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(-1,-1).get();
    }

//    public boolean appendArticle(int id, int idArticle, int rating) {
//        return readerServImpl.appendReadArticle(id, idArticle, rating);
//    }

}
