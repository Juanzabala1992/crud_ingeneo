package com.login.authentication.repository;

import com.login.authentication.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, String> {
    Optional<ScheduleModel> findByIdSch(String idSch);
    List<ScheduleModel> findByIdUser(String idUser);

}
