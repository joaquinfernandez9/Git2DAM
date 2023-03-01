package ui;

import dao.DaoMongoAggregations;
import dao.DaoPurchase;
import dao.Person;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MongoAggregations {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        Scanner scanner = new Scanner(System.in);

        DaoMongoAggregations dao = container.select(DaoMongoAggregations.class).get();

        System.out.println("query1");
        System.out.println(dao.query1("John"));
        System.out.println("\n");
        System.out.println("query2");
        System.out.println(dao.query2());
        System.out.println("\n");
        System.out.println("query3");
        System.out.println(dao.query3());
        System.out.println("\n");
        System.out.println("query4");
        System.out.println(dao.query4());
        System.out.println("\n");
        System.out.println("query5");
        System.out.println(dao.query5("running"));
        System.out.println("\n");
        System.out.println("query6");
        System.out.println(dao.query6());
        System.out.println("\n");
        System.out.println("query7");
        System.out.println(dao.query7("peppeep"));
        System.out.println("\n");
        System.out.println("query8");
        System.out.println(dao.query8());
        System.out.println("\n");
        System.out.println("query9");
        System.out.println(dao.query9());
        System.out.println("\n");
        System.out.println("query10");
        System.out.println(dao.query10());
        System.out.println("\n");
        System.out.println("query11");
        System.out.println(dao.query11());
        System.out.println("\n");
        System.out.println("query12");
        System.out.println(dao.query12());
        System.out.println("\n");
        System.out.println("query13");
        System.out.println(dao.query13());
        System.out.println("\n");
        System.out.println("query14");
        System.out.println("\n");
        System.out.println("query15");
        System.out.println(dao.query15());
        System.out.println("\n");
        System.out.println("query16");
        System.out.println(dao.query16());
        System.out.println("\n");
        System.out.println("query17");
        System.out.println(dao.query17());
        System.out.println("\n");


    }


    // get all persons with name

}
