package org.shevliakov.carpatymilitaryaccounting.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class AddEditButtonsToTable {

  public void addEditButtonsToTable(TableColumn<Worker, Button> editColumn) {
    Callback<TableColumn<Worker, Button>, TableCell<Worker, Button>> cellFactory = param -> new TableCell<>() {
      final Button btn = new Button("Редагувати");

      @Override
      public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
          setText(null);
        } else {
          btn.setOnAction(event -> {
            Worker worker = getTableView().getItems().get(getIndex());
            // Create window for editing worker info
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
            controller.setWorkerInfo(worker);
            stage.initOwner(btn.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    editColumn.setCellFactory(cellFactory);
  }

}
