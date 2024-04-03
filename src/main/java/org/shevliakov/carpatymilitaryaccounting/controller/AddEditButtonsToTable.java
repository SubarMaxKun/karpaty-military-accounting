package org.shevliakov.carpatymilitaryaccounting.controller;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(btn.getScene().getWindow());
            dialog.setTitle("Редагування інформації про працівника");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/org/shevliakov/carpatymilitaryaccounting/view/edit-worker-info-view.fxml"));
            try {
              dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
              e.printStackTrace();
              return;
            }
            EditWorkerInfoController controller = fxmlLoader.getController();
            controller.setWorkerInfo(worker);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
              // Save changes
              //controller.saveWorkerInfo(worker);
            }
          });
          setGraphic(btn);
          setText(null);
        }
      }
    };
    editColumn.setCellFactory(cellFactory);
  }

}
