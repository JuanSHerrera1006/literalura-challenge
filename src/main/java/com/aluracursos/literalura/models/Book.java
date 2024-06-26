package com.aluracursos.literalura.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    Long id;
    String title;
    @Enumerated(EnumType.STRING)
    BookLanguage language;
    @ManyToOne
    Author author;
    Long downloadCount;
    private Book(Long id, String title, BookLanguage language, Author author, Long downloadCount) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.author = author;
        this.downloadCount = downloadCount;
    }

    public Book() {}

    public static Book create(Long id, String title, List<String> language, Author author, Long downloadCount) {
        if (language == null || language.isEmpty()){
            return new Book(id, title, BookLanguage.UNKNOWN, author, downloadCount);
        }
        return new Book(id, title, BookLanguage.fromString(language.get(0)), author, downloadCount);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookLanguage getLanguage() {
        return language;
    }

    public void setLanguage(BookLanguage language) {
        this.language = language;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String formatted ="""
                
                -----------LIBRO-------------
                 Título: %s
                 Autor: %s
                 Idioma: %s
                 Numero de descargas: %d
                -----------------------------
                """.formatted(title, author.getName(), language, downloadCount);
        return formatted;

    }
}
