package com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class MasivTeen {
    private List<ActivitiesAssignDto> id_activities;
    private Integer id_teenager;
    private LocalDate start_date;
    private String duration;
    private String notes;
    private String participation_status;
    private String active;

    @Getter
    @Setter
    public static class ActivitiesAssignDto {
        private Integer id_activities;
    }

}