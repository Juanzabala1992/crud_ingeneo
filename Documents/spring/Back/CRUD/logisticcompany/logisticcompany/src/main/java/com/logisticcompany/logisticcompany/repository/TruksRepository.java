package com.logisticcompany.logisticcompany.repository;
import com.logisticcompany.logisticcompany.model.TruksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruksRepository extends JpaRepository<TruksModel, String> {
    Optional<TruksModel> findByGuide(String numero_guia);

}
