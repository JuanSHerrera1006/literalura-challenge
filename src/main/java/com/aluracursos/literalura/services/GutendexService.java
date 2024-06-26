package com.aluracursos.literalura.services;

import com.aluracursos.literalura.utils.ApiConsumerProvider;
import com.aluracursos.literalura.utils.DataParserProvider;

public class GutendexService implements BookApiService{
    private final String BASE_URL = "https://gutendex.com";
    private final ApiConsumerProvider apiConsumerProvider;
    private final DataParserProvider dataParserProvider;
    public GutendexService(ApiConsumerProvider apiConsumerProvider, DataParserProvider dataParserProvider) {
        this.apiConsumerProvider = apiConsumerProvider;
        this.dataParserProvider = dataParserProvider;
    }
    @Override
    public ResultsApiResponse getBookByTitle(String title) {
        String scapeTitle = title.replace(" ", "%20").toLowerCase().trim();
        String response = apiConsumerProvider.getRequest(BASE_URL + "/books/?search=" + scapeTitle);
        return dataParserProvider.jsonToClass(response, ResultsApiResponse.class);
    }
}
