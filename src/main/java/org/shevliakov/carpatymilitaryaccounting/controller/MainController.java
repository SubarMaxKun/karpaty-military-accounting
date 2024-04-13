package org.shevliakov.carpatymilitaryaccounting.controller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.carpatymilitaryaccounting.controller.filter.FilterWorkerByBirthYear;
import org.shevliakov.carpatymilitaryaccounting.controller.filter.FilterWorkerByRank;
import org.shevliakov.carpatymilitaryaccounting.controller.search.SearchWorkerByName;
import org.shevliakov.carpatymilitaryaccounting.controller.util.AddRowClickHandling;
import org.shevliakov.carpatymilitaryaccounting.controller.util.ConvertDatesToYears;
import org.shevliakov.carpatymilitaryaccounting.database.config.SpringConfig;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController {

  WorkerRepository workerRepository;
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
  private List<Worker> workers;
  private ObservableList<Worker> workersObservableList;

  public void initialize() {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);
    workerRepository = context.getBean(WorkerRepository.class);

    loadData();
    setupTableColumns();

    new SearchWorkerByName().search(nameSearchTextField, workers, workersObservableList);
    new FilterWorkerByBirthYear().filter(birthYearChoiceBox, rankChoiceBox, workers, workersObservableList);
    new FilterWorkerByRank().filter(rankChoiceBox, birthYearChoiceBox, workers, workersObservableList);
    new AddRowClickHandling().rowClickHandling(workersTableView);
  }

  private void setupTableColumns() {
    rankColumn.setCellValueFactory(workerStringCellDataFeatures ->
        new ReadOnlyStringWrapper(workerStringCellDataFeatures.getValue().getRank().getName()));
    fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));
    militarySpecialtyColumn.setCellValueFactory(new PropertyValueFactory<>("militarySpecialty"));
    trainingColumn.setCellValueFactory(workerStringCellDataFeatures ->
        new ReadOnlyStringWrapper(workerStringCellDataFeatures.getValue().getTraining().getName()));
    accountingCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("accountingCategory"));
    degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));
    idInfoColumn.setCellValueFactory(new PropertyValueFactory<>("idInfo"));
  }

  private void loadData() {
    workers = workerRepository.findAll();
    workersObservableList = workersTableView.getItems();
    workersObservableList.addAll(workers);

    List<Integer> years = ConvertDatesToYears.convert(workerRepository.getDistinctBirthDates());
    birthYearChoiceBox.getItems().addAll(years);

    rankChoiceBox.getItems().addAll(workerRepository.getDistinctRanks());
  }


  @FXML
  private void onRefreshButtonClicked() {
    workers.clear();
    workersObservableList.clear();
    rankChoiceBox.getItems().clear();
    birthYearChoiceBox.getItems().clear();
    initialize();
    workersTableView.refresh();
  }
}
