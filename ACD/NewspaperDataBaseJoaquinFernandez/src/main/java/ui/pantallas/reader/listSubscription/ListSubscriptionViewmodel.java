package ui.pantallas.reader.listSubscription;

import model.ArticleType;
import model.Newspaper;
import model.Reader;
import services.NewspaperServ;
import services.QuerysServ;
import services.ReadArticleServ;
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
    private final ReadArticleServ servicesReadArticleImpl;

    @Inject
    public ListSubscriptionViewmodel(ReaderServ readerServImpl, NewspaperServ servicesNewspaperImpl, QuerysServ querysServ, ReadArticleServ servicesReadArticleImpl) {
        this.querysServ = querysServ;

        this.readerServImpl = readerServImpl;
        this.servicesReadArticleImpl = servicesReadArticleImpl;
        this.state = new SimpleObjectProperty<>(
                new ListSubscriptionState(null, false,
                        readerServImpl.getAll(new Newspaper(0), null).get(), servicesNewspaperImpl.getAll()));
        this.servicesNewspaperImpl = servicesNewspaperImpl;
    }

    public ReadOnlyObjectProperty<ListSubscriptionState> getState() {
        return state;
    }


    public void reloadState(Newspaper idNewspaper, ArticleType description) {
        state.setValue(new ListSubscriptionState(
                null, !state.get().isChange(),
                readerServImpl.getAll(idNewspaper,  description).get(),
                servicesNewspaperImpl.getAll()
        ));
    }

    public List<Reader> getAll(){
        return readerServImpl.getAll(new Newspaper(0),null).get();
    }

    public void getOldest(int idNewspaper){
        querysServ.getOldest(idNewspaper).get();
    }

    public void getAvgRating(int reader){
        state.setValue(new ListSubscriptionState(
                !state.get().isChange(),
                readerServImpl.getAll(new Newspaper(0), null).get(),
                servicesNewspaperImpl.getAll(),
                servicesReadArticleImpl.getAvgRating(reader)
        ));
    }


}
