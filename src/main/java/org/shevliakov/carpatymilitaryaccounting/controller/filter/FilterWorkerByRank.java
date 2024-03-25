package org.shevliakov.carpatymilitaryaccounting.controller.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class FilterWorkerByRank {
  public void filter(ChoiceBox<String> rankChoiceBox, List<Worker> workers,
      ObservableList<Worker> workersObservableList) {
    rankChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(String rank) {
        return rank == null ? "Всі" : rank;
      }

      @Override
      public String fromString(String s) {
        return null;
      }
    });

    rankChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            workersObservableList.clear();
            workersObservableList.addAll(workers);
          } else {
            workersObservableList.clear();
            workersObservableList.addAll(workers);
            workersObservableList.removeIf(worker -> !worker.getRank().equals(newValue));
          }
        });
  }

}
