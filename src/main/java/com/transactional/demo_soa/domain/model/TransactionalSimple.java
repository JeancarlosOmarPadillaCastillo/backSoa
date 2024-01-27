package com.transactional.demo_soa.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "historial_soa_teen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionalSimple {

    @Id
    private Integer id_historial;

    @Column
    private Integer id_attendance;

    public TransactionalSimple (Integer id_attendance) {

        this.id_attendance = id_attendance;
    }

}
