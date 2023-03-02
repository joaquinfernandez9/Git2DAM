package ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.hibernate.Customer;
import services.ClientService;

import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Scanner scanner = new Scanner(System.in);

        ClientService serv = container.select(ClientService.class).get();

        Customer cust = new Customer(4);
        System.out.println(serv.deleteCustomer(cust, false));
    }
}
