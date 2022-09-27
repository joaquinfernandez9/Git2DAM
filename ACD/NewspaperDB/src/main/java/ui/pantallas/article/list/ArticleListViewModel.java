package ui.pantallas.article.list;

import domain.modelo.Article;
import domain.services.ArticleServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ArticleListViewModel {
    private final ArticleServ articleServ;
    private final ObjectProperty<ArticleListState> state;

    @Inject
    public ArticleListViewModel(ArticleServ articleServ) {
        this.articleServ = articleServ;
        this.state = new SimpleObjectProperty<>(
                new ArticleListState(null, false,
                        articleServ.getAll()));
    }

    public void load() {
        state.setValue(new
                ArticleListState(null, !state.get().isChange(),
                articleServ.getAll()));
    }

    public ReadOnlyObjectProperty<ArticleListState> getState() {
        return state;
    }

    public List<Article> getAll() {
        return articleServ.getAll();
    }

    public void getAllfilter(String description){
        articleServ.getArticlesFilter(description);
    }
}
