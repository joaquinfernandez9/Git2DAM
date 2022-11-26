package ui.pantallas.reader.addReader;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import services.ReaderServ;

import java.util.List;

public class AddReaderViewModel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<AddReaderState> state;

    @Inject
    public AddReaderViewModel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new AddReaderState(null, false, null));
    }

    public ReadOnlyObjectProperty<AddReaderState> getState() {
        return state;
    }


    public void getAll() {
        readerServImpl.getReaders()
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new AddReaderState(either.getLeft(), false, null));
                    else {
                        state.set(new AddReaderState(null, true, either.get()));
                    }
                });
    }

    public void addReader(Reader reader) {
        readerServImpl.saveReader(reader)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new AddReaderState(either.getLeft(), false, null));
                    else {
                        state.set(new AddReaderState(null, true, null));
                    }
                });
    }

    public void clearState() {
        state.set(new AddReaderState(null, false, null));
    }

}
