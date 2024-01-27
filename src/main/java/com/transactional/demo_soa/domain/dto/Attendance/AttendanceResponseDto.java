package com.transactional.demo_soa.domain.dto.Attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AttendanceResponseDto implements Serializable{


    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    @Id
    private Integer id_attendance;

    @Column
    private Integer id_activities;

    @Column
    private Integer id_adolescente;

    @Column
    private String attendance;


    @Column
    private String active;

    @Column
    private LocalDate date;
}
