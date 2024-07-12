package com.bmi.internship.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unit")
public class Unit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_unit")
    @SequenceGenerator(sequenceName = "sq_unit", allocationSize = 1, name = "sq_unit")
    @Column(name = "id")
    private Long id;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "unit", nullable = false)
    private String unit;

    public Unit(String abbreviation, String unit) {
        this.abbreviation = abbreviation;
        this.unit = unit;
    }
}
