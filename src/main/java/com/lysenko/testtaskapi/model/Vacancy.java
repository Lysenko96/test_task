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
    private String companyName;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean remote;
    private String url;
    @ElementCollection
    private List<String> tags;
    @ElementCollection
    private List<String> jobTypes;
    private String location;
    private Date createdAt;

    public Vacancy(String slug, String companyName, String title, String description, boolean remote, String url, List<String> tags, List<String> jobTypes, String location, Date createdAt) {
        this.slug = slug;
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.remote = remote;
        this.url = url;
        this.tags = tags;
        this.jobTypes = jobTypes;
        this.location = location;
        this.createdAt = createdAt;
    }
}
