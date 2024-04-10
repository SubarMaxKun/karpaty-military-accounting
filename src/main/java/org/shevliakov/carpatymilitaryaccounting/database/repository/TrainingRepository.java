package org.shevliakov.carpatymilitaryaccounting.database.repository;

import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.entity.Training;

public interface TrainingRepository {

  List<Training> getAllTraining();

  Training getTrainingById(Long id);

  Training getTrainingByName(String name);

  void persistTraining(Training training);

  void updateTraining(Training training);

  void deleteTraining(Training training);

}
