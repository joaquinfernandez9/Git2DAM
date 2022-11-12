package ui.pantallas.newspaper.delete;

import model.Newspaper;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ArticleServ;
import services.NewspaperServ;
import ui.pantallas.newspaper.list.NewsState;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewsDeleteViewModel {

    private final NewspaperServ newspaperServImpl;
    private final ArticleServ articleServImpl;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsDeleteViewModel(NewspaperServ newspaperServImpl, ArticleServ articleServImpl) {
        this.newspaperServImpl = newspaperServImpl;
        this.articleServImpl = articleServImpl;
        state = new SimpleObjectProperty<>(new NewsState(null, false, newspaperServImpl.getAll()));
    }

    public void load() {
        state.setValue(new NewsState(null, !state.get().isChange(), newspaperServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }

    public List<Newspaper> getAll(){
        return newspaperServImpl.getAll();
    }

    public void deleteNewspaper(int news){
        newspaperServImpl.deleteNewspaper(news);
    }

    public boolean containsArticels(int id) {
        AtomicBoolean response = new AtomicBoolean(false);
        newspaperServImpl.getAll().stream().filter(newspaper -> newspaper.getId() == id).findFirst().ifPresent(newspaper -> {
            if (articleServImpl.getAll().stream().anyMatch(article -> article.getId_newspaper() == newspaper.getId())) {
                response.set(true);
            }
        });
        return response.get();
    }



}
