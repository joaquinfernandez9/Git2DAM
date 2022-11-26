package ui.pantallas.reader.updateReader;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import services.ReaderServ;

import java.util.List;

public class UpdateViewModel {

    private final ReaderServ readerServImpl;
    private final ObjectProperty<UpdateState> state;

    @Inject
    public UpdateViewModel(ReaderServ readerServ) {
        this.readerServImpl = readerServ;
        this.state = new SimpleObjectProperty<>(
                new UpdateState(null, false,
                        null));
    }

    public ReadOnlyObjectProperty<UpdateState> getState() {
        return state;
    }


    public void getAll() {
        readerServImpl.getReaders()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new UpdateState(either.getLeft(), false, null));
                    else {
                        state.set(new UpdateState(null, true, either.get()));
                    }
                });
    }

    public void updateReader(Reader reader) {
        readerServImpl.updateReader(reader)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
            if (either.isLeft())
                state.set(new UpdateState(either.getLeft(), false, null));
            else {
                state.set(new UpdateState(null, true, null));
            }
        });
    }

    public void clearState() {
        state.set(new UpdateState(null, false, null));
    }

}
