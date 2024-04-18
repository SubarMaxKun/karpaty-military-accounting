package org.shevliakov.carpatymilitaryaccounting.controller.filter;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;


/**
 * Class for filtering workers by birth year using ChoiceBox.
 */
public class FilterWorkerByBirthYear {

  /**
   * Filters workers by birth year using ChoiceBox.
   *
   * @param yearChoiceBox         choice box for selecting a year
   * @param rankChoiceBox         choice box for selecting a rank
   * @param workers               list of workers
   * @param workersObservableList observable list of workers
   */
  public void filter(ChoiceBox<Integer> yearChoiceBox, ChoiceBox<Rank> rankChoiceBox,
      List<Worker> workers, ObservableList<Worker> workersObservableList) {
    // Initialize converter for yearChoiceBox.
    yearChoiceBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(Integer year) {
        return year == null ? "Всі" : year.toString();
      }

      @Override
      public Integer fromString(String s) {
        return null;
      }
    });

    // Add listener to yearChoiceBox.
    yearChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue == null) {
            workersObservableList.clear();
            workersObservableList.addAll(workers);
          } else {
            rankChoiceBox.getSelectionModel().clearSelection();
            workersObservableList.clear();
            workersObservableList.addAll(workers);
            workersObservableList.removeIf(
                worker -> worker.getBirthDate().toLocalDate().getYear() != newValue);
          }
        });
  }
}
