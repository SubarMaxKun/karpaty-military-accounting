package org.shevliakov.carpatymilitaryaccounting.database.repository;

import org.shevliakov.carpatymilitaryaccounting.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

  Training findByName(String name);
}
