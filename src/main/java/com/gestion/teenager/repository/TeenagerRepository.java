package com.gestion.teenager.repository;

import com.gestion.teenager.domain.model.Teenager;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeenagerRepository extends ReactiveCrudRepository<Teenager, Integer> {
}
