package com.transactional.demo_soa.domain.dto;

import com.transactional.demo_soa.domain.dto.Activities.ActivitiesResponseDto;
import com.transactional.demo_soa.domain.dto.Attendance.AttendanceResponseDto;
import com.transactional.demo_soa.domain.dto.Funcionary.FuncionaryResponseDto;
import com.transactional.demo_soa.domain.dto.Pograms.ProgramsResponseDto;
import com.transactional.demo_soa.domain.dto.Teen.TeenResponseDto;
import com.transactional.demo_soa.domain.dto.TransactionalSimple.TransactionalSimpleResponseDto;
import com.transactional.demo_soa.domain.model.TransactionalSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTransactionalSimpleHistorial {

    private ActivitiesResponseDto activitiesResponseDto;
    private FuncionaryResponseDto funcionaryResponseDto;
    private TeenResponseDto teenResponseDto;
    private TransactionalSimple transactionalSimple;
    private ProgramsResponseDto programsResponseDto;
    private AttendanceResponseDto attendanceResponseDto;

}
