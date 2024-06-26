package com.aluracursos.literalura.services;

import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Author findByNameAuthor(String name){
        Optional<Author> author = authorRepository.findByName(name);
        if (author.isEmpty()){
            return null;
        }
        return author.get();
    }

    public List<Author> findAuthorsByName(String name){
        List<Author> author = authorRepository.findByNameContaining(name);
        if (author.isEmpty()){
            return null;
        }
        return author;
    }

}
