package org.shevliakov.carpatymilitaryaccounting.controller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.carpatymilitaryaccounting.controller.filter.FilterWorkerByBirthYear;
import org.shevliakov.carpatymilitaryaccounting.controller.filter.FilterWorkerByRank;
import org.shevliakov.carpatymilitaryaccounting.controller.search.SearchWorkerByName;
import org.shevliakov.carpatymilitaryaccounting.database.config.SpringConfig;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController {

  @FXML
  private ChoiceBox<Rank> rankChoiceBox;
  @FXML
  private ChoiceBox<Integer> birthYearChoiceBox;
  @FXML
  private TextField nameSearchTextField;
  @FXML
  private TableView<Worker> workersTableView;
  @FXML
  private TableColumn<Worker, String> rankColumn;
  @FXML
  private TableColumn<?, ?> fullNameColumn;
  @FXML
  private TableColumn<?, ?> birthDateColumn;
  @FXML
  private TableColumn<?, ?> registrationNumberColumn;
  @FXML
  private TableColumn<?, ?> militarySpecialtyColumn;
  @FXML
  private TableColumn<Worker, String> trainingColumn;
  @FXML
  private TableColumn<?, ?> accountingCategoryColumn;
  @FXML
  private TableColumn<?, ?> degreeColumn;
  @FXML
  private TableColumn<?, ?> idInfoColumn;
  @FXML
  private TableColumn<Worker, Button> editColumn;
  private List<Worker> workers;
  private ObservableList<Worker> workersObservableList;

  public void initialize() {
    retrieveData();
    fillTable();
    new SearchWorkerByName().search(nameSearchTextField, workers, workersObservableList);
    new FilterWorkerByBirthYear().filter(birthYearChoiceBox, workers, workersObservableList);
    new FilterWorkerByRank().filter(rankChoiceBox, workers, workersObservableList);
  }

  private void fillTable() {
    rankColumn.setCellValueFactory(workerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        workerStringCellDataFeatures.getValue().getRank().getName()));
    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
    militarySpecialtyColumn.setCellValueFactory(new PropertyValueFactory<>("militarySpecialty"));
    trainingColumn.setCellValueFactory(workerStringCellDataFeatures -> new ReadOnlyStringWrapper(
        workerStringCellDataFeatures.getValue().getTraining().getName()));
    accountingCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("accountingCategory"));
    degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
    idInfoColumn.setCellValueFactory(new PropertyValueFactory<>("idInfo"));
    new AddEditButtonsToTable().addEditButtonsToTable(editColumn);
  }

  private void retrieveData() {
    // initialize Spring context and get WorkerRepository bean
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    var workerRepository = context.getBean(WorkerRepository.class);

    workers = workerRepository.findAll();
    workersObservableList = workersTableView.getItems();
    workersObservableList.addAll(workers);

    // TODO: implement choice box data initialization
    birthYearChoiceBox.getItems().add(null);
    birthYearChoiceBox.getItems().addAll();

    rankChoiceBox.getItems().add(null);
    rankChoiceBox.getItems().addAll(workerRepository.getDistinctRanks());
  }

  @FXML
  private void onRefreshButtonClicked() {
    // TODO: implement
  }
}
