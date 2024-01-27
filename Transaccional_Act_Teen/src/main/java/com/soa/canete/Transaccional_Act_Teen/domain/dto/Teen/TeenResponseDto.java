package com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class TeenResponseDto  implements Serializable {
    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    @Id
    private Integer id_teenager;
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
