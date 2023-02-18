package ui.pantallas.reader.listSubscription;

import model.Newspaper;
import model.Reader;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ListSubscriptionState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
    private final List<Newspaper> newspapersList;
    private Map<Double,String> averageRating;

    public ListSubscriptionState(boolean b, List<Reader> readers, List<Newspaper> all, Map<Double, String> avgRating) {
        this.error = null;
        this.change = b;
        this.readerList = readers;
        this.newspapersList = all;
        this.averageRating = avgRating;
    }

    public ListSubscriptionState(String error, boolean b, List<Reader> readers, List<Newspaper> all) {
        this.error = error;
        this.change = b;
        this.readerList = readers;
        this.newspapersList = all;
    }

}
