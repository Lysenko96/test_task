package com.lysenko.testtaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lysenko.testtaskapi.service.VacancyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VacancyControllerTest {

    @Mock
    private VacancyService vacancyService;

    @InjectMocks
    private VacancyController vacancyController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vacancyController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void download() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/download")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        Mockito.verify(vacancyService, Mockito.times(1)).download(5);
    }

    @Test
    void search() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/search")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        Mockito.verify(vacancyService, Mockito.times(1)).search(1, 10);
    }

    @Test
    void statistic() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/statistic")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        Mockito.verify(vacancyService, Mockito.times(1)).getStatistics();
    }
}