package com.example.as211beattendance.service.impl;

import com.example.as211beattendance.domain.dto.AttemdanceRequestDto;
import com.example.as211beattendance.domain.dto.AttendanceResponseDto;
import com.example.as211beattendance.domain.dto.attendanceViewDto;
import com.example.as211beattendance.domain.mapper.AttendanceMapper;
import com.example.as211beattendance.domain.model.Teenager;
import com.example.as211beattendance.domain.model.attendance;
import com.example.as211beattendance.domain.model.historial;
import com.example.as211beattendance.exception.ResourceNotFoundException;
import com.example.as211beattendance.repository.AttendanceRepository;
import com.example.as211beattendance.repository.historialRepository;
import com.example.as211beattendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.as211beattendance.domain.mapper.AttendanceMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    final historialRepository historialRepositorys;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Override
    public Mono<AttendanceResponseDto> findById(Integer id) {
        return this.attendanceRepository.findById(id)
                .map(AttendanceMapper::toDto);
    }

    @Override
    public Flux<AttendanceResponseDto> findAll() {
        return this.attendanceRepository.findAll()
                .map(AttendanceMapper::toDto);
    }

    @Override
    public Mono<AttendanceResponseDto> create(AttemdanceRequestDto request) {

        // Guardamos el registro en la primera tabla
        Mono<attendance> attendanceMono = this.attendanceRepository.save(toModel(request));

        // Guardamos el registro en la segunda tabla
        Mono<attendance> historialMono = attendanceMono
                .flatMap(attendance -> {
                    historial historial = new historial();
                    historial.setId_attendance(attendance.getId_attendance());
                    System.out.println(historial);
                    return this.historialRepositorys.save(historial).map(ignore-> attendance);
                });

        // Devolvemos el resultado
        return historialMono.map(historial -> AttendanceMapper.toDto(historial));
    }




    @Override
    public Mono<AttendanceResponseDto> update(AttemdanceRequestDto request, Integer id) {
        return this.attendanceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Asistencia no encontrada")))
                .flatMap(attendance -> this.attendanceRepository.save(toModel(attendance.getId_attendance(),request)))
                .map(AttendanceMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return this.attendanceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Asistencia no encontrada")))
                .flatMap(attendance -> {
                    attendance.setActive("I");
                    return this.attendanceRepository.save(attendance);
                })
                .doOnSuccess(unused -> log.info("se elimino el ID " + id))
                .then();
    }

    @Override
    public Flux<attendanceViewDto> viewAttendance(Integer id) {
        return this.attendanceRepository.findIdActividad(id)
                .flatMap(res -> {
                    Mono<Teenager> teenInfo = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/api/teenData/" + res.getId_teenager())
                            .retrieve()
                            .bodyToMono(Teenager.class);
                    return teenInfo.map(data ->{
                        attendanceViewDto view = new attendanceViewDto();
                        view.setId_attendance(res.getId_attendance());
                        view.setId_activities(res.getId_activities());
                        view.setId_teenager(res.getId_teenager());
                        view.setName(data.getName()+ " " + data.getFather_last_name() + " " + data.getMother_last_name());
                        view.setAttendance(res.getAttendance());
                        view.setActive(res.getActive());
                        view.setDate(res.getDate());
                        return view;
                    });
                });
    }
}
