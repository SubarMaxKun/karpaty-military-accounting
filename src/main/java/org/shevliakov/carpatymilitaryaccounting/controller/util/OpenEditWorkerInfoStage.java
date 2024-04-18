package org.shevliakov.carpatymilitaryaccounting.controller.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.shevliakov.carpatymilitaryaccounting.controller.EditWorkerInfoController;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

/**
 * Utility class for opening the stage for editing worker information.
 */
public class OpenEditWorkerInfoStage {

  /**
   * Opens the stage for editing worker information.
   *
   * @param owner  the owner window
   * @param worker the worker to edit
   */
  public void openStage(Window owner, Worker worker) {
    Stage stage = new Stage();
    stage.setResizable(false);
    stage.setTitle("Інформація про працівника");
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
    controller.initialize(worker);
    stage.initOwner(owner);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }
}
