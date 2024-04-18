package org.shevliakov.carpatymilitaryaccounting;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends javafx.application.Application {

  /**
   * Main method.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Start method.
   *
   * @param stage stage
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Військовий облік ТОВ \"Термінал Карпати\"");
    stage.setScene(scene);
    stage.show();
  }

}
