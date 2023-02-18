package services;

import model.Subscription;
import org.bson.types.ObjectId;

import java.util.List;

public interface SubscriptionServ {

    int delete(int idReader, ObjectId idNewspaper);

    int add(Subscription subscription);

    List<Subscription> getAll();

    List<Subscription> getAll(int id);

}
