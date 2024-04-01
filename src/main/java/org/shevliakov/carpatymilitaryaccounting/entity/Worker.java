package org.shevliakov.carpatymilitaryaccounting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class Worker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rank")
  private Rank rank;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "registration_number")
  private String registrationNumber;

  @Column(name = "military_specialty")
  private String militarySpecialty;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "training")
  private Training training;

  @Column(name = "accounting_category")
  private String accountingCategory;

  @Column(name = "degree")
  private String degree;

  @Column(name = "id_info")
  private String idInfo;
}
