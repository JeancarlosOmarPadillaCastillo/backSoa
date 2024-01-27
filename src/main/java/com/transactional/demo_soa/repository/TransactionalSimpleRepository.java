package com.transactional.demo_soa.repository;

import com.transactional.demo_soa.domain.model.TransactionalSimple;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.Optional;

public interface TransactionalSimpleRepository extends ReactiveCrudRepository<TransactionalSimple, Integer> {
    @Query("SELECT * FROM historial_soa_teen WHERE id_historial=:id_historial AND id_teenager=:id_teenager")
    Optional<TransactionalSimple> findByIdHAndTeenId(int id_historial, int id_teenager);
}
