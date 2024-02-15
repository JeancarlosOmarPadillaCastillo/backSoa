package com.cardenas.service;

import com.cardenas.domain.dto.ProgramsRequestDto;
import com.cardenas.domain.dto.ProgramsResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProgramsService {

    Mono<ProgramsResponseDto> findById(Integer id);

    Flux<ProgramsResponseDto> findAll();

    Flux<ProgramsResponseDto> findInactive();

    Flux<ProgramsResponseDto> findActive();

    Mono<ProgramsResponseDto> create(ProgramsRequestDto request);

    Mono<ProgramsResponseDto> update(Integer id, ProgramsRequestDto request);

    Mono<Void> delete(Integer id);

    Mono<ProgramsResponseDto> restore(Integer id);

}
