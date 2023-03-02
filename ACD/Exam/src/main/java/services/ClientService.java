package services;

import dao.DaoCustomer;
import dao.DaoOrders;
import jakarta.inject.Inject;
import model.hibernate.Customer;

public class ClientService {
    private final DaoCustomer daoCustomer;
    private final DaoOrders daoOrders;


    @Inject
    public ClientService(DaoCustomer daoCustomer, DaoOrders daoOrders) {
        this.daoCustomer = daoCustomer;
        this.daoOrders = daoOrders;
    }

    public String deleteCustomer(Customer customer, boolean del){
        String response = "customer deleted";
        if (daoOrders.getAll(customer).isEmpty()){
            daoCustomer.delete(customer);
        } else if (del){
            daoCustomer.delete(customer);
        } else {
            response = null;
        }
        return response;
    }
}
