package ui;

import dao.DaoMongoEmployees;
import dao.DaoPurchase;
import dao.DaoTransfer;
import dao.Person;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import modelo.hibernate.Client;
import modelo.hibernate.Items;
import modelo.hibernate.Purchase;
import modelo.hibernate.PurchaseItem;
import org.bson.types.ObjectId;
import services.ServicesClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Scanner scanner = new Scanner(System.in);

        DaoPurchase purchaseDao = container.select(DaoPurchase.class).get();
        ServicesClient serv = container.select(ServicesClient.class).get();
        DaoTransfer transfer = container.select(DaoTransfer.class).get();
        DaoMongoEmployees emp = container.select(DaoMongoEmployees.class).get();

        System.out.println("select exercise (1,2,3,4,5,6) 0 to exit");
        int response = scanner.nextInt();
        if (response == 1) {
            Client client = new Client(1, "Anne", 2000);
            Purchase purchase = new Purchase();
            purchase.setClient(client);
            PurchaseItem purchaseItem1 = new PurchaseItem(1, 1, new Items(1, "milk", 3.8));
            PurchaseItem purchaseItem2 = new PurchaseItem(2, 1, new Items(2, "fish", 1.3));
            purchase.setPurchaseItems(List.of(purchaseItem1, purchaseItem2));
            purchase.setPaid(0);
            purchase.setDate(LocalDate.now());
            purchaseDao.add(purchase);
        } else if (response == 2) {
            System.out.println(purchaseDao.getAll(new Client("Anne")));
        } else if (response == 3) {
                System.out.println(serv.deleteClient(new Client(2, "pepito"), false));
        } else if (response == 4) {
//                System.out.println(purchase.delete(new Client(2), true));
        } else if (response == 5) {
//                System.out.println(purchase.getAll(new Client("Anne")));
        } else if (response == 6) {
//                System.out.println(purchase.getAverage());
        } else if (response == 7){
            System.out.println(emp.getAll());
        } else if (response == 8){
            Person per = new Person("pepe", 12);
            System.out.println(emp.add(per));
        } else if (response == 9){
            Person per = new Person("asdasd", 13);
            System.out.println(emp.addFriend("pepe", per));
        } else if (response == 10){
            ObjectId id = new ObjectId("63fddf0e12cdca1ea3506685");
            Person per = new Person(id,"manue", 22);
            System.out.println(emp.deleteFriend(per));
        } else if (response ==11){
            Person pepe = new Person("pepe", 12);
            System.out.println(emp.addHobbie(pepe, "nadar por la noche"));
        } else if (response == 12){
            System.out.println("Lista de personas en mongo");
            System.out.println(transfer.getAll());
            System.out.println("probar solo con la primera persona de la lsita");
            System.out.println(transfer.transfer(transfer.getAll().get(0)));
        }
        System.out.println("bye");

    }
}


/*





6. queery average
 */
