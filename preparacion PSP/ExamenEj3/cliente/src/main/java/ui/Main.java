package ui;

import dao.DaoInforme;
import dao.DaoLogin;
import dao.DaoRaton;
import domain.modelo.Informe;
import domain.modelo.Raton;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        DaoLogin daoLogin = container.select(DaoLogin.class).get();
        DaoRaton daoRatones = container.select(DaoRaton.class).get();
        DaoInforme daoInformes = container.select(DaoInforme.class).get();

        Scanner sc = new Scanner(System.in);
        System.out.println("que quieres ejecutar?");
        int opcion;
        do {
            System.out.println("1. Login");
            System.out.println("2. Ver ratones (solo si eres curioso o biologo)");
            System.out.println("3. Añadir raton (solo si eres biologo)");
            System.out.println("4. Ver informes");
            System.out.println("5. Ver un informe concreto");
            System.out.println("6. Añadir informe (solo si eres espia)");

            opcion = sc.nextInt();
            switch (opcion) {
                default -> System.out.println("no valido");
                case 1 -> {
                    String user = "informe2";
                    String pass = "informe2";
                    daoLogin.login(user, pass).blockingSubscribe(peek -> peek
                            .peekLeft(error -> System.out.println("no se ha podido loguear"))
                            .peek(log -> System.out.println("logueado correctamente")));
                }
                case 2 -> daoRatones.getAll().blockingSubscribe(peek ->
                        peek.peekLeft(error -> System.out.println("error, no autorizado"))
                                .peek(ratons -> ratons.forEach(System.out::println)));
                case 3 -> {
                    Raton raton = new Raton();
                    String nombre = "nombre";
                    int edad = 20;
                    raton.setNombre(nombre);
                    raton.setEdad(edad);
                    daoRatones.add(raton).blockingSubscribe(peek ->
                            peek.peekLeft(error -> System.out.println("error, no autorizado"))
                                    .peek(raton1 -> System.out.println("Raton añadido")));
                }
                case 4 -> {
                    daoInformes.getAll().blockingSubscribe(peek ->
                            peek.peekLeft(error -> System.out.println("no auth"))
                                    .peek(informes -> informes.forEach(System.out::println)));
                }
                case 5 -> {
                    String nombre = "Informe 1";
                    daoInformes.get(nombre).blockingSubscribe(peek ->
                            peek.peekLeft(error -> System.out.println("no auth"))
                                    .peek(informe -> System.out.println(informe)));
                }
                case 6 -> {
                    String nombre = "nombre";
                    String rol = "NIVEL 2";
                    Informe info = new Informe(nombre, LocalDate.now(), rol);

                    daoInformes.add(info).blockingSubscribe(peek ->
                            peek.peekLeft(error -> System.out.println("no auth"))
                                    .peek(informe -> System.out.println("añadido correctamente: \n" + informe)));
                }
                case 0 -> {
                    System.out.println("salir");
                    container.close();
                }
            }
        } while (opcion!=0);


    }
}
