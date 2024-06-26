package com.aluracursos.literalura;

import com.aluracursos.literalura.services.AuthorService;
import com.aluracursos.literalura.services.BookApiService;
import com.aluracursos.literalura.utils.ConsolePrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final BookApiService bookApiService;
    private final AuthorService authorService;

    public ConsoleRunner(BookApiService bookApiService, AuthorService authorService) {
        this.bookApiService = bookApiService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        ConsolePrinter consolePrinter = new ConsolePrinter(bookApiService, authorService);
        consolePrinter.menu();
    }
}
