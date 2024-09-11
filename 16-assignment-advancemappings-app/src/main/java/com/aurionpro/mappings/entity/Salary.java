package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "salary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryId")
    private int salaryId;

    @Column(name = "salaryMonth")
    @NotBlank(message = "Salary month is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{4}$", message = "Salary month must be in MM/YYYY format")
    private String salaryMonth;

    @Column(name = "grossSalary")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gross salary must be positive")
    private Double grossSalary;

    @Column(name = "deductions")
    @DecimalMin(value = "0.0", inclusive = false, message = "Deductions must be positive")
    private Double deductions;

    @Column(name = "netSalary")
    @DecimalMin(value = "0.0", inclusive = false, message = "Net salary must be positive")
    private Double netSalary;

    @Column(name = "paymentDate")
    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status is required")
    private SalaryStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionId", referencedColumnName = "transactionId")
    private SalaryTransaction salaryTransaction;
    
 // Calculate net salary before persisting or updating the entity
    @PrePersist
    @PreUpdate
    public void calculateNetSalary() {
        if (this.grossSalary != null && this.deductions != null) {
            this.netSalary = this.grossSalary - this.deductions;
        }
    }

}