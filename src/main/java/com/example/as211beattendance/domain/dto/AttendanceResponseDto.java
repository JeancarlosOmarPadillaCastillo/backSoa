package com.example.as211beattendance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AttendanceResponseDto {
    private static final long serialVersionUID = 8222253670338491507L;
    private Integer id_attendance;
    private Integer id_activities;
    private Integer id_teenager;
    private Integer id_funcionary;
    private Integer id_programs;
    private String attendance;
    private LocalDate date;
    private String active;
}
