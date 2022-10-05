package ui.pantallas.newspaper.list;

import domain.modelo.Newspaper;
import domain.services.NewspaperServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class NewsListViewModel {

    private final NewspaperServ newspaperServ;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsListViewModel(NewspaperServ newspaperServ) {
        this.newspaperServ = newspaperServ;
        state = new SimpleObjectProperty<>(new NewsState(null, false, newspaperServ.getAll()));
    }

    public void load() {
        state.setValue(new NewsState(null, !state.get().isChange(), newspaperServ.getAll()));
    }

    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }


}
