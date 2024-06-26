package ui.pantallas.reader.listSubscription;

import lombok.Data;
import model.Newspaper;
import model.Reader;

import java.util.List;

@Data
public class ListSubscriptionState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
    private final List<Newspaper> newspapersList;
}
