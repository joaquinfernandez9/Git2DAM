package ui.pantallas.article.list;

import services.ArticleServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ArticleListViewModel {

    private final ArticleServ articleServImpl;
    private final ObjectProperty<ArticleListState> state;

    @Inject
    public ArticleListViewModel(ArticleServ articleServImpl) {
        this.articleServImpl = articleServImpl;
        this.state = new SimpleObjectProperty<>(
                new ArticleListState(null, false,
                        articleServImpl.getAll()));
    }

    public void reloadState() {
        state.setValue(new
                ArticleListState(null, !state.get().isChange(),
                articleServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<ArticleListState> getState() {
        return state;
    }


    public void getAllfilter(String description){
        state.setValue(new ArticleListState(null, !state.get().isChange(), articleServImpl.getArticlesFilter(description)));
        articleServImpl.getArticlesFilter(description);
    }


}
