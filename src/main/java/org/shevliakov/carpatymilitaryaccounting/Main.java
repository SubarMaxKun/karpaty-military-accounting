package org.shevliakov.carpatymilitaryaccounting;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("view/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Військовий облік ТОВ \"Термінал Карпати\"");
    stage.setScene(scene);
    stage.show();
  }

}
