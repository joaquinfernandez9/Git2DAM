package ui.pantallas.newspaper.update;

import io.reactivex.rxjava3.schedulers.Schedulers;
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
                new NewsUpdateState(null, false, null));
    }


    public void getAll(){
        serv.getNewspapers()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsUpdateState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsUpdateState(null, true, either.get()));
                    }
                });
    }

    public void updateNewspaper(int id, String name) {
        serv.updateNewspaper(new Newspaper(id, name, LocalDate.now()))
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsUpdateState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsUpdateState(null, true, null));
                    }
                });
    }

    public ObjectProperty<NewsUpdateState> getState() {
        return state;
    }

    //clear state
    public void clearState() {
        state.set(new NewsUpdateState(null, false, null));
    }


}
