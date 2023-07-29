package pt.ul.fc.css.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.ul.fc.css.example.demo.controllers.ClientController;

public class Client extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    String prefix = "/static/css/presentation/view/";

    BorderPane root = new BorderPane();
    FXMLLoader testeLoader = new FXMLLoader(getClass().getResource(prefix + "MenuPrincipal.fxml"));

    root.setCenter(testeLoader.load());

    ClientController controller = testeLoader.getController();

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
