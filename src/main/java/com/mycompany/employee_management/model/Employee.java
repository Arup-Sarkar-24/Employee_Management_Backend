package com.mycompany.employee_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    @Temporal(TemporalType.TIMESTAMP) // Specify the temporal type of the date field
    @Column(name = "joining_date", nullable = false, updatable = false) // Column mapping
    private Date joiningDate;

    @PrePersist // Executed before entity is persisted to the database
    protected void onCreate() {
        joiningDate = new Date(); // Set the joining date to the current date and time
    }
}
