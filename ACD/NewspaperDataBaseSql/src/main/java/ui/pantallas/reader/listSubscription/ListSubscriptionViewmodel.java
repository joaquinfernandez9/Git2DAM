package ui.pantallas.reader.listSubscription;

import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ListSubscriptionViewmodel {
    private final ReaderServ readerServImpl;
    private final ObjectProperty<ListSubscriptionState> state;

    @Inject
    public ListSubscriptionViewmodel(ReaderServ readerServImpl) {
        this.readerServImpl = readerServImpl;
        this.state = new SimpleObjectProperty<>(
                new ListSubscriptionState(null, false,
                        readerServImpl.getAll().get()));
    }

    public ReadOnlyObjectProperty<ListSubscriptionState> getState() {
        return state;
    }


    public void reloadState() {
        state.setValue(new ListSubscriptionState(
                null, !state.get().isChange(),
                readerServImpl.getAll().get()
        ));
    }

//    public List<Reader> getAll(){
//        return readerServ.getAll().get();
//    }

//    public void search(int id){
//        state.setValue(new ListSubscriptionState(null, !state.get().isChange(), readerServImpl.readersSubscribed(id)));
//    }

}
