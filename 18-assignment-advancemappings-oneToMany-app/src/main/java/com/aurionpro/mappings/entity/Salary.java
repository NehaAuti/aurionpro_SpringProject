package com.aurionpro.mappings.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salary")
@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="salaryId")
    private int salaryId;

    @Column(name="salaryMonth")
    private String salaryMonth;

    @Column(name="grossSalary")
    private Double grossSalary;

    @Column(name="deductions")
    private Double deductions;

    @Column(name="netSalary")
    private Double netSalary;

    @Column(name="paymentDate")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

  @OneToMany(mappedBy = "salary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  @JsonIgnore
  private List<SalaryTransaction> salaryTransactions;
    
 // Calculate net salary before persisting or updating the entity
    @PrePersist
    @PreUpdate
    public void calculateNetSalary() {
        if (this.grossSalary != null && this.deductions != null) {
            this.netSalary = this.grossSalary - this.deductions;
        }
    }
}