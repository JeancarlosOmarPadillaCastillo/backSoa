package com.soa.canete.Transaccional_Act_Teen.domain.dto.Report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionalReport {
    private Integer id;
    private Integer id_activities;
    private Integer id_teen;
    private String dni_teen;
    private String name_teen;
    private  String name_activities;
    private String duration;
    private LocalDate start_date;
    private String participation_status;
}
