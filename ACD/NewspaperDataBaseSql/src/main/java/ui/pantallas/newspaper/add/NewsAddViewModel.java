package ui.pantallas.newspaper.add;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Newspaper;
import services.NewspaperServ;

import java.time.LocalDate;

public class NewsAddViewModel {
    private final NewspaperServ serv;
    private final ObjectProperty<NewsAddState> state;

    @Inject
    public NewsAddViewModel(NewspaperServ serv) {
        this.serv = serv;
        state = new SimpleObjectProperty<>(
                new NewsAddState(null, false, serv.getAll()));
    }

    public void load() {
        state.setValue(new NewsAddState(null, !state.get().isChange(), serv.getAll()));
    }

    public void addNewspaper(int id, String name) {
        Newspaper newspaper = new Newspaper(id, name, LocalDate.now());
        serv.add(newspaper);
        load();
    }

    public ReadOnlyObjectProperty<NewsAddState> getState() {
        return state;
    }


}
