package ui.pantallas.reader.subscribe;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Newspaper;
import model.Subscription;
import services.NewspaperServ;
import services.ReadArticleServ;
import services.ReaderServ;
import services.SubscriptionServ;
import ui.pantallas.reader.appendArticle.AppendArticleState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
        subscriptionServ.add(sub);
    }

    public void unsubscribe(int idReader) {
        subscriptionServ.delete(idReader);
    }

}
