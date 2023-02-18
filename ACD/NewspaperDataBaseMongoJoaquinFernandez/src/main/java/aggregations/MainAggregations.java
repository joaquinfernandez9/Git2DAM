package aggregations;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.stage.Stage;

import java.util.Scanner;

public class MainAggregations {
    @Inject
    private DaoAggregations dao;

    public void start(@Observes @Start Stage stage){
        Scanner scanner = new Scanner(System.in);
        query1(scanner);
        System.out.println("Enter the number of the aggregation: ");
        String option;
        do {
            System.out.println("a. Get the id of the readers of articles of a specific type");
            System.out.println("b. Get the average rating of the articles read by a reader, group by newspaper");
            System.out.println("c. Get the type of articles that are most read");
            System.out.println("d. Show a list with the number of articles of each type of the selected newspaper");
            System.out.println("e. Get the description and the number of readers of each article");
            System.out.println("f. Get the name of the 100 oldest subscriptors of newspaper Tempo");
            System.out.println("g. Exit");
            option = scanner.nextLine();
            switch (option) {
                case "a" -> query1(scanner);
                case "b" -> query2(scanner);
                case "c" -> query3();
                case "d" -> query4(scanner);
                case "e" -> query5();
                case "f" -> query6();
                case "g" -> {
                    System.out.println("Exit");
                    stage.close();
                }
                default -> System.out.println("Invalid option");
            }

        } while (!option.equals("g"));
    }


    private void query1(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduce the type:");
        String type = scanner.nextLine();
        dao.getReadersByType(type).forEach(System.out::println);
    }

    private void query2(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduce the id:");

        Integer id = scanner.nextInt();
        dao.getAverageRatingByReaderAndNewspaper(id).forEach(System.out::println);
    }

    private void query3() {
        System.out.println(dao.getTypeMostRead());
    }

    private void query4(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Introduce the name of the newspaper:");
        String name = scanner.nextLine();
        dao.getNmbrArticlesofTypeNewspaper(name).forEach(System.out::println);
    }

    private void query5() {
        dao.getDescNmbrReadersOfArticle().forEach(System.out::println);
    }

    private void query6() {
        dao.getNameTenOldestNewspapers().forEach(System.out::println);
    }

}
