package com.lysenko.testtaskapi.service;

import com.lysenko.testtaskapi.model.Vacancy;
import com.lysenko.testtaskapi.repository.VacancyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class VacancyServiceTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    void download() {
        List<Vacancy> vacancies = vacancyService.download(0);
        Mockito.verify(vacancyRepository, Mockito.times(1)).saveAll(vacancies);
    }

    @Test
    void search() {
        vacancyService.search(1, 10);
        Pageable pageable = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "created"));
        Mockito.verify(vacancyRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    void getStatistics() {
        vacancyService.getStatistics();
        Mockito.verify(vacancyRepository, Mockito.times(1)).findCounterByLocation();
    }


}