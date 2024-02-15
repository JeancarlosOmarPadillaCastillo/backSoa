package com.gestion.teenager.service;

import com.gestion.teenager.domain.dto.TeenagerRequestDto;
import com.gestion.teenager.domain.dto.TeenagerResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TeenagerService {


    Mono<TeenagerResponseDto> findById(Integer id);
    Flux<TeenagerResponseDto> findAll();
    Flux<TeenagerResponseDto> findAllActive();
    Flux<TeenagerResponseDto> findAllInactive();
    Mono<TeenagerResponseDto> create(TeenagerRequestDto request);
    Mono<TeenagerResponseDto> update(TeenagerRequestDto request, Integer id);
    Mono<TeenagerResponseDto> deleteLogical(Integer id);
    Mono<TeenagerResponseDto> reactiveLogical(Integer id);
    Mono<Void> delete(Integer id);
}
