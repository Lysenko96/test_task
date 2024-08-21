package com.lysenko.testtaskapi.controller;

import com.lysenko.testtaskapi.model.Vacancy;
import com.lysenko.testtaskapi.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    //    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES) no args method only
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vacancy>> download(@RequestParam(defaultValue = "5") int pageCounter) {
        List<Vacancy> vacancies = vacancyService.download(pageCounter);
        return ResponseEntity.status(HttpStatus.OK).body(vacancies);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Vacancy>> getVacancies(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Page<Vacancy> vacancies = vacancyService.search(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(vacancies);
    }

    @GetMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> findStatistic() {
        List<String> statistics = vacancyService.getStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }
}
