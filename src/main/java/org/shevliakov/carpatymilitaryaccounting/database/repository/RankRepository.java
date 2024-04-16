package org.shevliakov.carpatymilitaryaccounting.database.repository;

import org.shevliakov.carpatymilitaryaccounting.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

  Rank findByName(String name);

  boolean existsByName(String name);
}