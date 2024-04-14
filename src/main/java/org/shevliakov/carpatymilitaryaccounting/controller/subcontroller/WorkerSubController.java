package org.shevliakov.carpatymilitaryaccounting.controller.subcontroller;

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
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class WorkerSubController {

  @FXML
  private final ChoiceBox<Rank> rankChoiceBox;
  @FXML
  private final ChoiceBox<Integer> birthYearChoiceBox;
  @FXML
  private final TextField nameSearchTextField;
  @FXML
  private final TableView<Worker> workersTableView;
  @FXML
  private final TableColumn<Worker, String> rankColumn;
  @FXML
  private final TableColumn<?, ?> fullNameColumn;
  @FXML
  private final TableColumn<?, ?> birthDateColumn;
  @FXML
  private final TableColumn<?, ?> registrationNumberColumn;
  @FXML
  private final TableColumn<?, ?> militarySpecialtyColumn;
  @FXML
  private final TableColumn<Worker, String> trainingColumn;
  @FXML
  private final TableColumn<?, ?> accountingCategoryColumn;
  @FXML
  private final TableColumn<?, ?> degreeColumn;
  @FXML
  private final TableColumn<?, ?> idInfoColumn;
  WorkerRepository workerRepository;
  private List<Worker> workers;
  private ObservableList<Worker> workersObservableList;


  public WorkerSubController(ChoiceBox<Rank> rankChoiceBox, ChoiceBox<Integer> birthYearChoiceBox,
      TextField nameSearchTextField, TableView<Worker> workersTableView,
      TableColumn<Worker, String> rankColumn, TableColumn<?, ?> fullNameColumn,
      TableColumn<?, ?> birthDateColumn, TableColumn<?, ?> registrationNumberColumn,
      TableColumn<?, ?> militarySpecialtyColumn, TableColumn<Worker, String> trainingColumn,
      TableColumn<?, ?> accountingCategoryColumn, TableColumn<?, ?> degreeColumn,
      TableColumn<?, ?> idInfoColumn, WorkerRepository workerRepository) {
    this.rankChoiceBox = rankChoiceBox;
    this.birthYearChoiceBox = birthYearChoiceBox;
    this.nameSearchTextField = nameSearchTextField;
    this.workersTableView = workersTableView;
    this.rankColumn = rankColumn;
    this.fullNameColumn = fullNameColumn;
    this.birthDateColumn = birthDateColumn;
    this.registrationNumberColumn = registrationNumberColumn;
    this.militarySpecialtyColumn = militarySpecialtyColumn;
    this.trainingColumn = trainingColumn;
    this.accountingCategoryColumn = accountingCategoryColumn;
    this.degreeColumn = degreeColumn;
    this.idInfoColumn = idInfoColumn;
    this.workerRepository = workerRepository;
  }

  public void loadData() {
    // Fill the table with data
    workers = workerRepository.findAll();
    workersObservableList = workersTableView.getItems();
    workersObservableList.addAll(workers);

    // Fill the choice box with years of birth
    List<Integer> years = ConvertDatesToYears.convert(workerRepository.getDistinctBirthDates());
    birthYearChoiceBox.getItems().add(null);
    birthYearChoiceBox.getItems().addAll(years);

    // Fill the choice box with ranks
    rankChoiceBox.getItems().add(null);
    rankChoiceBox.getItems().addAll(workerRepository.getDistinctRanks());
  }

  public void refreshData() {
    workers.clear();
    workersObservableList.clear();
    rankChoiceBox.getItems().clear();
    birthYearChoiceBox.getItems().clear();
    loadData();
    setupFiltering();
    workersTableView.refresh();
  }

  public void setupFiltering() {
    new SearchWorkerByName().search(nameSearchTextField, workers, workersObservableList);
    new FilterWorkerByBirthYear().filter(birthYearChoiceBox, rankChoiceBox, workers,
        workersObservableList);
    new FilterWorkerByRank().filter(rankChoiceBox, birthYearChoiceBox, workers,
        workersObservableList);
    new AddRowClickHandling().rowClickHandling(workersTableView);
  }

  public void setupTableColumns() {
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
  }
}
