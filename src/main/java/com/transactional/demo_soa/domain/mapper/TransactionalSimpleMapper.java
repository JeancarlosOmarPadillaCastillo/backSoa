package com.transactional.demo_soa.domain.mapper;

import com.transactional.demo_soa.domain.dto.TransactionalSimple.TransactionalSimpleRequestDto;
import com.transactional.demo_soa.domain.dto.TransactionalSimple.TransactionalSimpleResponseDto;
import com.transactional.demo_soa.domain.model.TransactionalSimple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionalSimpleMapper {

    public static TransactionalSimple toModel(TransactionalSimpleResponseDto dto) {
        return new TransactionalSimple(
                dto.getId_attendance()
        );
    }

    public static TransactionalSimple toModel(TransactionalSimpleRequestDto dto, Integer id_historial) {
        return new TransactionalSimple(
                id_historial,
                dto.getId_attendance()
        );
    }

    public static TransactionalSimpleResponseDto toDto(TransactionalSimple model) {
        return new TransactionalSimpleResponseDto(
                model.getId_historial(),
                model.getId_attendance()
        );
    }

}
