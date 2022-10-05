package ui.pantallas.article.list;

import domain.services.ArticleServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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

    public void reloadState() {
        state.setValue(new
                ArticleListState(null, !state.get().isChange(),
                articleServ.getAll()));
    }

    public ReadOnlyObjectProperty<ArticleListState> getState() {
        return state;
    }


    public void getAllfilter(String description){
        state.setValue(new ArticleListState(null, !state.get().isChange(), articleServ.getArticlesFilter(description)));
        articleServ.getArticlesFilter(description);
    }


}
