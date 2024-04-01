package org.shevliakov.carpatymilitaryaccounting.database.repository;

import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public interface WorkerRepository {

  List<Worker> getAllWorkers();

  Worker getWorkerById(Long id);

  List<Integer> getYearsOfBirth();

  List<Rank> getRanks();

  void persistWorker(Worker worker);

  void updateWorker(Worker worker);

  void deleteWorker(Worker worker);

  void deleteWorkerById(Long id);

}
