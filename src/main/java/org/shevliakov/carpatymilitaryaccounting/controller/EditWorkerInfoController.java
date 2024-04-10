package org.shevliakov.carpatymilitaryaccounting.controller;

import java.sql.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.carpatymilitaryaccounting.database.repository.impl.RankRepositoryImpl;
import org.shevliakov.carpatymilitaryaccounting.database.repository.impl.TrainingRepositoryImpl;
import org.shevliakov.carpatymilitaryaccounting.database.repository.impl.WorkerRepositoryImpl;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Training;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class EditWorkerInfoController {
  @FXML
  private TextField fullNameTextField;
  @FXML
  private ChoiceBox<String> rankChoiceBox;
  @FXML
  private DatePicker birthDateDatePicker;
  @FXML
  private TextField registrationNumberTextField;
  @FXML
  private TextField militarySpecialtyTextField;
  @FXML
  private ChoiceBox<String> trainingChoiceBox;
  @FXML
  private TextField accountingCategoryTextField;
  @FXML
  private TextField degreeTextField;
  @FXML
  private TextArea idInfoTextArea;
  @FXML
  private Button cancelButton;
  @FXML
  private Button updateButton;
  @FXML
  private Button deleteButton;

  private Worker worker;

  public void initialize(Worker worker) {
    this.worker = worker;
    setWorkerInfo();

    List<Rank> ranks = new RankRepositoryImpl().getAllRanks();
    for (Rank rank : ranks) {
      rankChoiceBox.getItems().add(rank.getName());
    }

    List<Training> trainings = new TrainingRepositoryImpl().getAllTraining();
    for (Training training : trainings) {
      trainingChoiceBox.getItems().add(training.getName());
    }

  }

  private void setWorkerInfo() {
    fullNameTextField.setText(worker.getFullName());
    rankChoiceBox.setValue(worker.getRank().getName());
    birthDateDatePicker.setValue(worker.getBirthDate().toLocalDate());
    registrationNumberTextField.setText(worker.getRegistrationNumber());
    militarySpecialtyTextField.setText(worker.getMilitarySpecialty());
    trainingChoiceBox.setValue(worker.getTraining().getName());
    accountingCategoryTextField.setText(worker.getAccountingCategory());
    degreeTextField.setText(worker.getDegree());
    idInfoTextArea.setText(worker.getIdInfo());
  }

  @FXML
  private void onCancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void onUpdateButtonClicked() {
    WorkerRepositoryImpl workerRepository = new WorkerRepositoryImpl();
    worker.setFullName(fullNameTextField.getText());
    worker.setRank(new RankRepositoryImpl().getRankByName(rankChoiceBox.getValue()));
    worker.setBirthDate(Date.valueOf(birthDateDatePicker.getValue()));
    worker.setRegistrationNumber(registrationNumberTextField.getText());
    worker.setMilitarySpecialty(militarySpecialtyTextField.getText());
    worker.setTraining(new TrainingRepositoryImpl().getTrainingByName(trainingChoiceBox.getValue()));
    worker.setAccountingCategory(accountingCategoryTextField.getText());
    worker.setDegree(degreeTextField.getText());
    worker.setIdInfo(idInfoTextArea.getText());
    workerRepository.updateWorker(worker);
    updateButton.getScene().getWindow().hide();
  }

  @FXML
  private void onDeleteButtonClicked() {
    WorkerRepositoryImpl workerRepository = new WorkerRepositoryImpl();
    workerRepository.deleteWorkerById(worker.getId());
    deleteButton.getScene().getWindow().hide();
  }
}
