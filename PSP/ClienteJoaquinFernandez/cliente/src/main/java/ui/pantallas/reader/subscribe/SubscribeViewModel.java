package ui.pantallas.reader.subscribe;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Subscription;
import domain.services.NewspaperServ;
import domain.services.SubscriptionServ;

import java.time.LocalDate;
import java.util.ArrayList;

public class SubscribeViewModel {
    private final SubscriptionServ subscriptionServ;
    private final NewspaperServ newspaperServ;
    private final ObjectProperty<SubscribeState> state;

    @Inject
    public SubscribeViewModel(SubscriptionServ subscriptionServ,
                              NewspaperServ newspaperServ) {
        this.subscriptionServ = subscriptionServ;
        this.newspaperServ = newspaperServ;
        this.state = new SimpleObjectProperty<>(
                new SubscribeState(null, false,
                        new ArrayList<>(),
                        new ArrayList<>()));
    }

    public ReadOnlyObjectProperty<SubscribeState> getState() {
        return state;
    }

    public void reloadState(int id) {
        state.setValue(new SubscribeState(
                null,
                !state.get().isChange(),
                newspaperServ.getAll(),
                subscriptionServ.getAll(id)
        ));
    }


    public void subscribe(int idReader, int idNewspaper, LocalDate singDate, LocalDate cancelDate) {
        Subscription sub = new Subscription(idReader, idNewspaper, singDate, cancelDate);
        int response = subscriptionServ.add(sub);
        if (response == 1) {
            state.setValue(new SubscribeState(
                    "Subscripción realizada con éxito",
                    !state.get().isChange(),
                    newspaperServ.getAll(),
                    subscriptionServ.getAll(idReader)
            ));
        } else {
            state.setValue(new SubscribeState(
                    "Error al realizar la subscripción",
                    !state.get().isChange(),
                    newspaperServ.getAll(),
                    subscriptionServ.getAll(idReader)
            ));
        }
    }

    public void unsubscribe(int idReader, int idNewspaper) {
        int response = subscriptionServ.delete(idReader, idNewspaper);
        if (response == 1) {
            state.setValue(new SubscribeState(
                    null,
                    !state.get().isChange(),
                    newspaperServ.getAll(),
                    subscriptionServ.getAll(idReader)
            ));
        }
    }

}
