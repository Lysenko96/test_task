package com.lysenko.testtaskapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lysenko.testtaskapi.model.Vacancy;
import com.lysenko.testtaskapi.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Service
@RequiredArgsConstructor
//@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@Transactional
@Slf4j
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public List<Vacancy> download(int pageCounter) {
        String jsonStr = null;
        List<Vacancy> vacancies = new ArrayList<>();

        try {
            for (int i = 1; i <= pageCounter; i++) {

                URL url = new URL("https://www.arbeitnow.com/api/job-board-api?page=" + i);
                URLConnection con = url.openConnection();
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                jsonStr = sb.toString();

            }

            List<Vacancy> vacancyList = new ArrayList<>();
            if (jsonStr != null) {
                jsonStr = jsonStr.replace("created_at", "created");

                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                ObjectMapper mapper = new ObjectMapper();
                vacancyList = mapper.readValue(jsonArray.toString(), new TypeReference<>() {
                });
            }
            vacancies = vacancyRepository.saveAll(vacancyList);
            log.debug("download");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    public Page<Vacancy> search(int page, int size) {
//        List<Vacancy> result = vacancyRepo.findVacanciesLimitByCreatedAtDesc();
//        Page<Vacancy> finalResult = new PageImpl<>(result);
        Sort sort = Sort.by(Sort.Direction.DESC, "created");
        Page<Vacancy> result = vacancyRepository.findAll(PageRequest.of(page, size, sort));
        log.debug("search");
        return result;
    }

    public List<String> getStatistics() {
        List<String> result = vacancyRepository.findCounterByLocation();
        log.debug("getStatistics");
        return result;
    }
}
