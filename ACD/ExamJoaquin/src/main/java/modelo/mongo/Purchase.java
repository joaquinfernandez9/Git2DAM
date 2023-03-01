package modelo.mongo;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Purchase {
    private ObjectId _id;

    private String datePurchase;

    private List<Item> items;

    private Client client;

    public Purchase(ObjectId _id) {
        this._id = _id;
    }
}
