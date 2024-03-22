package org.shevliakov.carpatymilitaryaccounting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Worker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "rank")
  private String rank;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "registration_number")
  private String registrationNumber;

  @Column(name = "military_specialty")
  private String militarySpecialty;

  @Column(name = "training")
  private String training;

  @Column(name = "accounting_category")
  private String accountingCategory;

  @Column(name = "degree")
  private String degree;

  @Column(name = "id_info")
  private String idInfo;
}
