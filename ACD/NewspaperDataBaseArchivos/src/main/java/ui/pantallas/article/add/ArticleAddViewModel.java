package ui.pantallas.article.add;

import domain.modelo.Article;
import domain.services.ArticleServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ArticleAddViewModel {

    private final ArticleServ articleServImpl;
    private final ObjectProperty<ArticleAddState> state;

    @Inject
    public ArticleAddViewModel(ArticleServ articleServImpl) {
        this.articleServImpl = articleServImpl;
        this.state = new SimpleObjectProperty<>(
                new ArticleAddState(null, false,
                        articleServImpl.getAll()));
    }

    public void load() {
        state.setValue(new
                ArticleAddState(null, !state.get().isChange(),
                articleServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<ArticleAddState> getState() {
        return state;
    }

    public List<Article> getAll() {
        return articleServImpl.getAll();
    }

    public void add(Article article){
        articleServImpl.addArticle(article);
        state.setValue(new ArticleAddState(null, !state.get().isChange(), articleServImpl.getAll()));
    }


}
