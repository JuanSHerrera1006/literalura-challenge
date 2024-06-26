package com.aluracursos.literalura.services;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookApiResponse(
        @JsonAlias("id")
        Long id,
        @JsonAlias("title")
        String title,
        @JsonAlias("languages")
        List<String> languages,
        @JsonAlias("authors")
        List<AuthorApiResponse> authors,
        @JsonAlias("download_count")
        Long downloadCount
) {}
