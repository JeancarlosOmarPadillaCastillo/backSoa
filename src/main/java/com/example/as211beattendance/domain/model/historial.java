package com.example.as211beattendance.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@Table(name = "historial_soa_teen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class historial {
    @Id
    private Integer id_historial;
    @Column
    private Integer id_attendance;

    public historial(Integer id_attendance) {
        this.id_attendance = id_attendance;
    }
}
