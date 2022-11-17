package ui.pantallas.reader.appendArticle;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.ReadArticle;
import model.Reader;
import services.ArticleServ;
import services.ReadArticleServ;

import java.util.ArrayList;

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


    public void appendArticle(Reader r, int article, int rating) {
        ReadArticle ra = new ReadArticle(r.getId(), article, rating);
        int response = readArticleServ.appendReadArticle(ra);
        if (response == 1) {
            state.setValue(new AppendArticleState(
                    "Article appended",
                    true,
                    readArticleServ.getAll(r.getId()),
                    articleServ.getArticlesOfAReader(r.getId())
            ));
        } else if (response == -1) {
            state.setValue(new AppendArticleState(
                    "Article already appended",
                    true,
                    readArticleServ.getAll(r.getId()),
                    articleServ.getArticlesOfAReader(r.getId())
            ));
        } else if (response == -2) {
            state.setValue(new AppendArticleState(
                    "Database error",
                    true,
                    readArticleServ.getAll(r.getId()),
                    articleServ.getArticlesOfAReader(r.getId())
            ));
        }
    }

}
