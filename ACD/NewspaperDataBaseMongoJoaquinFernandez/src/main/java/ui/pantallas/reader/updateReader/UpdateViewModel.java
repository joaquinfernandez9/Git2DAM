package ui.pantallas.reader.updateReader;

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

public class UpdateViewModel {

    private final ReaderServ readerServImpl;
    private final NewspaperServ newspaperServ;
    private final ObjectProperty<UpdateState> state;

    @Inject
    public UpdateViewModel(ReaderServ readerServ,NewspaperServ serv) {
        this.readerServImpl = readerServ;
        this.newspaperServ = serv;
        this.state = new SimpleObjectProperty<>(
                new UpdateState(null, false,
                        readerServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<UpdateState> getState() {
        return state;
    }


    public List<Newspaper> loadNewspaper() {
        return newspaperServ.getAll();
    }

    public List<Reader> loadReaders(Newspaper newspaper) {
        return readerServImpl.getAll(newspaper);
    }
    public void reloadState(){
        state.setValue(new UpdateState(
                null, !state.get().isChange(),
                readerServImpl.getAll(new Newspaper(-1))
        ));
    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(new Newspaper(-1));
    }

    public void updateReader(Reader reader){
        readerServImpl.update(reader);
    }

}