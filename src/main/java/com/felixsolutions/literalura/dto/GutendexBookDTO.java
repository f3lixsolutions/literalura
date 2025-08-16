package com.felixsolutions.literalura.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GutendexBookDTO {
    private Integer id;
    private String title;
    private java.util.List<GutendexAuthorDTO> authors;
    private java.util.List<String> languages;
    @SerializedName("download_count") private Integer downloadCount;

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public List<GutendexAuthorDTO> getAuthors() { return authors; }
    public List<String> getLanguages() { return languages; }
    public Integer getDownloadCount() { return downloadCount; }
}
