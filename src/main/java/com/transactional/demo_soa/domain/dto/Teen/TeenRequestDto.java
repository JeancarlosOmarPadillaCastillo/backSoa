package com.transactional.demo_soa.domain.dto.Teen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TeenRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    @Column
    private String name;
    @Column
    private String father_last_name;
    @Column
    private String mother_last_name;
    @Column
    private String document_type;
    @Column
    private String document_number;
    @Column
    private String cellphone;
    @Column
    private String address;
}
