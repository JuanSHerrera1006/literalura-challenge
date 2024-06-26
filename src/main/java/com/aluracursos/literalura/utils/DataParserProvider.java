package com.aluracursos.literalura.utils;

public interface DataParserProvider {
    <T> T jsonToClass(String data, Class<T> customClass);
}
