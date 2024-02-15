package com.gestion.teenager.service.impl;

import com.gestion.teenager.domain.dto.TeenagerRequestDto;
import com.gestion.teenager.domain.dto.TeenagerResponseDto;
import com.gestion.teenager.domain.mapper.TeenagerMapper;
import com.gestion.teenager.domain.model.Teenager;
import com.gestion.teenager.repository.TeenagerRepository;
import com.gestion.teenager.service.TeenagerService;
import com.gestion.teenager.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Objects;

import static com.gestion.teenager.domain.mapper.TeenagerMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeenagerImpl implements TeenagerService {

    private final TeenagerRepository teenagerRepository;

    @Override
    public Mono<TeenagerResponseDto> findById(Integer id) {
        return this.teenagerRepository.findById(id)
                .map(TeenagerMapper::toDto);
    }

    @Override
    public Flux<TeenagerResponseDto> findAll() {
        return this.teenagerRepository.findAll()
                .sort(Comparator.comparing(Teenager::getId).reversed())
                .map(TeenagerMapper::toDto);
    }

    @Override
    public Flux<TeenagerResponseDto> findAllActive() {
        return this.teenagerRepository.findAll()
                .sort(Comparator.comparing(Teenager::getId).reversed())
                .filter(active -> Objects.equals(active.getActive(), "A"))
                .map(TeenagerMapper::toDto);
    }

    @Override
    public Flux<TeenagerResponseDto> findAllInactive() {
        return this.teenagerRepository.findAll()
                .sort(Comparator.comparing(Teenager::getId).reversed())
                .filter(active -> Objects.equals(active.getActive(), "I"))
                .map(TeenagerMapper::toDto);
    }

    @Override
    public Mono<TeenagerResponseDto> create(TeenagerRequestDto request) {
        return this.teenagerRepository.save(toModel(request))
                .map(TeenagerMapper::toDto);
    }

    @Override
    public Mono<TeenagerResponseDto> update(TeenagerRequestDto request, Integer id) {
        return this.teenagerRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El id : " + id + " no fue encontrado")))
                .flatMap(programs -> this.teenagerRepository.save(toModel(programs.getId(), request)))
                .map(TeenagerMapper::toDto);
    }

    @Override
    public Mono<TeenagerResponseDto> deleteLogical(Integer id) {
        return null;
    }

    @Override
    public Mono<TeenagerResponseDto> reactiveLogical(Integer id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return null;
    }

}
