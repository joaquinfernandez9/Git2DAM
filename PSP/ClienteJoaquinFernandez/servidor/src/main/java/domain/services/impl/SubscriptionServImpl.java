package domain.services.impl;

import dao.DaoSubscriptions;
import domain.services.SubscriptionServ;
import jakarta.inject.Inject;
import model.Subscription;

import java.util.List;

public class SubscriptionServImpl implements SubscriptionServ {

    private final DaoSubscriptions daoSubscriptions;

    @Inject
    public SubscriptionServImpl(DaoSubscriptions daoSubscriptions) {
        this.daoSubscriptions = daoSubscriptions;
    }

    @Override
    public int delete(int idReader, int idNewspaper) {
        return daoSubscriptions.delete(idReader, idNewspaper);
    }

    @Override
    public int add(Subscription subscription) {
        return daoSubscriptions.add(subscription);
    }

    @Override
    public List<Subscription> getAll() {
        return daoSubscriptions.getAll().get();
    }

    @Override
    public List<Subscription> getAll(int id) {
        return daoSubscriptions.getAll(id).get();
    }


}
