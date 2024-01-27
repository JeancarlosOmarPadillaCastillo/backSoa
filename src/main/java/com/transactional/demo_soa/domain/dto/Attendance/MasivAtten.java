package com.transactional.demo_soa.domain.dto.Attendance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;

import java.util.List;

@Getter
@Setter
@ToString

public class MasivAtten {
    private Integer id_activities;
    private Integer id_teenager;
    private Integer id_funcionary;
    private Integer id_programs;
    private List<AttendanceAssignDto> id_attendance;

    @Getter
    @Setter
    public static class AttendanceAssignDto {
        private Integer id_attendance;
    }
}
