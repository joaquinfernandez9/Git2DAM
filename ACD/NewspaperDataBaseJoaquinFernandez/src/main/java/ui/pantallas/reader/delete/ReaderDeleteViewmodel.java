package ui.pantallas.reader.delete;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import model.Subscription;
import services.ReaderServ;
import jakarta.inject.Inject;
import services.SubscriptionServ;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReaderDeleteViewmodel {

    private final ReaderServ readerServImpl;
    private final SubscriptionServ subscriptionServImpl;
    private final ObjectProperty<ReaderDeleteState> state;

    @Inject
    public ReaderDeleteViewmodel(ReaderServ readerServImpl, SubscriptionServ subscriptionServImpl) {
        this.readerServImpl = readerServImpl;
        this.subscriptionServImpl = subscriptionServImpl;
        this.state = new SimpleObjectProperty<>(
                new ReaderDeleteState(null, false,
                        readerServImpl.getAll(-1, null).get()));
    }

    public ReadOnlyObjectProperty<ReaderDeleteState> getState() {
        return state;
    }

    public List<Reader> getAll() {
        return readerServImpl.getAll(-1, null).get();
    }

    public void reloadState() {
        state.setValue(new ReaderDeleteState(
                null, !state.get().isChange(),
                readerServImpl.getAll(-1, null).get()
        ));
    }

    public void deleteReader(int idReader) {
        int response = readerServImpl.deleteReader(idReader);
        if (response == 1) {
            state.setValue(new ReaderDeleteState(
                    "Reader deleted", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (response == -1) {
            state.setValue(new ReaderDeleteState(
                    "Reader not found", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        } else if (response == -2) {
            state.setValue(new ReaderDeleteState(
                    "Database error", true,
                    readerServImpl.getAll(-1, null).get()
            ));
        }
    }

    public boolean hasSubscriptions(int id) {
        List<Subscription> result = subscriptionServImpl.getAll(id);
        return !result.isEmpty();
    }


}
