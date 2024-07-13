package com.bmi.internship.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_employee")
    @SequenceGenerator(sequenceName = "sq_employee", allocationSize = 1, name = "sq_employee")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "employeeid", nullable = false)
    private String employeeid;

    @ManyToOne
    @JoinColumn(name = "titel_pekerjaan", nullable = false)
    private Function titelPekerjaan;
}
