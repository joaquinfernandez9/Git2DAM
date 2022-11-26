package ui.pantallas.reader.delete;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import services.ReaderServ;

import java.util.List;

public class ReaderDeleteViewmodel {

    private final ReaderServ readerServImpl;
    private final ObjectProperty<ReaderDeleteState> state;

    @Inject
    public ReaderDeleteViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ReaderDeleteState(null, false,
                        null));
    }

    public ReadOnlyObjectProperty<ReaderDeleteState> getState() {
        return state;
    }

    public void getAll() {
        readerServImpl.getReaders()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new ReaderDeleteState(either.getLeft(), false, null));
                    else {
                        state.set(new ReaderDeleteState(null, true, either.get()));
                    }
                });
    }


    public void deleteReader(int idReader) {
        readerServImpl.deleteReader(idReader)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new ReaderDeleteState(either.getLeft(), false, null));
                    else {
                        state.set(new ReaderDeleteState(null, true, null));
                    }
                });

    }

    public void clearState() {
        state.set(new ReaderDeleteState(null, false, null));
    }


}
