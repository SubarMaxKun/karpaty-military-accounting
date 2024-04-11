package org.shevliakov.carpatymilitaryaccounting.controller;

import jakarta.transaction.Transactional;
import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DumpWorkers {

  @Autowired
  WorkerRepository workerRepository;

  public List<Worker> dump() {
    if (workerRepository != null) {
      return workerRepository.findAll();
    } else {
      System.err.println("WorkerRepository is null. Check your configuration.");
    }
    return null;
  }
}
