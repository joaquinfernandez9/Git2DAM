package ui.pantallas.reader.listSubscription;

import model.Reader;
import services.NewspaperServ;
import services.QuerysServ;
import services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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
                        readerServImpl.getAll(0, null).get(), servicesNewspaperImpl.getAll()));
        this.servicesNewspaperImpl = servicesNewspaperImpl;
    }

    public ReadOnlyObjectProperty<ListSubscriptionState> getState() {
        return state;
    }


    public void reloadState(int idNewspaper, String description) {
        state.setValue(new ListSubscriptionState(
                null, !state.get().isChange(),
                readerServImpl.getAll(idNewspaper,  description).get(),
                servicesNewspaperImpl.getAll()
        ));
    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(0,null).get();
    }

    public void getOldest(int idNewspaper){
        querysServ.getOldest(idNewspaper).get();
    }


}
