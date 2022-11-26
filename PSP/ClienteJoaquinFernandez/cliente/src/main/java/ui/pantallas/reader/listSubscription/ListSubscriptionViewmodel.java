package ui.pantallas.reader.listSubscription;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Reader;
import services.NewspaperServ;
import services.QuerysServ;
import services.ReaderServ;

import java.util.List;

public class ListSubscriptionViewmodel {
    private final ReaderServ readerServImpl;
    private final QuerysServ querysServ;
    private final ObjectProperty<ListSubscriptionState> state;
    private final NewspaperServ servicesNewspaperImpl;

    @Inject
    public ListSubscriptionViewmodel(ReaderServ readerServImpl, NewspaperServ servicesNewspaperImpl, QuerysServ querysServ) {
        this.querysServ = querysServ;
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ListSubscriptionState(null, false,
                        readerServImpl.getReaders().blockingGet().get(),
                        servicesNewspaperImpl.getNewspapers().blockingGet().get()));
        this.servicesNewspaperImpl = servicesNewspaperImpl;
    }

    public ReadOnlyObjectProperty<ListSubscriptionState> getState() {
        return state;
    }



    public void getAll(){
        readerServImpl.getReaders()
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new ListSubscriptionState(either.getLeft(), false, null, null));
                    else {
                        state.set(new ListSubscriptionState(null, true, either.get(), null));
                    }
                });
    }

    public void getOldest(int idNewspaper){
        querysServ.getQuery2(idNewspaper).subscribe(either -> {
            if (either.isLeft())
                state.set(new ListSubscriptionState(either.getLeft(), false, null, null));
            else {
                state.set(new ListSubscriptionState(null, true, either.get(), null));
            }
        });
    }

    public void clearState() {
        state.set(new ListSubscriptionState(null, false, null, null));
    }


}
