package org.shevliakov.carpatymilitaryaccounting.database.repository;

import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;

public interface RankRepository {
  List<Rank> getAllRanks();
  Rank getRankById(Long id);
  Rank getRankByName(String name);
  void persistRank(Rank rank);
  void updateRank(Rank rank);
  void deleteRank(Rank rank);

}
