package com.felixsolutions.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexAuthorDTO {
    private String name;
    @JsonAlias("birth_year")
    private Integer birthYear;
    @JsonAlias("death_year")
    private Integer deathYear;

    public String getName() { return name; }
    public Integer getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
}
