package ui.pantallas.querys;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.QuerysServ;

public class QueryViewModel {

    private final QuerysServ querysServ;
    private final ObjectProperty<QueryState> state;

    @Inject
    public QueryViewModel(QuerysServ querysServ) {
        this.querysServ = querysServ;
        this.state = new SimpleObjectProperty<>(new QueryState(null, false, null, null));
    }

    public void load() {
        state.setValue(new QueryState(null, !state.get().isChange(), querysServ.getAll(null), querysServ.getArticles(0)));
    }

    public ObjectProperty<QueryState> getState() {
        return state;
    }

    public void thirdQuery(String description) {
        state.setValue(new QueryState(null, !state.get().isChange(), querysServ.getAll(description), querysServ.getArticles(0)));
    }

    public void fourthQuery(int idNewspaper) {
        state.setValue(new QueryState(null, !state.get().isChange(), querysServ.getAll(null), querysServ.getArticles(idNewspaper)));
    }





}
