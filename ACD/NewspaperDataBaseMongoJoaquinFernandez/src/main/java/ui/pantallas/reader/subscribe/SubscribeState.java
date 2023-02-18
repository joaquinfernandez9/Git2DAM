package ui.pantallas.reader.subscribe;

import lombok.Data;
import model.Newspaper;
import model.Subscription;

import java.util.List;

@Data
public class SubscribeState {
    private final String error;
    private final boolean change;
    private final List<Newspaper> newspaperList;
    private final List<Subscription> subscriptionList;
}
