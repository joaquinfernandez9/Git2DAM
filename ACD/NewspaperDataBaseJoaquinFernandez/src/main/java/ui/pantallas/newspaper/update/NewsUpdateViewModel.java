package ui.pantallas.newspaper.update;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Newspaper;
import services.NewspaperServ;

import java.time.LocalDate;

public class NewsUpdateViewModel {
    private final NewspaperServ serv;
    private final ObjectProperty<NewsUpdateState> state;

    @Inject
    public NewsUpdateViewModel(NewspaperServ serv) {
        this.serv = serv;
        state = new SimpleObjectProperty<>(
                new NewsUpdateState(null, false, serv.getAll()));
    }

    public void load() {
        state.setValue(new NewsUpdateState(null, !state.get().isChange(), serv.getAll()));
    }

    public void updateNewspaper(int id, String name) {
        serv.update(new Newspaper(id, name, LocalDate.now()));
        load();
    }

    public ObjectProperty<NewsUpdateState> getState() {
        return state;
    }




}
