package com.aluracursos.literalura.config;

import com.aluracursos.literalura.services.BookApiService;
import com.aluracursos.literalura.services.GutendexService;
import com.aluracursos.literalura.utils.ApiConsumerProvider;
import com.aluracursos.literalura.utils.DataParserProvider;
import com.aluracursos.literalura.utils.HttpRequestConsumer;
import com.aluracursos.literalura.utils.JacksonObjectMapperParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApiConsumerProvider apiConsumerProvider() {
        return new HttpRequestConsumer();
    }

    @Bean
    public DataParserProvider dataParserProvider() {
        return new JacksonObjectMapperParser();
    }

    @Bean
    public BookApiService bookApiService(ApiConsumerProvider apiConsumerProvider, DataParserProvider dataParserProvider) {
        return new GutendexService(apiConsumerProvider, dataParserProvider);
    }
}
