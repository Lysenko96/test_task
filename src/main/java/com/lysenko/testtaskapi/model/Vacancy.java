package com.lysenko.testtaskapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    private String company_name;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean remote;
    private String url;
    @ElementCollection
    private List<String> tags;
    @ElementCollection
    private List<String> job_types;
    private String location;
    private Date created;

    public Vacancy(String slug, String company_name, String title, String description, boolean remote, String url, List<String> tags, List<String> job_types, String location, Date created) {
        this.slug = slug;
        this.company_name = company_name;
        this.title = title;
        this.description = description;
        this.remote = remote;
        this.url = url;
        this.tags = tags;
        this.job_types = job_types;
        this.location = location;
        this.created = created;
    }
}
