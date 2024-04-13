package org.shevliakov.carpatymilitaryaccounting.controller.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.shevliakov.carpatymilitaryaccounting.controller.EditWorkerInfoController;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class AddRowClickHandling {

  public void rowClickHandling(TableView<Worker> tableView) {
    tableView.setRowFactory(tv -> {
      TableRow<Worker> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {

          Worker clickedRow = row.getItem();

          Stage stage = new Stage();
          stage.setResizable(false);
          stage.setTitle("Редагування інформації про працівника");
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(
              "/org/shevliakov/carpatymilitaryaccounting/view/edit-worker-info-view.fxml"));
          try {
            loader.load();
          } catch (IOException e) {
            e.printStackTrace();
          }
          Scene scene = new Scene(loader.getRoot());
          stage.setScene(scene);
          EditWorkerInfoController controller = loader.getController();
          controller.initialize(clickedRow);
          stage.initOwner(row.getScene().getWindow());
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.showAndWait();
        }
      });
      return row;
    });
  }
}
