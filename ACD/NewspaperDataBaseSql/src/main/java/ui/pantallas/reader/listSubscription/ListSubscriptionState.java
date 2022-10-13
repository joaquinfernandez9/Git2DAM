package ui.pantallas.reader.listSubscription;

import domain.modelo.Reader;
import lombok.Data;

import java.util.List;

@Data
public class ListSubscriptionState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}
