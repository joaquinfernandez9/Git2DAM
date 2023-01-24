package ui.pantallas.article.list;

import model.ArticleType;
import services.ArticleServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.TypeServ;

import java.util.List;

public class ArticleListViewModel {

    private final ArticleServ articleServImpl;
    private final TypeServ typeServImpl;
    private final ObjectProperty<ArticleListState> state;

    @Inject
    public ArticleListViewModel(ArticleServ articleServImpl, TypeServ typeServImpl) {
        this.articleServImpl = articleServImpl;
        this.typeServImpl = typeServImpl;
        this.state = new SimpleObjectProperty<>(
                new ArticleListState(null, false,
                        articleServImpl.getAll(), typeServImpl.get()));
    }

    public void reloadState() {
        state.setValue(new
                ArticleListState(null, !state.get().isChange(),
                articleServImpl.getAll(), typeServImpl.get()));
    }

    public ReadOnlyObjectProperty<ArticleListState> getState() {
        return state;
    }


    public void getAllfilter(String description){
        state.setValue(new ArticleListState(null, !state.get().isChange(), articleServImpl.getArticlesFilter(description), typeServImpl.get()));
        articleServImpl.getArticlesFilter(description);
    }

    public List<ArticleType> getAll(){
        return typeServImpl.getAll();
    }


}
