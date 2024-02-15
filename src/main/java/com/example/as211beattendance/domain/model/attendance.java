package com.example.as211beattendance.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class attendance {

    @Id
    private Integer id_attendance;
    private Integer id_activities;
    private Integer id_teenager;
    private Integer id_funcionary;
    private Integer id_programs;
    private String attendance;
    private LocalDate date;
    private String active;


    public attendance(Integer id_activities, Integer id_teenager, Integer id_funcionary, Integer id_programs, String attendance, LocalDate date, String active) {
        this.id_activities = id_activities;
        this.id_teenager = id_teenager;
        this.id_funcionary = id_funcionary;
        this.id_programs = id_programs;
        this.attendance = attendance;
        this.date = date;
        this.active = active;
    }

}
