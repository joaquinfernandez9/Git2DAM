package ui.pantallas.newspaper.list;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Newspaper;
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
        state = new SimpleObjectProperty<>(new NewsState(null, null, false));
    }

    public void load() {
        state.setValue(new NewsState(newspaperServImpl.getAll(), null, !state.get().isChange()));
    }

    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }

    public void getArticles(int id) {
        state.setValue(new NewsState(newspaperServImpl.getAll(), null, !state.get().isChange(), articleServImpl.getAll()));
    }

    public void deleteArticles(Newspaper idNewspaper) {
        String a = articleServImpl.deleteArticle(idNewspaper.getId());
        state.set(new NewsState(newspaperServImpl.getAll(), a, !state.get().isChange()));
    }

    public void getNbrArticles(int newspaper) {
        state.set(new NewsState(newspaperServImpl.getAll(), null, !state.get().isChange(), newspaperServImpl.getNbrArticles(newspaper)));
    }


}
