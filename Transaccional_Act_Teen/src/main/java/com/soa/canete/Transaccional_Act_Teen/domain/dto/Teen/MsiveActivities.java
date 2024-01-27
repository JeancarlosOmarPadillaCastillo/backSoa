package com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
public class MsiveActivities {
    private Integer id_activities;
    private List<TeenagerAssignDto> id_teenager;
    private LocalDate start_date;
    private String duration;
    private String notes;
    private String participation_status;
    private String active;

    @Getter
    @Setter
    public static class TeenagerAssignDto {
        private Integer id_teenager;
    }

}
