package com.cardenas.repository;


import com.cardenas.domain.model.Programs;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramsRepository extends ReactiveCrudRepository<Programs, Integer> {
}
