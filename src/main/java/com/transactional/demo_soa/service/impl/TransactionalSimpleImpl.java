package com.transactional.demo_soa.service.impl;

import com.transactional.demo_soa.domain.dto.Attendance.MasivAtten;
import com.transactional.demo_soa.domain.dto.DataTransactionalSimpleHistorial;
import com.transactional.demo_soa.domain.dto.TransactionalSimple.TransactionalSimpleResponseDto;
import com.transactional.demo_soa.domain.mapper.TransactionalSimpleMapper;
import com.transactional.demo_soa.repository.TransactionalSimpleRepository;
import com.transactional.demo_soa.service.TransactionalSimpleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.transactional.demo_soa.domain.mapper.TransactionalSimpleMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalSimpleImpl implements TransactionalSimpleService {



    final TransactionalSimpleRepository transactionalSimpleRepository;


    /*@Override
    public Flux<DataTransactionalSimpleHistorial> getDataFullComplete() {
        Flux<TransactionalSimple> family = transactionalSimpleRepository.findAll()
                .sort(Comparator.comparing(TransactionalSimple::getId_historial).reversed());
        return family.flatMap((dataFamily) -> {
            Mono<FuncionaryResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/funcionaryData/" + dataFamily.getId_funcionary())
                    .retrieve()
                    .bodyToMono(FuncionaryResponseDto.class);
            Mono<ActivitiesResponseDto> activitiesResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8093/ms-soa/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            Mono<ProgramsResponseDto> programsResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8098/v1/programs/find/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(ProgramsResponseDto.class);
            Mono<AttendanceResponseDto> attendanceResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8085/v1/attendance/" + dataFamily.getId_attendance())
                    .retrieve()
                    .bodyToMono(AttendanceResponseDto.class);
            return Mono.zip(funcionaryResponseDtoMono, activitiesResponseDtoMono, teenResponseDtoMono, programsResponseDtoMono, attendanceResponseDtoMono).map(dataGeneral -> {
                FuncionaryResponseDto funcionaryResponseDtoData = dataGeneral.getT1();
                ActivitiesResponseDto activitiesResponseDtoData = dataGeneral.getT2();
                TeenResponseDto teenResponseDtoData = dataGeneral.getT3();
                ProgramsResponseDto programsResponseDto = dataGeneral.getT4();
                AttendanceResponseDto attendanceResponseDto = dataGeneral.getT5();
                DataTransactionalSimpleHistorial dataTransactionalSimpleHistorial = new DataTransactionalSimpleHistorial();
                dataTransactionalSimpleHistorial.setTransactionalSimple(dataFamily);
                dataTransactionalSimpleHistorial.setFuncionaryResponseDto(funcionaryResponseDtoData);
                dataTransactionalSimpleHistorial.setActivitiesResponseDto(activitiesResponseDtoData);
                dataTransactionalSimpleHistorial.setTeenResponseDto(teenResponseDtoData);
                dataTransactionalSimpleHistorial.setProgramsResponseDto(programsResponseDto);
                dataTransactionalSimpleHistorial.setAttendanceResponseDto(attendanceResponseDto);
                return dataTransactionalSimpleHistorial;
            });
        });
    }*/

    @Override
    public Flux<TransactionalSimpleResponseDto> getDataFullComplete() {
        return this.transactionalSimpleRepository.findAll()
                .map(TransactionalSimpleMapper::toDto);
    }

    @Override
    public Flux<DataTransactionalSimpleHistorial> findAll() {
        return null;
    }

    @Override
    public Mono<Void> saveNewData(MasivAtten request) {
        Flux<Void> saveOperations = Flux.fromIterable(request.getId_attendance())
                .flatMap(res -> {
                    TransactionalSimpleResponseDto trans = TransactionalSimpleResponseDto.builder()
                            .id_attendance(res.getId_attendance())
                            .build();

                    return transactionalSimpleRepository.save(toModel(trans)).then();
                });

        return saveOperations.then();
    }
}
