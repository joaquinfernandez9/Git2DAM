package ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.hibernate.*;
import services.OrdersService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Scanner scanner = new Scanner(System.in);

        OrdersService serv = container.select(OrdersService.class).get();

        MenuItem steakItem = new MenuItem(1, "Steak", "A juicy 8oz sirloin", 18.99);
        MenuItem salmonItem = new MenuItem(2, "Salmon", "Grilled salmon with a lemon butter sauce", 16.99);


        Table table = new Table(2, 2, 2);
        Customer customer = new Customer(1, "John", "Doe", "johndoe@example.com", "555-1234");
        Order order = new Order(2, table, customer, LocalDate.now());

        OrderItems steak = new OrderItems(1, order, steakItem, 1, 18.99);
        OrderItems salmon = new OrderItems(2, order, salmonItem, 1, 16.99);
        order.setOrderItems(List.of(steak, salmon));


        System.out.println(serv.add(order));
    }
}
