package ui.pantallas.newspaper.list;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.NewspaperServ;


public class NewsListViewModel {

    private final NewspaperServ newspaperServImpl;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsListViewModel(NewspaperServ newspaperServImpl) {
        this.newspaperServImpl = newspaperServImpl;
        state = new SimpleObjectProperty<>(new NewsState(null, false, null));
    }

    public void getAll(){
        newspaperServImpl.getNewspapers()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsState(null, true, either.get()));
                    }
                });
    }

        public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }

    public void clearState() {
        state.set(new NewsState(null, false, null));
    }


}
