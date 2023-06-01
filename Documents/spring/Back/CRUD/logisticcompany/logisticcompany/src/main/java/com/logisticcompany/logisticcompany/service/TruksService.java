package com.logisticcompany.logisticcompany.service;
import java.util.List;
import java.util.Optional;

import com.logisticcompany.logisticcompany.exceptions.ApiRequestException;
import com.logisticcompany.logisticcompany.exceptions.ApiRequestExceptionValid;
import com.logisticcompany.logisticcompany.model.TruksModel;
import com.logisticcompany.logisticcompany.repository.TruksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class TruksService {

    @Autowired
    private TruksRepository repositorio;


    public ResponseEntity<List<TruksModel>> getAllData(List<TruksModel> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay usuarios en la base");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }
    public ResponseEntity<Optional<TruksModel>> getData(Optional<TruksModel> truks){
        if(truks.isEmpty() || truks == null) {
            throw new ApiRequestException("No se encuentra el usuario" );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(truks);
        }
    }


    public ResponseEntity<TruksModel> setData(TruksModel truksData, BindingResult result){
        if(result.hasErrors()) {
            if(!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            }else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        }else {
            String numero_guia = truksData.getGuide();
            Optional<TruksModel> truk = repositorio.findByGuide(numero_guia);
            if(truk == null) {
                TruksModel trukRepo = repositorio.save(truksData);
                return ResponseEntity.status(HttpStatus.OK).body(truksData);
            }else {
                throw new ApiRequestExceptionValid("Guia ya existe");
            }
        }
    }
    public ResponseEntity<Optional<TruksModel>> getDataDoc(TruksModel truksDTO, BindingResult result){
        if(!truksDTO.getGuide().isEmpty()) {

            String numero_guia = truksDTO.getGuide();
            Optional<TruksModel> camion = repositorio.findByGuide(numero_guia);

            if(camion == null || !camion.get().equals(truksDTO.getGuide())) {

                throw new ApiRequestExceptionValid("Guia no existe o los datos ingresados no son correctos");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(camion);
            }
        }else {
            throw new ApiRequestExceptionValid("Debe ingresar el Documento y tipo");
        }
    }
}
