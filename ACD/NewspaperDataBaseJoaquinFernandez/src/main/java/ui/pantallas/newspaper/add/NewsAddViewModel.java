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
        state = new SimpleObjectProperty<>(new NewsAddState(null, false, serv.getAll()));
    }

    public void load() {
        state.setValue(new NewsAddState(null, !state.get().isChange(), serv.getAll()));
    }

    public void addNewspaper(int id, String name) {
        Newspaper newspaper = new Newspaper(id, name, LocalDate.now());
        int response = serv.add(newspaper);
        if (response == 1) {
            state.setValue(new NewsAddState("Newspaper added", true, serv.getAll()));
        } else if (response == -1) {
            state.setValue(new NewsAddState("Newspaper already exists", true, serv.getAll()));
        } else if (response == -2) {
            state.setValue(new NewsAddState("Newspaper id already exists", true, serv.getAll()));
        } else if (response == -3) {
            state.setValue(new NewsAddState("Database error", true, serv.getAll()));
        }
        load();
    }

    public ReadOnlyObjectProperty<NewsAddState> getState() {
        return state;
    }


}
