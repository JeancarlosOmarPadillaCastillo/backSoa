package com.cardenas.domain.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Table
@Getter
@Setter
@NoArgsConstructor
public class Programs {

    @Id
    private Integer id_program;
    private String name;
    private String type;
    private String beneficiary;
    private String responsible;
    private String description;
    private String duration;
    private String condition;
    //@Column("created_date")
    //@CreatedDate
   // private LocalDateTime createdDate = LocalDateTime.now(ZoneId.of("America/Lima"));


    public Programs(String name, String type, String beneficiary, String responsible, String description, String duration, String condition) {
        this.name = name;
        this.type = type;
        this.beneficiary = beneficiary;
        this.responsible = responsible;
        this.description = description;
        this.duration = duration;
        this.condition = condition;
    }

    public Programs(Integer id_program, String name, String type, String beneficiary, String responsible, String description, String condition,String duration) {
        this.id_program = id_program;
        this.name = name;
        this.type = type;
        this.beneficiary = beneficiary;
        this.responsible = responsible;
        this.description = description;
        this.condition = condition;
        this.duration = duration;
    }
}
