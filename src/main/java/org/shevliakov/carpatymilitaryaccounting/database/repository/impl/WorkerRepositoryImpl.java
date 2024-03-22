package org.shevliakov.carpatymilitaryaccounting.database.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.database.DatabaseManager;
import org.shevliakov.carpatymilitaryaccounting.database.repository.WorkerRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Worker;

public class WorkerRepositoryImpl implements WorkerRepository {

  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public List<Worker> getAllWorkers() {
    TypedQuery<Worker> query = entityManager.createQuery("SELECT w FROM Worker w", Worker.class);
    return query.getResultList();
  }

  @Override
  public Worker getWorkerById(Long id) {
    return entityManager.find(Worker.class, id);
  }

  @Override
  public void persistWorker(Worker worker) {
    if (worker.getId() == null){
      entityManager.persist(worker);
      entityManager.getTransaction().commit();
    } else {
      updateWorker(worker);
    }
  }

  @Override
  public void updateWorker(Worker worker) {
    entityManager.merge(worker);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteWorker(Worker worker) {
    entityManager.remove(worker);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteWorkerById(Long id) {
    Worker worker = getWorkerById(id);
    deleteWorker(worker);
  }
}
