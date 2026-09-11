package vallegrande.edu.pe.usuario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.usuario.controller.MainController;
import vallegrande.edu.pe.usuario.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainView view = new MainView();
        new MainController(view);
        Scene scene = new Scene(view, 900, 600);
        stage.setTitle("MI SISTEMA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}