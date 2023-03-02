package ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.hibernate.Customer;
import services.ClientService;
import services.MenuService;

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Scanner scanner = new Scanner(System.in);

        MenuService serv = container.select(MenuService.class).get();

        Customer john = new Customer("John", "Doe");
        System.out.println(serv.getAll(john));
    }
}
