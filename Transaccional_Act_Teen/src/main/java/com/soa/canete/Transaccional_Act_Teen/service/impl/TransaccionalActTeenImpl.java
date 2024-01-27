package com.soa.canete.Transaccional_Act_Teen.service.impl;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.DataTeenActivitiesTransaccional;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Activities.ActivitiesResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Report.TransaccionalReport;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen.MasivTeen;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen.MsiveActivities;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.mapper.TransaccionalActTeenMapper;
import com.soa.canete.Transaccional_Act_Teen.domain.model.TransaccionalActTeen;
import com.soa.canete.Transaccional_Act_Teen.service.TransaccionalActTeenService;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen.TeenResponseDto;
import com.soa.canete.Transaccional_Act_Teen.exception.ResourceNotFoundException;
import com.soa.canete.Transaccional_Act_Teen.repository.TransaccionalActTeenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.soa.canete.Transaccional_Act_Teen.domain.mapper.TransaccionalActTeenMapper.toModel;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransaccionalActTeenImpl implements TransaccionalActTeenService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    final TransaccionalActTeenRepository transaccionalActTeenRepository;

    @Override
    public Mono<DataTeenActivitiesTransaccional> findById(Integer id_act_teen) {
        Mono<TransaccionalActTeen> family = transaccionalActTeenRepository.findById(id_act_teen);
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8090/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenActivitiesTransaccional = new DataTeenActivitiesTransaccional();
                dataTeenActivitiesTransaccional.setTransaccionalActTeen(dataFamily);
                dataTeenActivitiesTransaccional.setTeenResponseDto(adolescentResponseDtoData);
                dataTeenActivitiesTransaccional.setActivitiesResponseDto(legalGuardianResponseDtoData);
                return dataTeenActivitiesTransaccional;
            });
        });
    }

    @Override
    public Flux<TransaccionalReport> findAll() {
        Flux<TransaccionalActTeen> family = transaccionalActTeenRepository.findAll().sort(Comparator.comparing(TransaccionalActTeen::getId_act_teen).reversed());
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8090/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                TransaccionalReport transaccionalReport = new TransaccionalReport();
                transaccionalReport.setId(dataFamily.getId_act_teen());
                transaccionalReport.setId_teen(dataFamily.getId_teenager());
                transaccionalReport.setId_activities(dataFamily.getId_activities());
                transaccionalReport.setDni_teen(adolescentResponseDtoData.getDocument_number());
                transaccionalReport.setName_teen(adolescentResponseDtoData.getName());
                transaccionalReport.setName_activities(legalGuardianResponseDtoData.getName());
                transaccionalReport.setDuration(dataFamily.getDuration());
                transaccionalReport.setStart_date(dataFamily.getStart_date());
                transaccionalReport.setParticipation_status(dataFamily.getParticipation_status());
                return transaccionalReport;
            });
        });
    }

    @Override
    public Flux<DataTeenActivitiesTransaccional> findAllDataActive() {
        Flux<TransaccionalActTeen> family = transaccionalActTeenRepository.findAll()
                .sort(Comparator.comparing(TransaccionalActTeen::getId_act_teen).reversed())
                .filter((active) -> active.getActive().equals("A"));
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8090/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenFuncionaryTransaccional = new DataTeenActivitiesTransaccional();
                dataTeenFuncionaryTransaccional.setTransaccionalActTeen(dataFamily);
                dataTeenFuncionaryTransaccional.setTeenResponseDto(adolescentResponseDtoData);
                dataTeenFuncionaryTransaccional.setActivitiesResponseDto(legalGuardianResponseDtoData);
                return dataTeenFuncionaryTransaccional;
            });
        });
    }

    @Override
    public Flux<DataTeenActivitiesTransaccional> findAllDataInactive() {
        Flux<TransaccionalActTeen> family = transaccionalActTeenRepository.findAll()
                .sort(Comparator.comparing(TransaccionalActTeen::getId_act_teen).reversed())
                .filter((active) -> active.getActive().equals("I"));;
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8090/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenActivitiesTransaccional = new DataTeenActivitiesTransaccional();
                dataTeenActivitiesTransaccional.setTransaccionalActTeen(dataFamily);
                dataTeenActivitiesTransaccional.setTeenResponseDto(adolescentResponseDtoData);
                dataTeenActivitiesTransaccional.setActivitiesResponseDto(legalGuardianResponseDtoData);
                return dataTeenActivitiesTransaccional;
            });
        });
    }

    @Override
    public Mono<Void> saveNewDataTransaccional(MasivTeen request) {
        Flux<Void> saveOperations = Flux.fromIterable(request.getId_activities())
                .flatMap(res -> {
                    TransaccionalAllocationResponseDto trans = TransaccionalAllocationResponseDto.builder()
                            .id_teenager(request.getId_teenager())
                            .start_date(request.getStart_date())
                            .duration(request.getDuration())
                            .notes(request.getNotes())
                            .id_activities(res.getId_activities())
                            .participation_status(request.getParticipation_status())
                            .build();

                    return transaccionalActTeenRepository.save(toModel(trans)).then();
                });

        return saveOperations.then();
    }

    @Override
    public Mono<Void> saveTeenMasive(MsiveActivities request) {
        Flux<Void> saveOperations = Flux.fromIterable(request.getId_teenager())
                .flatMap(res -> {
                    TransaccionalAllocationResponseDto trans = TransaccionalAllocationResponseDto.builder()
                            .id_teenager(res.getId_teenager())
                            .start_date(request.getStart_date())
                            .duration(request.getDuration())
                            .notes(request.getNotes())
                            .id_activities(request.getId_activities())
                            .participation_status(request.getParticipation_status())
                            .build();

                    return transaccionalActTeenRepository.save(toModel(trans)).then();
                });

        return saveOperations.then();
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> updateDataTransaction(TransaccionalAllocationRequestDto request, Integer id_act_teen) {
        return this.transaccionalActTeenRepository.findById(id_act_teen)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El identificador: " + id_act_teen + " no fue encontrado")))
                .flatMap((dataAsignationOrTransaction) -> this.transaccionalActTeenRepository.save(TransaccionalActTeenMapper.toModel(request, dataAsignationOrTransaction.getId_act_teen())))
                .map(TransaccionalActTeenMapper::toDto);
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> deleteLogicalTransaction(Integer id_act_teen) {
        return this.transaccionalActTeenRepository.findById(id_act_teen)
                .map((deleteData) -> {
                    deleteData.setActive("I");
                    return deleteData;
                })
                .flatMap(transaccionalActTeenRepository::save)
                .map(TransaccionalActTeenMapper::toDto);
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> reactiveLogicalTransaction(Integer id_act_teen) {
        return this.transaccionalActTeenRepository.findById(id_act_teen)
                .map((deleteData) -> {
                    deleteData.setActive("A");
                    return deleteData;
                })
                .flatMap(transaccionalActTeenRepository::save)
                .map(TransaccionalActTeenMapper::toDto);
    }

    public Mono<ResponseEntity<Resource>> exportAsignationReport(String dniteen) {
        Flux<TransaccionalReport> asignationReportDtoFlux = findAll().filter(programs ->
                programs.getDni_teen().equals(dniteen)
        );

        return asignationReportDtoFlux.collectList()
                .flatMap(asignationReportDtos -> {
                    try {
                        final HashMap<String, Object> parameters = new HashMap<>();
                        parameters.put("dataset", new JRBeanCollectionDataSource(asignationReportDtos));
                        return Mono.just(jasperReport("Transaccional.jasper", "asignationprograms", parameters));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Mono.error(e);
                    }
                })
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }



    public ResponseEntity<Resource> jasperReport(String reportPath, String outputFileName, HashMap<String, Object> parameters) throws JRException {

        try {
            final File file = ResourceUtils.getFile("classpath:"+reportPath);
            final JasperReport report = (JasperReport) JRLoader.loadObject(file);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
            StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(stringBuilder.append(outputFileName).toString())
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(contentDisposition);
            return ResponseEntity.ok().contentLength((long) reporte.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .headers(headers).body(new ByteArrayResource(reporte));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
