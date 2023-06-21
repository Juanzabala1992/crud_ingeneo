package com.login.authentication.controller;


import java.util.List;
import java.util.Optional;

import com.login.authentication.model.ScheduleModel;
import com.login.authentication.repository.ScheduleRepository;
import com.login.authentication.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins="http://localhost:4200/")
public class ScheduleController {

    @Autowired
    private ScheduleRepository repositorio;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/hello")
    @PreAuthorize("hasRole('client_admin')")
    public String hello() {
        return "Hello from Spring boot & Keycloak";
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity <Optional<ScheduleModel>> getSchId(@PathVariable String id){
        Optional<ScheduleModel> schedule = repositorio.findByIdSch(id);
        return scheduleService.getData(schedule);
    }
    @GetMapping("/schedule/user/{id}")
    public ResponseEntity <List<ScheduleModel>>  getSchByUser(@PathVariable String id){
        List<ScheduleModel> schedule = repositorio.findByIdUser(id);
        return scheduleService.getByUserId(schedule);
    }
    @GetMapping("/schedules/all")
    public ResponseEntity <List<ScheduleModel>> listAllSchedules(){
        List<ScheduleModel> all = repositorio.findAll();
        return scheduleService.getAllData(all);
    }
    @PostMapping("/save")
    public ResponseEntity<ScheduleModel> saveSchedule(@Valid @RequestBody ScheduleModel scheduleModel, BindingResult result) {
        return scheduleService.setData(scheduleModel, result);
    }
}
