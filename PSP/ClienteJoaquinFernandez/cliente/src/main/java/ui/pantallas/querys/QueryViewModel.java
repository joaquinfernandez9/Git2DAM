package ui.pantallas.querys;

import io.reactivex.rxjava3.schedulers.Schedulers;
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
        this.state = new SimpleObjectProperty<>(new
                QueryState(null, false, null, null));
    }

    public void load() {
        querysServ.getQuery3(null)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new QueryState(either.getLeft(), false, null, null));
                    else {
                        state.set(new QueryState(null, true, either.get(), null));
                    }
                });
        querysServ.getQuery4(0)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new QueryState(either.getLeft(), false, null, null));
                    else {
                        state.set(new QueryState(null, true, null, either.get()));
                    }
                });
    }

    public ObjectProperty<QueryState> getState() {
        return state;
    }

    public void thirdQuery(String description) {
        querysServ.getQuery3(description)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new QueryState(either.getLeft(), false, null,
                                state.getValue().getFourthQuery()));
                    else {
                        state.set(new QueryState(null, true, either.get(),
                                state.getValue().getFourthQuery()));
                    }
                });
    }

    public void fourthQuery(int idNewspaper) {
        querysServ.getQuery4(idNewspaper)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new QueryState(either.getLeft(), false, state.getValue().getThirdQuery(), null));
                    else {
                        state.set(new QueryState(null, true, state.getValue().getThirdQuery(), either.get()));
                    }
                });
    }

    public void clearState() {
        state.set(new QueryState(null, false, null, null));
    }


}
