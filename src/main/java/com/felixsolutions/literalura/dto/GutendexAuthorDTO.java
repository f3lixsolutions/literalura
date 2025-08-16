package com.felixsolutions.literalura.dto;

import com.google.gson.annotations.SerializedName;

public class GutendexAuthorDTO {
    private String name;
    @SerializedName("birth_year") private Integer birthYear;
    @SerializedName("death_year") private Integer deathYear;

    public String getName() { return name; }
    public Integer getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
}
