package org.shevliakov.carpatymilitaryaccounting.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.carpatymilitaryaccounting.controller.search.SearchWorkerByName;
import org.shevliakov.carpatymilitaryaccounting.database.repository.impl.WorkerRepositoryImpl;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class MainController implements Initializable {

  @FXML
  private TextField nameSearchTextField;
  @FXML
  private TableView<Worker> workersTableView;
  @FXML
  private TableColumn<?, ?> rankColumn;
  @FXML
  private TableColumn<?, ?> fullNameColumn;
  @FXML
  private TableColumn<?, ?> birthDateColumn;
  @FXML
  private TableColumn<?, ?> registrationNumberColumn;
  @FXML
  private TableColumn<?, ?> militarySpecialtyColumn;
  @FXML
  private TableColumn<?, ?> trainingColumn;
  @FXML
  private TableColumn<?, ?> accountingCategoryColumn;
  @FXML
  private TableColumn<?, ?> degreeColumn;
  @FXML
  private TableColumn<?, ?> idInfoColumn;

  private List<Worker> workers;
  private ObservableList<Worker> workersObservableList;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    retrieveWorkers();
    fillTable();
    new SearchWorkerByName().search(nameSearchTextField, workers, workersObservableList);
  }

  private void fillTable() {
    rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
    militarySpecialtyColumn.setCellValueFactory(new PropertyValueFactory<>("militarySpecialty"));
    trainingColumn.setCellValueFactory(new PropertyValueFactory<>("training"));
    accountingCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("accountingCategory"));
    degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
    idInfoColumn.setCellValueFactory(new PropertyValueFactory<>("idInfo"));
  }

  private void retrieveWorkers() {
    WorkerRepositoryImpl workerRepository = new WorkerRepositoryImpl();
    workers = workerRepository.getAllWorkers();
    workersObservableList = workersTableView.getItems();
    workersObservableList.addAll(workers);
  }
}
