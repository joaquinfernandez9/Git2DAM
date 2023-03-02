package services;

import dao.DaoMenuItems;
import jakarta.inject.Inject;
import model.hibernate.Customer;
import model.hibernate.MenuItem;

import java.util.List;

public class MenuService {
    private final DaoMenuItems dao;

    @Inject
    public MenuService(DaoMenuItems dao) {
        this.dao = dao;
    }

    public List<MenuItem> getAll(Customer customer){
        return dao.getAll(customer);
    }
}
