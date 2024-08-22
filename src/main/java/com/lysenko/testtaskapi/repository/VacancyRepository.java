package com.lysenko.testtaskapi.repository;

import com.lysenko.testtaskapi.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

//    @Query(value = "SELECT * FROM vacancy ORDER BY created_at DESC LIMIT 10", nativeQuery = true)
//    List<Vacancy> find10ByCreatedAtDesc();

    @Query(value = "SELECT DISTINCT LOCATION as location, COUNT(*) as counter FROM VACANCY GROUP BY LOCATION", nativeQuery = true)
    List<String> findCounterByLocation();
}
