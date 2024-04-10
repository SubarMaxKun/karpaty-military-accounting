package org.shevliakov.carpatymilitaryaccounting.database.repository.impl;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.database.DatabaseManager;
import org.shevliakov.carpatymilitaryaccounting.database.repository.TrainingRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Training;

public class TrainingRepositoryImpl implements TrainingRepository {

  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public List<Training> getAllTraining() {
    return entityManager.createQuery("SELECT t FROM Training t", Training.class).getResultList();
  }

  @Override
  public Training getTrainingById(Long id) {
    return entityManager.find(Training.class, id);
  }

  @Override
  public Training getTrainingByName(String name) {
    return entityManager.createQuery("SELECT t FROM Training t WHERE t.name = :name", Training.class)
        .setParameter("name", name)
        .getSingleResult();
  }

  @Override
  public void persistTraining(Training training) {
    if (training.getId() == null) {
      entityManager.persist(training);
      entityManager.getTransaction().commit();
    } else {
      updateTraining(training);
    }
  }

  @Override
  public void updateTraining(Training training) {
    entityManager.merge(training);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteTraining(Training training) {
    entityManager.remove(training);
    entityManager.getTransaction().commit();
  }
}
