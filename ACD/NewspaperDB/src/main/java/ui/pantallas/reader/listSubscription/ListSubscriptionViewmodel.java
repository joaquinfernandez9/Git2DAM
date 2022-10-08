package ui.pantallas.reader.listSubscription;

import domain.modelo.Reader;
import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.article.list.ArticleListState;

import java.util.List;

public class ListSubscriptionViewmodel {
    private final ReaderServ readerServ;
    private final ObjectProperty<ListSubscriptionState> state;

    @Inject
    public ListSubscriptionViewmodel(ReaderServ readerServ) {
        this.readerServ = readerServ;
        this.state = new SimpleObjectProperty<>(
                new ListSubscriptionState(null, false,
                        readerServ.getAll().get()));
    }

    public ReadOnlyObjectProperty<ListSubscriptionState> getState() {
        return state;
    }


    public void reloadState() {
        state.setValue(new ListSubscriptionState(
                null, !state.get().isChange(),
                readerServ.getAll().get()
        ));
    }

//    public List<Reader> getAll(){
//        return readerServ.getAll().get();
//    }

    public void search(int id){
        state.setValue(new ListSubscriptionState(null, !state.get().isChange(), readerServ.readersSubscribed(id)));
    }

}
