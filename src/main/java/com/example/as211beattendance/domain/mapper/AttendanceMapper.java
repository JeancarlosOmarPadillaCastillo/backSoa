package com.example.as211beattendance.domain.mapper;

import com.example.as211beattendance.domain.dto.AttemdanceRequestDto;
import com.example.as211beattendance.domain.dto.AttendanceResponseDto;
import com.example.as211beattendance.domain.model.attendance;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceMapper {

    public static attendance toModel(AttemdanceRequestDto attemdanceRequestDto){
        return new attendance(
                attemdanceRequestDto.getId_activities(),
                attemdanceRequestDto.getId_teenager(),
                attemdanceRequestDto.getId_funcionary(),
                attemdanceRequestDto.getId_programs(),
                attemdanceRequestDto.getAttendance(),
                attemdanceRequestDto.getDate(),
                attemdanceRequestDto.getActive()
        );
    }

    public static attendance toModel(Integer id, AttemdanceRequestDto attemdanceRequestDto){
        return new attendance(
                id,
                attemdanceRequestDto.getId_activities(),
                attemdanceRequestDto.getId_teenager(),
                attemdanceRequestDto.getId_funcionary(),
                attemdanceRequestDto.getId_programs(),
                attemdanceRequestDto.getAttendance(),
                attemdanceRequestDto.getDate(),
                attemdanceRequestDto.getActive()

        );
    }

    public static AttendanceResponseDto toDto(attendance attendance){
        return new AttendanceResponseDto(
                attendance.getId_attendance(),
                attendance.getId_activities(),
                attendance.getId_teenager(),
                attendance.getId_funcionary(),
                attendance.getId_programs(),
                attendance.getAttendance(),
                attendance.getDate(),
                attendance.getActive()

        );
    }
}
