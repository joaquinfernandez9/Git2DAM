package ui.pantallas.newspaper.list;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Newspaper;
import org.bson.types.ObjectId;
import services.ArticleServ;
import services.NewspaperServ;


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

    public void getArticles(ObjectId id) {
        state.setValue(new NewsState(newspaperServImpl.getAll(), null, !state.get().isChange(), articleServImpl.getAll()));
    }

    public void deleteArticles(Newspaper idNewspaper) {
        String a = articleServImpl.deleteArticle(idNewspaper.get_id()).toString();
        state.set(new NewsState(newspaperServImpl.getAll(), a, !state.get().isChange()));
    }

    public void getNbrArticles(ObjectId newspaper) {
        state.set(new NewsState(newspaperServImpl.getAll(), null, !state.get().isChange(), newspaperServImpl.getNbrArticles(newspaper)));
    }


}