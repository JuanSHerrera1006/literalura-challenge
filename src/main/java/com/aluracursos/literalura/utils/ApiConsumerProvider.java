package com.aluracursos.literalura.utils;

public interface ApiConsumerProvider {
    <T> T getRequest(String url);
}
