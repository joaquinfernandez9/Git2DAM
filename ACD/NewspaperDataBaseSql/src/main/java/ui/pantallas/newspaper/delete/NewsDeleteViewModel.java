package ui.pantallas.newspaper.delete;

import model.Newspaper;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.NewspaperServ;
import ui.pantallas.newspaper.list.NewsState;

import java.util.List;

public class NewsDeleteViewModel {

    private final NewspaperServ newspaperServImpl;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsDeleteViewModel(NewspaperServ newspaperServImpl) {
        this.newspaperServImpl = newspaperServImpl;
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
        if (newspaperServImpl.deleteNewspaper(news).isRight()){
            state.setValue(new NewsState(null, !state.get().isChange(), newspaperServImpl.getAll()));
        }
    }

    public boolean containsArticels(int id){
        return newspaperServImpl.newspaperContainsArticles(id);
    }


}
