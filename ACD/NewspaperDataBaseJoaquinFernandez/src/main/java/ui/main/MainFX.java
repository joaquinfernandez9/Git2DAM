package ui.main;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;

public class MainFX {

    @Inject
    FXMLLoader loader;

    public void start(@Observes @StartupScene Stage stage) throws IOException {
        Parent fxmlParent = loader.load(getClass()
                .getResourceAsStream("/fxml/principal.fxml"));

        PrincipalController controller  = loader.getController();
        controller.setStage(stage);
        stage.setScene(new Scene(fxmlParent));
        stage.setTitle("Newspaper");
        stage.show();
    }

}
