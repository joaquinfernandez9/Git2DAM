package ui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import services.OrdersService;

import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        OrdersService serv = container.select(OrdersService.class).get();

        System.out.println(serv.query());
    }
}
