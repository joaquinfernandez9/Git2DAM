package ui.pantallas.reader.addReader;

import model.Login;
import model.Newspaper;
import model.Reader;
import services.NewspaperServ;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.List;

public class AddReaderViewModel {
    private final ReaderServ readerServImpl;
    private final NewspaperServ newspaperServ;
    private final ObjectProperty<AddReaderState> state;

    @Inject
    public AddReaderViewModel(ReaderServ readerServImpl, NewspaperServ serv) {
        this.readerServImpl = readerServImpl;
        this.newspaperServ = serv;
        this.state = new SimpleObjectProperty<>(
                new AddReaderState(null, false,
                        readerServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<AddReaderState> getState() {
        return state;
    }

    public void reloadState() {
        state.setValue(new AddReaderState(
                null, !state.get().isChange(),
                readerServImpl.getAll()
        ));
    }

    public List<Reader> getAll() {
        return readerServImpl.getAll();
    }

    public List<Newspaper> loadNewspaper(){
        return newspaperServ.getAll();
    }

    public List<Reader> loadReaders(Newspaper newspaper){
        return readerServImpl.getAll(newspaper);
    }

    public void addReader(Reader log, Newspaper np) {
        int result = readerServImpl.add(log, np);
        if (result == 1) {
            state.setValue(new AddReaderState(
                    "Reader added", true,
                    readerServImpl.getAll()
            ));
        } else if (result == -1) {
            state.setValue(new AddReaderState(
                    "Reader already exists", true,
                    readerServImpl.getAll()
            ));
        } else if (result == -2) {
            state.setValue(new AddReaderState(
                    "Reader id already exists", true,
                    readerServImpl.getAll()
            ));
        } else if (result == -3) {
            state.setValue(new AddReaderState(
                    "Database error", true,
                    readerServImpl.getAll()
            ));
        }

    }
}
