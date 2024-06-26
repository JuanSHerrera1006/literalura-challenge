package com.aluracursos.literalura.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    private Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Author() {}

    public static Author create(String name, Integer birthYear, Integer deathYear) {
        return new Author(name, birthYear, deathYear);
    }
    public Book addNewBook(Long id, String title, List<String> language, Long downloadCount) {
        Book book = Book.create(id, title, language, this, downloadCount);
        if (this.books.contains(book)){
            return null;
        }
        this.books.add(book);
        return book;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String formattedBooks = books.stream()
                .limit(5)
                .map(Book::getTitle).collect(Collectors.joining(", "));
        if (books.size() > 5){
            formattedBooks += ", (...)";
        }
        String formatted ="""
                 
                 -----------AUTOR-------------
                 Autor: %s
                 Fecha de nacimiento: %d
                 Fecha de fallecimiento: %d
                 Libros: [%s]
                 -----------------------------
                """.formatted(name, birthYear, deathYear, formattedBooks);
        return formatted;
    }
}
