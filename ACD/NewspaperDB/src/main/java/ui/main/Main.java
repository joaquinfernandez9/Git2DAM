package ui.main;

import javafx.application.Application;
import javafx.stage.Stage;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.util.AnnotationLiteral;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();

        stage.setMinHeight(500);
        stage.setMinWidth(700);
        stage.setResizable(true);
        container.getBeanManager().fireEvent(stage, new AnnotationLiteral<StartupScene>() {});

//        container.getBeanManager().getEvent().select(new AnnotationLiteral<StartupScene>(){
//
//        }).fire(stage);

        stage.show();

    }
}
