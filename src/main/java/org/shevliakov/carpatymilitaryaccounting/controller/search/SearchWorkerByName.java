package org.shevliakov.carpatymilitaryaccounting.controller.search;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class SearchWorkerByName {

  public void search(TextField nameTextField, List<Worker> workers,
      ObservableList<Worker> workersObservableList) {
    nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.isEmpty()) {
        workersObservableList.clear();
        workersObservableList.addAll(workers);
      } else if (newValue.length() > oldValue.length() || newValue.length() < oldValue.length()) {
        workersObservableList.clear();
        workersObservableList.addAll(workers);
        workersObservableList.removeIf(
            student -> !student.getFullName().toLowerCase().contains(newValue.toLowerCase()));
      }
    });
  }
}
