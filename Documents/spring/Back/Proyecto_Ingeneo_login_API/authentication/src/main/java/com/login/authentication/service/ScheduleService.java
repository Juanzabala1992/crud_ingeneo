package com.login.authentication.service;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.ActividadesExtrasModel;
import com.login.authentication.model.ActividadesModel;
import com.login.authentication.model.ScheduleModel;
import com.login.authentication.repository.ActivitiesExtrasRepository;
import com.login.authentication.repository.ActivitiesRepository;
import com.login.authentication.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repositorio;

    @Autowired
    private ActivitiesRepository activitiesRepository;

    @Autowired
    private ActivitiesExtrasRepository activitiesExtrasRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<List<ScheduleModel>> getAllData(List<ScheduleModel> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay usuarios en la base");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }

    public ResponseEntity<List<ScheduleModel>> getByUserId(List<ScheduleModel> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay datos para el usuario");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }

    public ResponseEntity<Optional<ScheduleModel>> getData(Optional<ScheduleModel> sch){
        if(sch.isEmpty() || sch == null) {
            throw new ApiRequestException("No se encuentra el dashboard" );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(sch);
        }
    }


    public ResponseEntity<ScheduleModel> setData(ScheduleModel SheduleData, BindingResult result){
        if(result.hasErrors()) {
            if(!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            }else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        }else {
            String id_sch = SheduleData.getIdSch();
            Optional<ScheduleModel> data = repositorio.findByIdSch(id_sch);
            if(data.isEmpty()) {

                boolean filter = repositorio.findBetweenDates(SheduleData.getFecha_inicio(),
                        SheduleData.getFecha_fin(), SheduleData.getIdUser());
                if(!filter){
                    ScheduleModel schRepo = repositorio.save(SheduleData);


                    List<ActividadesModel> actividades = SheduleData.getActividades();
                    for (ActividadesModel actividad : actividades) {
                        actividad.setScheduleModel(schRepo);
                        activitiesRepository.save(actividad);
                    }

                    List<ActividadesExtrasModel> actividadesExt = SheduleData.getActividades_extras();
                    for (ActividadesExtrasModel actividad : actividadesExt) {
                        actividad.setScheduleModel(schRepo);
                        activitiesExtrasRepository.save(actividad);
                    }


                    return ResponseEntity.status(HttpStatus.OK).body(SheduleData);
                }else{
                    throw new ApiRequestExceptionValid("Ya existe un reporte para estas fechas");
                }
            }else {
                throw new ApiRequestExceptionValid("Horario ya existe");
            }
        }
    }
}
