package ui.pantallas.article.add;

import domain.modelo.Article;
import domain.services.ArticleServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ArticleAddViewModel {

    private final ArticleServ articleServ;
    private final ObjectProperty<ArticleAddState> state;

    @Inject
    public ArticleAddViewModel(ArticleServ articleServ) {
        this.articleServ = articleServ;
        this.state = new SimpleObjectProperty<>(
                new ArticleAddState(null, false,
                        articleServ.getAll()));
    }

    public void load() {
        state.setValue(new
                ArticleAddState(null, !state.get().isChange(),
                articleServ.getAll()));
    }

    public ReadOnlyObjectProperty<ArticleAddState> getState() {
        return state;
    }

    public List<Article> getAll() {
        return articleServ.getAll();
    }

    public void add(Article article){
        articleServ.addArticle(article);
        state.setValue(new ArticleAddState(null, !state.get().isChange(), articleServ.getAll()));
    }


}
