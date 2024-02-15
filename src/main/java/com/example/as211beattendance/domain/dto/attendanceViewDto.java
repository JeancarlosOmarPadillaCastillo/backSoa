package com.example.as211beattendance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class attendanceViewDto {

    private static final long serialVersionUID = 8222253670338491507L;
    private Integer id_attendance;
    private Integer id_activities;
    private Integer id_teenager;
    private String name;
    private String attendance;
    private String active;
    private LocalDate date;
}
