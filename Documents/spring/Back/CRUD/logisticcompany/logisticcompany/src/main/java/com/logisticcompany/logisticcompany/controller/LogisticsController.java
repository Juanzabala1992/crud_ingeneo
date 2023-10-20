package com.logisticcompany.logisticcompany.controller;
import java.util.List;
import java.util.Optional;
import com.logisticcompany.logisticcompany.model.TruksModel;
import com.logisticcompany.logisticcompany.repository.TruksRepository;
import com.logisticcompany.logisticcompany.service.TruksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins="http://localhost:4200/")
public class LogisticsController {

    @Autowired
    private TruksRepository repositorio;

    @Autowired
    private TruksService truksService;

    @GetMapping("/guias/all")
    public ResponseEntity <List<TruksModel>> listarTodasLasGuias(@Valid BindingResult result){
        List<TruksModel> all = repositorio.findAll();
        return truksService.getAllData(all);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('client_admin')")
    public String hello() {
        return "Hello from Spring boot & Keycloak";
    }

    @GetMapping("/guia/{id}")
    public ResponseEntity <Optional<TruksModel>> obtenerGuia(@PathVariable String id, @Valid BindingResult result){
        Optional<TruksModel> persona = repositorio.findById(id);
        return truksService.getData(persona);
    }
    @GetMapping("/truks/guia")
    public ResponseEntity<Optional<TruksModel>> obtenerPersona(@Valid @RequestBody TruksModel personasDTO, BindingResult result){
        return truksService.getDataDoc(personasDTO, result);

    }
    @PostMapping("/save")
    public ResponseEntity<TruksModel> guardarGuia(@Valid @RequestBody TruksModel persona, BindingResult result) {
        return truksService.setData(persona, result);
    }
}
