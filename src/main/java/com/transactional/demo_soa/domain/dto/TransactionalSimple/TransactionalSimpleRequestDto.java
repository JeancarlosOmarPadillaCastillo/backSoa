package com.transactional.demo_soa.domain.dto.TransactionalSimple;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class TransactionalSimpleRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8222253670338491507L;


    @Column
    private Integer id_attendance;

}
