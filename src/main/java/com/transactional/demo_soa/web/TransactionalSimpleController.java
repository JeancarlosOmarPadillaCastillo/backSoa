package com.transactional.demo_soa.web;

import com.transactional.demo_soa.domain.dto.Attendance.MasivAtten;
import com.transactional.demo_soa.domain.dto.TransactionalSimple.TransactionalSimpleResponseDto;
import com.transactional.demo_soa.repository.TransactionalSimpleRepository;
import com.transactional.demo_soa.service.TransactionalSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactionalDataSimple")
@RequiredArgsConstructor
public class TransactionalSimpleController {

    final TransactionalSimpleRepository transactionalSimpleRepository;
    final TransactionalSimpleService transactionalSimpleService;



    @GetMapping("/listData")
    public Flux<TransactionalSimpleResponseDto> getDataComplete() {
        return this.transactionalSimpleService.getDataFullComplete();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/savetd")
    public Mono<Void> saveNewData(@RequestBody MasivAtten dto) {
        return this.transactionalSimpleService.saveNewData(dto);

    }


}
