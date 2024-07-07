package com.bmi.internship.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeedib")
public class Employee extends BaseEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_employee")
	@SequenceGenerator(sequenceName = "sq_employee", allocationSize = 1, name = "sq_employee")
	@Column(name = "id")
	public Long id;

    public String name;
    public String employeeid;
    public String function;
    public String unit;
}
