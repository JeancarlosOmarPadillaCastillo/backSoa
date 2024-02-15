package com.gestion.teenager.domain.mapper;

import com.gestion.teenager.domain.dto.TeenagerRequestDto;
import com.gestion.teenager.domain.dto.TeenagerResponseDto;
import com.gestion.teenager.domain.model.Teenager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeenagerMapper {

    public static Teenager toModel(TeenagerRequestDto dto) {
        return new Teenager(
                dto.getName(),
                dto.getFather_last_name(),
                dto.getMother_last_name(),
                dto.getDocument_type(),
                dto.getDocument_number(),
                dto.getCellphone(),
                dto.getAddress(),
                dto.getActive()
        );
    }

    public static Teenager toModel(Integer id, TeenagerRequestDto dto) {
        return new Teenager(
                id,
                dto.getName(),
                dto.getFather_last_name(),
                dto.getMother_last_name(),
                dto.getDocument_type(),
                dto.getDocument_number(),
                dto.getCellphone(),
                dto.getAddress(),
                dto.getActive()
        );
    }

    public static TeenagerResponseDto toDto(Teenager model) {
        return new TeenagerResponseDto(
                model.getId(),
                model.getName(),
                model.getFather_last_name(),
                model.getMother_last_name(),
                model.getDocument_type(),
                model.getDocument_number(),
                model.getCellphone(),
                model.getAddress(),
                model.getActive()
        );
    }

}

