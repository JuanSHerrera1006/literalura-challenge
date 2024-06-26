package com.aluracursos.literalura.services;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultsApiResponse(
        @JsonAlias("count")
        Long count,
        @JsonAlias("results")
        List<BookApiResponse> results
){}
