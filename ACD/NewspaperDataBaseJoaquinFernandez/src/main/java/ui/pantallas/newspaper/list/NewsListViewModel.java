package ui.pantallas.newspaper.list;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.NewspaperServ;


public class NewsListViewModel {

    private final NewspaperServ newspaperServImpl;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsListViewModel(NewspaperServ newspaperServImpl) {
        this.newspaperServImpl = newspaperServImpl;
        state = new SimpleObjectProperty<>(new NewsState(null, false, newspaperServImpl.getAll()));
    }

    public void load() {
        state.setValue(new NewsState(null, !state.get().isChange(), newspaperServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }


}
