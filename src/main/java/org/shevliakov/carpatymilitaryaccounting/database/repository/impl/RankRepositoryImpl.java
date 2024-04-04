package org.shevliakov.carpatymilitaryaccounting.database.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.shevliakov.carpatymilitaryaccounting.database.DatabaseManager;
import org.shevliakov.carpatymilitaryaccounting.database.repository.RankRepository;
import org.shevliakov.carpatymilitaryaccounting.entity.Rank;

public class RankRepositoryImpl implements RankRepository {
  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public List<Rank> getAllRanks() {
    TypedQuery<Rank> query = entityManager.createQuery("SELECT r FROM Rank r", Rank.class);
    return query.getResultList();
  }

  @Override
  public Rank getRankById(Long id) {
    return entityManager.find(Rank.class, id);
  }

  @Override
  public Rank getRankByName(String name) {
    TypedQuery<Rank> query = entityManager.createQuery("SELECT r FROM Rank r WHERE r.name = :name", Rank.class);
    query.setParameter("name", name);
    return query.getSingleResult();
  }

  @Override
  public void persistRank(Rank rank) {
    if (rank.getId() == null) {
      entityManager.persist(rank);
      entityManager.getTransaction().commit();
    } else {
      updateRank(rank);
    }
  }

  @Override
  public void updateRank(Rank rank) {
    entityManager.merge(rank);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteRank(Rank rank) {
    entityManager.remove(rank);
    entityManager.getTransaction().commit();
  }
}
