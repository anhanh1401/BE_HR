package com.cnpm.hr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Entity
@Table()
@NoArgsConstructor
@AllArgsConstructor
public class Employees implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column
    private String employeeName;

    @Column
    private String employeeEmail;

    @JsonIgnore
    private String password;

    @Column
    private String employeePhone;

    @Column
    private String employeeAddress;

}
