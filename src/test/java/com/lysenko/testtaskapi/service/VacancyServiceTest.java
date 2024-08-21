package com.lysenko.testtaskapi.service;

import com.lysenko.testtaskapi.repository.VacancyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    }

    @Test
    void search() {
    }

    @Test
    void getStatistics() {

    }
}