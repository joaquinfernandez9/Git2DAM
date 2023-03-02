package services;

import dao.DaoOrders;
import jakarta.inject.Inject;
import model.hibernate.Order;

public class OrdersService {

    private final DaoOrders dao;

    @Inject
    public OrdersService(DaoOrders dao) {
        this.dao = dao;
    }

    public int add(Order order){
        return dao.add(order);
    }

    public int transfer(){
        return dao.trasnferData();
    }

    public int query(){
        return dao.numSteaksPerCustomer();
    }
}
