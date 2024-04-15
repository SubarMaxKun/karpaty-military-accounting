package org.shevliakov.carpatymilitaryaccounting.controller;

import java.sql.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.carpatymilitaryaccounting.database.config.SpringConfig;
import org.shevliakov.carpatymilitaryaccounting.database.repository.RankRepository;
import org.shevliakov.carpatymilitaryaccounting.database.repository.TrainingRepository;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Training;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
  private Button commitButton;
  @FXML
  private Button deleteButton;
  private Worker worker;
  private WorkerRepository workerRepository;
  private RankRepository rankRepository;
  private TrainingRepository trainingRepository;

  public void initialize(Worker worker) {
    if (worker != null) {
      this.worker = worker;
      setWorkerInfo();
    } else {
      commitButton.setText("Додати");
      deleteButton.setVisible(false);
    }

    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    workerRepository = context.getBean(WorkerRepository.class);
    rankRepository = context.getBean(RankRepository.class);
    trainingRepository = context.getBean(TrainingRepository.class);

    List<Rank> ranks = rankRepository.findAll();
    for (Rank rank : ranks) {
      rankChoiceBox.getItems().add(rank.getName());
    }

    List<Training> trainings = trainingRepository.findAll();
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
  private void onCommitButtonClicked() {
    worker.setFullName(fullNameTextField.getText());
    worker.setRank(rankRepository.findByName(rankChoiceBox.getValue()));
    worker.setBirthDate(Date.valueOf(birthDateDatePicker.getValue()));
    worker.setRegistrationNumber(registrationNumberTextField.getText());
    worker.setMilitarySpecialty(militarySpecialtyTextField.getText());
    worker.setTraining(trainingRepository.findByName(trainingChoiceBox.getValue()));
    worker.setAccountingCategory(accountingCategoryTextField.getText());
    worker.setDegree(degreeTextField.getText());
    worker.setIdInfo(idInfoTextArea.getText());
    workerRepository.save(worker);
    commitButton.getScene().getWindow().hide();
  }

  @FXML
  private void onDeleteButtonClicked() {
    workerRepository.delete(worker);
    deleteButton.getScene().getWindow().hide();
  }
}
