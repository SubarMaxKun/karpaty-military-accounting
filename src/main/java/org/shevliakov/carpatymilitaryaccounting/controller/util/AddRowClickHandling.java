package org.shevliakov.carpatymilitaryaccounting.controller.util;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

/**
 * Class for handling double click on row in TableView. When user double-clicks on row, new stage
 * with worker info will be opened.
 */
public class AddRowClickHandling {

  /**
   * Method for handling double click on row in TableView. When user double-clicks on row, new stage
   * with worker info will be opened.
   *
   * @param tableView TableView with workers.
   */
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
