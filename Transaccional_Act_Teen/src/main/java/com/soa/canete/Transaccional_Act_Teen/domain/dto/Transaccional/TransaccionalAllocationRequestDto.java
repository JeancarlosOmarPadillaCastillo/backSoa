package com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class TransaccionalAllocationRequestDto{
    private Integer id_act_teen;
    private Integer id_activities;
    private Integer id_teenager;
    private LocalDate start_date;
    private String duration;
    private String notes;
    private String participation_status;
    private String active;
}
