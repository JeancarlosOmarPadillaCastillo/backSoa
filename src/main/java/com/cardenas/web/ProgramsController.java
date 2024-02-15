package com.cardenas.web;

import com.cardenas.domain.dto.ProgramsRequestDto;
import com.cardenas.domain.dto.ProgramsResponseDto;
import com.cardenas.service.ProgramsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@RequestMapping("/v1/programs")
@RequiredArgsConstructor
public class ProgramsController {

    private final ProgramsService programsService;

    @GetMapping(value="/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<ProgramsResponseDto> findAll() {
        return this.programsService.findAll();
    }

    @GetMapping(value="/listI", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<ProgramsResponseDto> findInactive() {
        return this.programsService.findInactive();
    }

    @GetMapping(value="/listA", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<ProgramsResponseDto> findActive() {
        return this.programsService.findActive();
    }

    @GetMapping(value = "/find/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProgramsResponseDto> findById(@PathVariable Integer id) {
        return this.programsService.findById(id);
    }

    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProgramsResponseDto> update(@PathVariable Integer id, @RequestBody ProgramsRequestDto dto) {
        return this.programsService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProgramsResponseDto> create(@RequestBody ProgramsRequestDto dto) {
        return this.programsService.create(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return this.programsService.delete(id);
    }

    @PutMapping(value = "/restore/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ProgramsResponseDto> restore(@PathVariable Integer id) {
        return this.programsService.restore(id);
    }

}


