package com.login.authentication.service;
import java.util.List;
import java.util.Optional;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.ScheduleModel;
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

    public ResponseEntity<Optional<ScheduleModel>> getData(Optional<ScheduleModel> truks){
        if(truks.isEmpty() || truks == null) {
            throw new ApiRequestException("No se encuentra el usuario" );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(truks);
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
                ScheduleModel schRepo = repositorio.save(SheduleData);
                return ResponseEntity.status(HttpStatus.OK).body(SheduleData);
            }else {
                throw new ApiRequestExceptionValid("Guia ya existe");
            }
        }
    }
    public ResponseEntity<Optional<ScheduleModel>> getDataDoc(ScheduleModel truksDTO, BindingResult result){
        if(!truksDTO.getActividades().isEmpty()) {

            String id_sch = truksDTO.getActividades();
            Optional<ScheduleModel> camion = repositorio.findByIdSch(id_sch);

            if(camion == null || !camion.get().equals(truksDTO.getActividades())) {

                throw new ApiRequestExceptionValid("Guia no existe o los datos ingresados no son correctos");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(camion);
            }
        }else {
            throw new ApiRequestExceptionValid("Debe ingresar el Documento y tipo");
        }
    }
}
