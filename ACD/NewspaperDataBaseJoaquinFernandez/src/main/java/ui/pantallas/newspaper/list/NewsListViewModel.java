package ui.pantallas.newspaper.list;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ArticleServ;
import services.NewspaperServ;

import java.util.Collections;


public class NewsListViewModel {

    private final NewspaperServ newspaperServImpl;
    private final ArticleServ articleServImpl;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsListViewModel(NewspaperServ newspaperServImpl, ArticleServ articleServImpl) {
        this.newspaperServImpl = newspaperServImpl;
        this.articleServImpl = articleServImpl;
        state = new SimpleObjectProperty<>(new NewsState(null, false, newspaperServImpl.getAll(), Collections.emptyList()));
    }

    public void load() {
        state.setValue(new NewsState(null, !state.get().isChange(), newspaperServImpl.getAll(), Collections.emptyList()));
    }

    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }

    public void getArticles(int id) {
        state.setValue(new NewsState(null, !state.get().isChange(), newspaperServImpl.getAll(), articleServImpl.getAll(id)));
    }



}
