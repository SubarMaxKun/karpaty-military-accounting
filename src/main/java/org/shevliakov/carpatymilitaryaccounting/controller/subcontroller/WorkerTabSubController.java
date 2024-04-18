package org.shevliakov.carpatymilitaryaccounting.controller.subcontroller;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
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

/**
 * WorkerTabSubController for Worker tab.
 */
public class WorkerTabSubController {

  private final ChoiceBox<Rank> rankChoiceBox;
  private final ChoiceBox<Integer> birthYearChoiceBox;
  private final TextField nameSearchTextField;
  private final TableView<Worker> workersTableView;
  private final TableColumn<Worker, String> rankColumn;
  private final TableColumn<?, ?> fullNameColumn;
  private final TableColumn<?, ?> birthDateColumn;
  private final TableColumn<?, ?> registrationNumberColumn;
  private final TableColumn<?, ?> militarySpecialtyColumn;
  private final TableColumn<Worker, String> trainingColumn;
  private final TableColumn<?, ?> accountingCategoryColumn;
  private final TableColumn<?, ?> degreeColumn;
  private final TableColumn<?, ?> idInfoColumn;
  WorkerRepository workerRepository;
  private List<Worker> workers;
  private ObservableList<Worker> workersObservableList;

  /**
   * Constructor.
   *
   * @param rankChoiceBox            ChoiceBox for rank.
   * @param birthYearChoiceBox       ChoiceBox for birth year.
   * @param nameSearchTextField      TextField for name search.
   * @param workersTableView         TableView for workers.
   * @param rankColumn               TableColumn for rank.
   * @param fullNameColumn           TableColumn for full name.
   * @param birthDateColumn          TableColumn for birthdate.
   * @param registrationNumberColumn TableColumn for registration number.
   * @param militarySpecialtyColumn  TableColumn for military specialty.
   * @param trainingColumn           TableColumn for training.
   * @param accountingCategoryColumn TableColumn for accounting category.
   * @param degreeColumn             TableColumn for degree.
   * @param idInfoColumn             TableColumn for ID info.
   * @param workerRepository         Worker repository.
   */
  public WorkerTabSubController(ChoiceBox<Rank> rankChoiceBox,
      ChoiceBox<Integer> birthYearChoiceBox, TextField nameSearchTextField,
      TableView<Worker> workersTableView, TableColumn<Worker, String> rankColumn,
      TableColumn<?, ?> fullNameColumn, TableColumn<?, ?> birthDateColumn,
      TableColumn<?, ?> registrationNumberColumn, TableColumn<?, ?> militarySpecialtyColumn,
      TableColumn<Worker, String> trainingColumn, TableColumn<?, ?> accountingCategoryColumn,
      TableColumn<?, ?> degreeColumn, TableColumn<?, ?> idInfoColumn,
      WorkerRepository workerRepository) {
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

  /**
   * Load data from database.
   */
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

  /**
   * Refresh data in TableView.
   */
  public void refreshData() {
    workers.clear();
    workersObservableList.clear();
    rankChoiceBox.getItems().clear();
    birthYearChoiceBox.getItems().clear();
    loadData();
    setupFiltering();
    workersTableView.refresh();
  }

  /**
   * Add worker to database.
   */
  public void setupFiltering() {
    new SearchWorkerByName().search(nameSearchTextField, workers, workersObservableList);
    new FilterWorkerByBirthYear().filter(birthYearChoiceBox, rankChoiceBox, workers,
        workersObservableList);
    new FilterWorkerByRank().filter(rankChoiceBox, birthYearChoiceBox, workers,
        workersObservableList);
    new AddRowClickHandling().rowClickHandling(workersTableView);
  }

  /**
   * Setup table columns.
   */
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
