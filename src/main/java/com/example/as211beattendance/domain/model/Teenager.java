package com.example.as211beattendance.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "teenager")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teenager {

    @Id
    private Integer id;
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
    @Column
    private String active;

    public Teenager(String name, String father_last_name, String mother_last_name, String document_type, String document_number, String cellphone, String address, String active) {
        this.name = name;
        this.father_last_name = father_last_name;
        this.mother_last_name = mother_last_name;
        this.document_type = document_type;
        this.document_number = document_number;
        this.cellphone = cellphone;
        this.address = address;
        this.active = active;
    }
}
