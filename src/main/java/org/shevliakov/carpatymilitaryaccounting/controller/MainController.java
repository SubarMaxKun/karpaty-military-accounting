package org.shevliakov.carpatymilitaryaccounting.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.shevliakov.carpatymilitaryaccounting.controller.subcontroller.RankTabSubController;
import org.shevliakov.carpatymilitaryaccounting.controller.subcontroller.WorkerTabSubController;
import org.shevliakov.carpatymilitaryaccounting.controller.util.OpenEditWorkerInfoStage;
import org.shevliakov.carpatymilitaryaccounting.database.config.SpringConfig;
import org.shevliakov.carpatymilitaryaccounting.database.repository.RankRepository;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController {

  @FXML
  public TableColumn<?, ?> rankNameColumn;
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
  private WorkerTabSubController workerTabSubController;
  private RankTabSubController rankTabSubController;

  public void initialize() {
    var context = new AnnotationConfigApplicationContext(SpringConfig.class);

    workerTabSubController = new WorkerTabSubController(rankChoiceBox, birthYearChoiceBox,
        nameSearchTextField, workersTableView, rankColumn, fullNameColumn, birthDateColumn,
        registrationNumberColumn, militarySpecialtyColumn, trainingColumn, accountingCategoryColumn,
        degreeColumn, idInfoColumn, context.getBean(WorkerRepository.class));

    setupWorkersTab();

    rankTabSubController = new RankTabSubController(ranksTableView, rankNameColumn,
        rankSearchTextField,
        context.getBean(RankRepository.class));

    setupRanksTab();
  }

  private void setupWorkersTab() {
    workerTabSubController.loadData();
    workerTabSubController.setupTableColumns();
    workerTabSubController.setupFiltering();
  }

  private void setupRanksTab() {
    rankTabSubController.loadData();
    rankTabSubController.setupTableColumns();
  }

  @FXML
  private void onRefreshWorkersButtonClicked() {
    workerTabSubController.refreshData();
  }

  @FXML
  private void onAddWorkerButtonClicked() {
    new OpenEditWorkerInfoStage().openStage(Window.getWindows().getFirst(), null);
  }

  @FXML
  private void onRefreshRanksButtonClicked() {
    rankTabSubController.refreshData();
  }

  @FXML
  public void onAddRankButtonClicked() {
    rankTabSubController.addRank();
  }
}
