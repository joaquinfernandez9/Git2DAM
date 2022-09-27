package ui.pantallas.newspaper.delete;

import domain.modelo.Newspaper;
import domain.services.NewspaperServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.newspaper.list.NewsState;

import java.util.List;

public class NewsDeleteViewModel {

    private final NewspaperServ newspaperServ;
    private final ObjectProperty<ui.pantallas.newspaper.list.NewsState> state;

    @Inject
    public NewsDeleteViewModel(NewspaperServ newspaperServ) {
        this.newspaperServ = newspaperServ;
        state = new SimpleObjectProperty<>(new ui.pantallas.newspaper.list.NewsState(null, false, null));
    }

    public void load() {
        state.setValue(new ui.pantallas.newspaper.list.NewsState(null, !state.get().isChange(), newspaperServ.getAll()));
    }

    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }

    public List<Newspaper> getAll(){
        return newspaperServ.getAll();
    }

    public boolean deleteNewspaper(int news){
        return newspaperServ.deleteNewspaper(news);
    }
}
