package ui.pantallas.reader.delete;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Newspaper;
import model.Reader;
import model.Subscription;
import services.NewspaperServ;
import services.ReaderServ;
import jakarta.inject.Inject;
import services.SubscriptionServ;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReaderDeleteViewmodel {

    private final ReaderServ readerServImpl;
    private final SubscriptionServ subscriptionServImpl;
    private final NewspaperServ newspaperServ;
    private final ObjectProperty<ReaderDeleteState> state;

    @Inject
    public ReaderDeleteViewmodel(ReaderServ readerServImpl, NewspaperServ serv, SubscriptionServ subscriptionServImpl) {
        this.readerServImpl = readerServImpl;
        this.subscriptionServImpl = subscriptionServImpl;
        this.newspaperServ = serv;
        this.state = new SimpleObjectProperty<>(
                new ReaderDeleteState(null, false,
                        readerServImpl.getAll()));
    }

    public ReadOnlyObjectProperty<ReaderDeleteState> getState() {
        return state;
    }

    public List<Reader> getAll() {
        return readerServImpl.getAll();
    }

    public List<Newspaper> loadNewspaper() {
        return newspaperServ.getAll();
    }

    public List<Reader> loadReaders(Newspaper newspaper) {
        return readerServImpl.getAll(newspaper);
    }

    public void reloadState() {
        state.setValue(new ReaderDeleteState(
                null, !state.get().isChange(),
                readerServImpl.getAll()
        ));
    }

    public void deleteReader(Newspaper np, int idReader) {
        int response = readerServImpl.deleteReader(np, new Reader(idReader));
        if (response == 1) {
            state.setValue(new ReaderDeleteState(
                    "Reader deleted", true,
                    readerServImpl.getAll()
            ));
        } else if (response == -1) {
            state.setValue(new ReaderDeleteState(
                    "Reader not found", true,
                    readerServImpl.getAll()
            ));
        } else if (response == -2) {
            state.setValue(new ReaderDeleteState(
                    "Database error", true,
                    readerServImpl.getAll()
            ));
        }
    }

    public boolean hasSubscriptions(int id) {
        List<Subscription> result = subscriptionServImpl.getAll(id);
        return !result.isEmpty();
    }


}
