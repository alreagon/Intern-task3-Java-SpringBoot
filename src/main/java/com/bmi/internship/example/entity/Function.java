// File: src/main/java/com/bmi/internship/example/entity/Function.java
package com.bmi.internship.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "function")
public class Function extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "function", nullable = false)
    private String function;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    public Function(String id, String function) {
        this.id = id;
        this.function = function;
    }

    // public Function(String id, String function, Unit unit) {
    //     this.id = id;
    //     this.function = function;
    //     this.unit = unit;
    // }
}
