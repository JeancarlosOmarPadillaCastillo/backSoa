package com.gestion.activities.web;

import com.gestion.activities.domain.dto.ActivitiesRequestDto;
import com.gestion.activities.domain.dto.ActivitiesResponseDto;
import com.gestion.activities.repository.ActivitiesRepository;
import com.gestion.activities.service.ActivitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/ms-soa")
@RequiredArgsConstructor
public class ActivitiesController {

    //private final Logger logger = LoggerFactory.getLogger(OperationalUnitController.class);


    final ActivitiesService activitiesService;

    final ActivitiesRepository activitiesRepository;

    @GetMapping("{id}")
    public Mono<ActivitiesResponseDto> getDataActivitiesById(@PathVariable Integer id) {
        return this.activitiesService.findById(id);
    }

    @GetMapping("/listData")
    public Flux<ActivitiesResponseDto> getDataActivitiesComplete() {
        return this.activitiesService.findAll();
    }

    //      logger.info("Entrando en getDataFuncionaryComplete"); // Mensaje de registro
    //      Flux<OperationalUnitResponseDto> data = this.operationalUnitService.findAll();
    //      logger.info("Saliendo de getDataFuncionaryComplete"); // Mensaje de registro
    //      return data;


    @GetMapping("/listData/active")
    public Flux<ActivitiesResponseDto> getDataActivitiesActive() { return this.activitiesService.findAllActive();
    }

    @GetMapping("/listData/inactive")
    public Flux<ActivitiesResponseDto> getDataActivitiesInactive() { return this.activitiesService.findAllInactive();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Mono<ActivitiesResponseDto> saveNewDataActivities(@RequestBody ActivitiesRequestDto dto) {
        return this.activitiesService.saveNewActivities(dto);
    }

    @PutMapping("/update/{id}")
    public Mono<ActivitiesResponseDto> updateDataActivities(@RequestBody ActivitiesRequestDto dto, @PathVariable Integer id) {
        return this.activitiesService.updateActivities(dto, id);
    }

    @DeleteMapping("/deleteLogical/{id}")
    public Mono<ActivitiesResponseDto> deleteLogicalActivities(@PathVariable Integer id) {
        return this.activitiesService.deleteLogicalActivities(id);
    }

    @PutMapping(value="/reactiveLogical/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<ActivitiesResponseDto> reactiveLogicalActivities(@PathVariable Integer id) {
        return this.activitiesService.reactiveLogicalActivities(id);
    }

    @DeleteMapping("/dtotal/{id}")
    public Mono<Void> deleteTotalActivities(@PathVariable Integer id) {
        return this.activitiesService.deleteActivities(id);
    }

}
