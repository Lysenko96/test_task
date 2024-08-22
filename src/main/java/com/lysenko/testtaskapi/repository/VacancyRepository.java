package com.lysenko.testtaskapi.repository;

import com.lysenko.testtaskapi.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

//    @Query(value = "SELECT * FROM vacancy ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
//    List<Vacancy> findVacanciesLimitByCreatedAtDesc(@Param("limit") int limit);

    @Query(value = "SELECT DISTINCT LOCATION as location, COUNT(*) as counter FROM VACANCY GROUP BY LOCATION", nativeQuery = true)
    List<String> findCounterByLocation();
}
