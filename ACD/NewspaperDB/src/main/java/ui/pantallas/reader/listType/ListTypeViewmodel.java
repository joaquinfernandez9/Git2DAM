package ui.pantallas.reader.listType;

import domain.modelo.Reader;
import domain.services.ReaderServ;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.reader.appendArticle.AppendArticleState;
import ui.pantallas.reader.listSubscription.ListSubscriptionState;

import java.util.List;

public class ListTypeViewmodel {
    private final ReaderServ readerServ;
    private final ObjectProperty<ListTypeState> state;

    @Inject
    public ListTypeViewmodel(ReaderServ readerServ) {
        this.readerServ = readerServ;
        this.state = new SimpleObjectProperty<>(
                new ListTypeState(null, false,
                        readerServ.getAll().get()));
    }

    public ReadOnlyObjectProperty<ListTypeState> getState() {
        return state;
    }

    public void reloadState() {
        state.setValue(new ListTypeState(
                null, !state.get().isChange(),
                readerServ.getAll().get()
        ));
    }


    public void getByDesc(String desc){
        state.setValue(new ListTypeState(null, !state.get().isChange(), readerServ.getReadersFromArticleType(desc)));
    }
}
