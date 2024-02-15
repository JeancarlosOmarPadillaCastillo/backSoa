package com.gestion.teenager.web;

import com.gestion.teenager.domain.dto.TeenagerRequestDto;
import com.gestion.teenager.domain.dto.TeenagerResponseDto;
import com.gestion.teenager.repository.TeenagerRepository;
import com.gestion.teenager.service.TeenagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/teenData")
@RequiredArgsConstructor
public class TeenagerController {

    //private final Logger logger = LoggerFactory.getLogger(OperationalUnitController.class);


    final TeenagerService teenagerService;

    final TeenagerRepository teenagerRepository;

    @GetMapping("{id_teen}")
    public Mono<TeenagerResponseDto> getDataTeenagerById(@PathVariable Integer id_teen) {
        return this.teenagerService.findById(id_teen);
    }

    @GetMapping("/listData")
    public Flux<TeenagerResponseDto> getDataTeenagerComplete() {
        return this.teenagerService.findAll();
    }

    //      logger.info("Entrando en getDataFuncionaryComplete"); // Mensaje de registro
    //      Flux<OperationalUnitResponseDto> data = this.operationalUnitService.findAll();
    //      logger.info("Saliendo de getDataFuncionaryComplete"); // Mensaje de registro
    //      return data;


    @GetMapping("/listData/active")
    public Flux<TeenagerResponseDto> getDataTeenagerActive() { return this.teenagerService.findAllActive();
    }

    @GetMapping("/listData/inactive")
    public Flux<TeenagerResponseDto> getDataTeenagerInactive() { return this.teenagerService.findAllInactive();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<TeenagerResponseDto> saveNewDataTeenager(@RequestBody TeenagerRequestDto dto) {
        return this.teenagerService.create(dto);
    }

    @PutMapping("/{id_teen}")
    public Mono<TeenagerResponseDto> updateDataTeenager(@RequestBody TeenagerRequestDto dto, @PathVariable Integer id_teen) {
        return this.teenagerService.update(dto, id_teen);
    }

    @PatchMapping("/deleteLogical/{id_teen}")
    public Mono<TeenagerResponseDto> deleteLogicalTeenager(@PathVariable Integer id_teen) {
        return this.teenagerService.deleteLogical(id_teen);
    }

    @PatchMapping("/reactiveLogical/{id_teen}")
    public Mono<TeenagerResponseDto> reactiveLogicalTeenager(@PathVariable Integer id_teen) {
        return this.teenagerService.reactiveLogical(id_teen);
    }

    @DeleteMapping("/{id_teen}")
    public Mono<Void> deleteTotalTeenager(@PathVariable Integer id_teen) {
        return this.teenagerService.delete(id_teen);
    }

}
