package ui.pantallas.newspaper.delete;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import services.NewspaperServ;
import ui.pantallas.newspaper.list.NewsState;

import java.util.concurrent.atomic.AtomicBoolean;

public class NewsDeleteViewModel {

    private final NewspaperServ newspaperServImpl;
    private final ObjectProperty<NewsState> state;

    @Inject
    public NewsDeleteViewModel(NewspaperServ newspaperServImpl) {
        this.newspaperServImpl = newspaperServImpl;
        state = new SimpleObjectProperty<>(new NewsState(null, false, null));
    }


    public ReadOnlyObjectProperty<NewsState> getState() {
        return state;
    }

    public void getAll(){
        newspaperServImpl.getNewspapers()
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsState(null, true, either.get()));
                    }
                });
    }

    public void deleteNewspaper(int news){
        newspaperServImpl.deleteNewspaper(news)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft()){
                        state.set(new NewsState(either.getLeft(), false, null));
                    }
                    else {
                        either.get();
                        state.set(new NewsState(null, true, null));
                    }
                    System.out.println(either);
                });
    }

    public void deleteConfirmed(int news){
        newspaperServImpl.deleteConfirmed(news)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new NewsState(either.getLeft(), false, null));
                    else {
                        state.set(new NewsState(null, true, null));
                    }
                });
    }

    public void clearState() {
        state.set(new NewsState(null, false, null));
    }




}
