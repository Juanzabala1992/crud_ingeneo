package com.login.authentication.repository;

import com.login.authentication.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, String> {
    Optional<ScheduleModel> findByIdSch(String idSch);
    List<ScheduleModel> findByIdUser(String idUser);

    @Query("SELECT COUNT(s) > 0 FROM ScheduleModel s WHERE s.fecha_inicio <= :fechaFin AND s.fecha_fin >= :fechaInicio AND s.idUser = :idUser")
    Boolean findBetweenDates(LocalDate fechaInicio, LocalDate fechaFin, String idUser);
}
