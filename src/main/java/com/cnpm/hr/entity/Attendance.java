package com.cnpm.hr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int attendanceId;

    @Column
    private int employeeId;

    @Column
    private LocalDate attendanceDate;

    @Column
    private String attendanceStatus;
}
