package vallegrande.edu.pe.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vallegrande.edu.pe.controller.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Instanciamos la vista y el controlador
        MainView view = new MainView();
        MainController controller = new MainController(view);

        // Creamos la escena principal
        Scene scene = new Scene(view, 800, 500);

        primaryStage.setTitle("Sistema de Gestión de Usuarios");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}