package org.shevliakov.carpatymilitaryaccounting.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class EditWorkerInfoController {
  @FXML
  private TextField FullNameTextField;
  @FXML
  private TextField RankTextField;
  @FXML
  private DatePicker BirthDateDatePicker;
  @FXML
  private TextField RegistrationNumberTextField;
  @FXML
  private TextField MilitarySpecialtyTextField;
  @FXML
  private TextField TrainingTextField;
  @FXML
  private TextField AccountingCategoryTextField;
  @FXML
  private TextField DegreeTextField;
  @FXML
  private TextArea IdInfoTextArea;

  public void setWorkerInfo(Worker worker) {
    FullNameTextField.setText(worker.getFullName());
    RankTextField.setText(worker.getRank().getRank());
    BirthDateDatePicker.setValue(worker.getBirthDate().toLocalDate());
    RegistrationNumberTextField.setText(worker.getRegistrationNumber());
    MilitarySpecialtyTextField.setText(worker.getMilitarySpecialty());
    TrainingTextField.setText(worker.getTraining().getName());
    AccountingCategoryTextField.setText(worker.getAccountingCategory());
    DegreeTextField.setText(worker.getDegree());
    IdInfoTextArea.setText(worker.getIdInfo());
  }

  @FXML
  private void onCancelButtonClicked() {
    FullNameTextField.getScene().getWindow().hide();
  }
}
