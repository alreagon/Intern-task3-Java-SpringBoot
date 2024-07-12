package com.bmi.internship.example.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Version
    private Long version;
    private Timestamp created;
    private Timestamp updated;
    private Timestamp deleted;

}
