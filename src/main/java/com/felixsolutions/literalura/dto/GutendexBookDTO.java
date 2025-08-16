package com.felixsolutions.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexBookDTO {
    private Integer id;
    private String title;
    private List<GutendexAuthorDTO> authors;
    private List<String> languages;
    @JsonAlias("download_count")
    private Integer downloadCount;

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public List<GutendexAuthorDTO> getAuthors() { return authors; }
    public List<String> getLanguages() { return languages; }
    public Integer getDownloadCount() { return downloadCount; }
}
