package org.shevliakov.carpatymilitaryaccounting.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.shevliakov.carpatymilitaryaccounting.controller.subcontroller.WorkerSubController;
import org.shevliakov.carpatymilitaryaccounting.controller.util.OpenAddWorkerInfo;
import org.shevliakov.carpatymilitaryaccounting.database.config.SpringConfig;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController {

  @FXML
  public TableColumn<?, ?> rankNameColumn;
  @FXML
  public TableColumn<?, ?> deleteRankColumn;
  @FXML
  private TableView<Rank> ranksTableView;
  @FXML
  private TextField rankSearchTextField;
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

  private WorkerSubController workerSubController;

  public void initialize() {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    workerSubController = new WorkerSubController(rankChoiceBox, birthYearChoiceBox,
        nameSearchTextField, workersTableView, rankColumn, fullNameColumn, birthDateColumn,
        registrationNumberColumn, militarySpecialtyColumn, trainingColumn, accountingCategoryColumn,
        degreeColumn, idInfoColumn, context.getBean(WorkerRepository.class));

    setupWorkersTab();
  }

  private void setupWorkersTab() {
    workerSubController.loadData();
    workerSubController.setupTableColumns();
    workerSubController.setupFiltering();
  }

  @FXML
  private void onRefreshWorkersButtonClicked() {
    workerSubController.refreshData();
  }

  @FXML
  private void onAddWorkerButtonClicked() {
    new OpenAddWorkerInfo().open(Window.getWindows().getFirst());
  }

  @FXML
  private void onRefreshRanksButtonClicked() {
  }

  @FXML
  public void onAddRankButtonClicked() {
  }
}
