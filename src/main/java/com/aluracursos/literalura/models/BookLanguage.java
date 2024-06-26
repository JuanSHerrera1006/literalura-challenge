package com.aluracursos.literalura.models;

import java.util.Arrays;
import java.util.Optional;

public enum BookLanguage {
    EN("en", "Ingles"),
    PT("pt", "Portuges"),
    ES("es", "Español"),
    FR("fr", "Frances"),
    DE("de", "Aleman"),
    IT("it", "Italiano"),
    FI("fi", "Finlandes"),
    UNKNOWN("unknown", "Desconocido");
    private String languageExt;
    private String languageName;
    BookLanguage(String languageExt, String languageName) {
        this.languageExt = languageExt;
        this.languageName = languageName;
    }
    public static BookLanguage fromString(String input) {
        Optional<BookLanguage> category = Arrays
                .stream(BookLanguage.values())
                .filter(c -> c.languageExt.equalsIgnoreCase(input))
                .findFirst();

        if (category.isPresent()) {
            return category.get();
        }
        return BookLanguage.UNKNOWN;
    }

    public String getLanguageExt() {
        return languageExt;
    }

    public void setLanguageExt(String languageExt) {
        this.languageExt = languageExt;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
