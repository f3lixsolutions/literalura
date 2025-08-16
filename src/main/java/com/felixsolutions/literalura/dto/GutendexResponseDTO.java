package com.felixsolutions.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponseDTO {
    private List<GutendexBookDTO> results;

    public List<GutendexBookDTO> getResults() { return results; }
}
