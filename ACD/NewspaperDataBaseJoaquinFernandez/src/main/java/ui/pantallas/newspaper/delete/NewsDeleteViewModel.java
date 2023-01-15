package ui.pantallas.newspaper.delete;

import model.Newspaper;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ArticleServ;
import services.NewspaperServ;
import ui.pantallas.newspaper.delete.NewsState;

import java.util.Collections;
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
        int repsonse = newspaperServImpl.deleteNewspaper(news);
        if (repsonse == 1) {
            state.setValue(new NewsState("Newspaper deleted", true, newspaperServImpl.getAll()));
        } else if (repsonse == -1) {
            state.setValue(new NewsState("Newspaper doesn't exist", true, newspaperServImpl.getAll()));
        } else if (repsonse == -2) {
            state.setValue(new NewsState("Newspaper has articles", true, newspaperServImpl.getAll()));
        } else if (repsonse == -3) {
            state.setValue(new NewsState("Database error", true, newspaperServImpl.getAll()));
        }
    }

    public boolean containsArticels(int id) {
        AtomicBoolean response = new AtomicBoolean(false);
        newspaperServImpl.getAll().stream().filter(newspaper -> newspaper.getId() == id).findFirst().ifPresent(newspaper -> {
            if (articleServImpl.getAll().stream().anyMatch(article -> article.getNewspaper().getId() == newspaper.getId())) {
                response.set(true);
            }
        });
        return response.get();
    }



}
