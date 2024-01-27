package com.soa.canete.Transaccional_Act_Teen.domain.dto;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.Activities.ActivitiesResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen.TeenResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.model.TransaccionalActTeen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTeenActivitiesTransaccional {

    private ActivitiesResponseDto activitiesResponseDto;
    private TeenResponseDto teenResponseDto;
    private TransaccionalActTeen transaccionalActTeen;

}
