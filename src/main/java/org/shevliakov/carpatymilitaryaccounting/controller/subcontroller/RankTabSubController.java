package org.shevliakov.carpatymilitaryaccounting.controller.subcontroller;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.shevliakov.carpatymilitaryaccounting.database.repository.RankRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;

/**
 * Subcontroller for Rank tab.
 */
public class RankTabSubController {

  private final TableView<Rank> ranksTableView;
  private final TableColumn<?, ?> rankNameColumn;
  private final TextField rankSearchTextField;
  private final RankRepository rankRepository;
  private List<Rank> ranks;
  private ObservableList<Rank> ranksObservableList;

  /**
   * Constructor.
   *
   * @param ranksTableView      TableView for ranks.
   * @param rankNameColumn      TableColumn for rank name.
   * @param rankSearchTextField TextField for rank search.
   * @param rankRepository      Rank repository.
   */
  public RankTabSubController(TableView<Rank> ranksTableView, TableColumn<?, ?> rankNameColumn,
      TextField rankSearchTextField, RankRepository rankRepository) {
    this.ranksTableView = ranksTableView;
    this.rankNameColumn = rankNameColumn;
    this.rankSearchTextField = rankSearchTextField;
    this.rankRepository = rankRepository;
  }

  /**
   * Load data from database.
   */
  public void loadData() {
    ranks = rankRepository.findAll();
    ranksObservableList = ranksTableView.getItems();
    ranksObservableList.addAll(ranks);
  }

  /**
   * Setup table columns.
   */
  public void setupTableColumns() {
    rankNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
  }

  /**
   * Refresh data in TableView.
   */
  public void refreshData() {
    ranks.clear();
    ranksObservableList.clear();
    loadData();
    ranksTableView.refresh();
  }

  /**
   * Add rank to database.
   */
  public void addRank() {
    if (rankSearchTextField.getText().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Помилка");
      alert.setHeaderText("Помилка додавання звання");
      alert.setContentText("Поле не може бути пустим!");
      alert.show();
      return;
    }

    if (rankRepository.existsByName(rankSearchTextField.getText())) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Помилка");
      alert.setHeaderText("Помилка додавання звання");
      alert.setContentText("Таке звання вже існує!");
      alert.show();
      return;
    }

    Rank rank = new Rank();
    rank.setName(rankSearchTextField.getText());
    rankRepository.save(rank);
    refreshData();
  }
}
