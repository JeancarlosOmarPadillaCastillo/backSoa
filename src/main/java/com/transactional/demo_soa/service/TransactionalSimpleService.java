package com.transactional.demo_soa.service;

import com.transactional.demo_soa.domain.dto.Attendance.MasivAtten;
import com.transactional.demo_soa.domain.dto.DataTransactionalSimpleHistorial;
import com.transactional.demo_soa.domain.dto.TransactionalSimple.TransactionalSimpleResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TransactionalSimpleService {

    Flux<TransactionalSimpleResponseDto> getDataFullComplete();

    Flux<DataTransactionalSimpleHistorial> findAll();

    Mono<Void> saveNewData(MasivAtten request);

}
