package ui.pantallas.newspaper.add;

import io.reactivex.rxjava3.schedulers.Schedulers;
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
        state = new SimpleObjectProperty<>(new NewsAddState(null, false, null));
    }


    public void getAll() {
        serv.getNewspapers()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsAddState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsAddState(null, true, either.get()));
                    }
                });
    }


    public void addNewspaper(int id, String name) {
        Newspaper newspaper = new Newspaper(id, name, LocalDate.now());
        serv.saveNewspaper(newspaper)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsAddState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsAddState(null, true,
                                null));
                    }
                });
    }

    public ReadOnlyObjectProperty<NewsAddState> getState() {
        return state;
    }

    //clear state
    public void clearState() {
        state.set(new NewsAddState(null, false, null));
    }


}
