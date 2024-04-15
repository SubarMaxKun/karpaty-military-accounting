package org.shevliakov.carpatymilitaryaccounting.controller.util;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class AddRowClickHandling {

  public void rowClickHandling(TableView<Worker> tableView) {
    tableView.setRowFactory(tv -> {
      TableRow<Worker> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
            && event.getClickCount() == 2) {
          Worker clickedRow = row.getItem();
          new OpenEditWorkerInfoStage().openStage(row.getScene().getWindow(), clickedRow);
        }
      });
      return row;
    });
  }
}
