package org.shevliakov.carpatymilitaryaccounting.controller;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.carpatymilitaryaccounting.database.repository.impl.RankRepositoryImpl;
import org.shevliakov.carpatymilitaryaccounting.database.repository.impl.TrainingRepositoryImpl;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Training;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class EditWorkerInfoController {
  @FXML
  private TextField FullNameTextField;
  @FXML
  private ChoiceBox<String> RankChoiceBox;
  @FXML
  private DatePicker BirthDateDatePicker;
  @FXML
  private TextField RegistrationNumberTextField;
  @FXML
  private TextField MilitarySpecialtyTextField;
  @FXML
  private ChoiceBox TrainingChoiceBox;
  @FXML
  private TextField AccountingCategoryTextField;
  @FXML
  private TextField DegreeTextField;
  @FXML
  private TextArea IdInfoTextArea;
  @FXML
  private Button CancelButton;
  @FXML
  private Button UpdateButton;
  @FXML
  private Button DeleteButton;

  public void initialize() {
    List<Rank> ranks = new RankRepositoryImpl().getAllRanks();
    for (Rank rank : ranks) {
      RankChoiceBox.getItems().add(rank.toString());
    }

    List<Training> trainings = new TrainingRepositoryImpl().getAllTraining();
    for (Training training : trainings) {
      TrainingChoiceBox.getItems().add(training.toString());
    }

  }

  public void setWorkerInfo(Worker worker) {
    FullNameTextField.setText(worker.getFullName());
    RankChoiceBox.setValue(worker.getRank().toString());
    BirthDateDatePicker.setValue(worker.getBirthDate().toLocalDate());
    RegistrationNumberTextField.setText(worker.getRegistrationNumber());
    MilitarySpecialtyTextField.setText(worker.getMilitarySpecialty());
    TrainingChoiceBox.setValue(worker.getTraining().toString());
    AccountingCategoryTextField.setText(worker.getAccountingCategory());
    DegreeTextField.setText(worker.getDegree());
    IdInfoTextArea.setText(worker.getIdInfo());
  }

  @FXML
  private void onCancelButtonClicked() {
    CancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void onUpdateButtonClicked() {
    System.out.println("Update button clicked");
  }

  @FXML
  private void onDeleteButtonClicked() {
    System.out.println("Delete button clicked");
  }
}
