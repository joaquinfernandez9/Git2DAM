package services;


import model.Subscription;

import java.util.List;

public interface SubscriptionServ {

    int delete(int idReader, int idNewspaper);

    int add(Subscription subscription);

    List<Subscription> getAll();

    List<Subscription> getAll(int id);

}
