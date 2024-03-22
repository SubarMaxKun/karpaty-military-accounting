package org.shevliakov.carpatymilitaryaccounting.database;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class DatabaseManager {

  private static EntityManagerFactory entityManagerFactory;

  /**
   * Private constructor to prevent instantiation.
   */
  private DatabaseManager() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Creates and returns EntityManager instance.
   *
   * @return EntityManager instance.
   */
  public static EntityManager getEntityManager() {
    if (entityManagerFactory == null) {
      entityManagerFactory = createEntityManagerFactory(
          "org.shevliakov.military-accounting.persistence");
    }
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    return entityManager;
  }
}
