package com.aluracursos.literalura.repositories;

import com.aluracursos.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByName(String name);
    List<Author> findByNameContaining(String name);
}
