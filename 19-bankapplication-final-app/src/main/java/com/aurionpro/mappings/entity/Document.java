package com.aurionpro.mappings.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="url")
    private String url;

    @Column(name="type")
    private String type;	//  "ID_PROOF", "ADDRESS_PROOF"

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer; 	//  "PENDING", "APPROVED", "REJECTED"
}
