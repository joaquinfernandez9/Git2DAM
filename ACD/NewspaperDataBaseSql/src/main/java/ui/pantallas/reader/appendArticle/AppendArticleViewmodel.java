package ui.pantallas.reader.appendArticle;

import model.Article;
import model.ReadArticle;
import model.Reader;
import services.ArticleServ;
import services.ReadArticleServ;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.principal.PrincipalController;

import java.util.ArrayList;
import java.util.List;

public class AppendArticleViewmodel {
    private final ReadArticleServ readArticleServ;
    private final ArticleServ articleServ;
    private final ObjectProperty<AppendArticleState> state;


    @Inject
    public AppendArticleViewmodel(ReadArticleServ readArticleServ,
                                  ArticleServ articleServ) {
        this.readArticleServ = readArticleServ;
        this.articleServ = articleServ;
        this.state = new SimpleObjectProperty<>(
                new AppendArticleState(null, false,
                        new ArrayList<>(),
                        new ArrayList<>()));

    }

    public ReadOnlyObjectProperty<AppendArticleState> getState() {
        return state;
    }


    public void reloadState(int id) {
        state.setValue(new AppendArticleState(
                null,
                !state.get().isChange(),
                readArticleServ.getAll(id),
                articleServ.getArticlesOfAReader(id)
        ));
    }


    public int appendArticle(Reader r, int article, int rating) {
        ReadArticle ra = new ReadArticle(r.getId(), article, rating);
        return readArticleServ.appendReadArticle(ra);
    }

}
