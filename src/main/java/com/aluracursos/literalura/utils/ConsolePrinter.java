package com.aluracursos.literalura.utils;

import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.models.BookLanguage;
import com.aluracursos.literalura.services.AuthorApiResponse;
import com.aluracursos.literalura.services.AuthorService;
import com.aluracursos.literalura.services.BookApiResponse;
import com.aluracursos.literalura.services.BookApiService;
import com.aluracursos.literalura.services.ResultsApiResponse;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsolePrinter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private final Scanner scanner = new Scanner(System.in);
    private final BookApiService bookApiService;
    private final AuthorService authorService;

    public ConsolePrinter(BookApiService bookApiService, AuthorService authorService) {
        this.bookApiService = bookApiService;
        this.authorService = authorService;
    }

    public void showInitMessage() {
        System.out.println();
        System.out.println(ANSI_PURPLE + """
                                .-~~~~~~~~~-._       _.-~~~~~~~~~-.
                            __.'              ~.   .~              `.__
                          .'//                  \\./                  \\\\`.
                        .'//                     |                     \\\\`.
                      .'// .-~""\"""\""~~~~-._     |     _,-~~~~""\"""\""~-. \\\\`.    \s
                    .'//.-"                 `-.  |  .-'                 "-.\\\\`.
                  .'//______.============-..   \\ | /   ..-============.______\\\\`.
                .'______________________________\\|/______________________________`.
                """ + ANSI_RESET);
        System.out.print(ANSI_YELLOW + """
                 _      _____  _____  _____ ______   ___   _      _   _ ______   ___ \s
                | |    |_   _||_   _||  ___|| ___ \\ / _ \\ | |    | | | || ___ \\ / _ \\\s
                | |      | |    | |  | |__  | |_/ // /_\\ \\| |    | | | || |_/ // /_\\ \\
                | |      | |    | |  |  __| |    / |  _  || |    | | | ||    / |  _  |
                | |____ _| |_   | |  | |___ | |\\ \\ | | | || |____| |_| || |\\ \\ | | | |
                \\_____/ \\___/   \\_/  \\____/ \\_| \\_|\\_| |_/\\_____/ \\___/ \\_| \\_|\\_| |_/
                                                                                     \s
                                                                                     \s
                """ + ANSI_RESET);
    }

    public void showExtrasMessage() {
        System.out.println();
        System.out.println(ANSI_GREEN +"""
                 _______   _____________  ___   _____\s
                |  ___\\ \\ / /_   _| ___ \\/ _ \\ /  ___|
                | |__  \\ V /  | | | |_/ / /_\\ \\\\ `--.\s
                |  __| /   \\  | | |    /|  _  | `--. \\
                | |___/ /^\\ \\ | | | |\\ \\| | | |/\\__/ /
                \\____/\\/   \\/ \\_/ \\_| \\_\\_| |_/\\____/\s
                """ + ANSI_RESET);
    }

    public void menu() {
        showInitMessage();
        String opt = "-1";

        while (!opt.equals("0")) {
            System.out.println("""
                     +=====================================================================+
                     | Seleccione una opcion:                                              |
                     | 1 - Buscar libro por título.                                        |
                     | 2 - Listar libros regitrados.                                       |
                     | 3 - Listar autores registrados.                                     |
                     | 4 - Listar autores vivos en un determinado año.                     |
                     | 5 - Listar libros por idioma.                                       |
                     | 6 - Opciones extra.                                                 |
                     |_____________________________________________________________________|
                     | 0 - Salir.                                                          |
                     +=====================================================================+
                    """);

            System.out.print("Haz seleccionado la opcion: ");
            opt = scanner.nextLine();

            switch (opt) {
                case "1" -> searchBookByTitle();
                case "2" -> listBooks();
                case "3" -> listAuthors();
                case "4" -> searchAuthorsByYear();
                case "5" -> menuSearchByLanguage();
                case "6" -> menuExtra();
                case "0" -> System.out.println("Cerrando la aplicacion...");
                default -> System.out.println("Haz ingresado una opcion no valida.");
            }
            System.out.println("Presione enter para continuar... ");
            scanner.nextLine();
        }
    }

    public void menuSearchByLanguage() {
        while (true) {
            System.out.println("**** Idiomas disponibles ****");
            System.out.println();
            Stream.of(BookLanguage.values())
                    .filter(b -> !b.equals(BookLanguage.UNKNOWN))
                    .collect(Collectors.toMap(BookLanguage::getLanguageExt, BookLanguage::getLanguageName))
                    .forEach((k, v) -> System.out.println(k + " - " + v));

            System.out.println();
            System.out.print("Ingresa el idioma para buscar los libros o \"q\" para salir: ");
            String language = scanner.nextLine();

            if (language.equals("q")) {
                break;
            }

            BookLanguage bookLanguage = BookLanguage.fromString(language);
            if (bookLanguage == BookLanguage.UNKNOWN) {
                System.out.println("El idioma ingresado no es valido.");
                continue;
            }
            searchBookByLanguage(bookLanguage);
            return;
        }
    }

    private void menuExtra() {
        showExtrasMessage();
        String opt = "-1";
        while (!opt.equals("0")) {
            System.out.println("""
                    +=====================================================================+
                    | Seleccione una opcion:                                              |
                    | 1 - Generar Estadisticas.                                           |
                    | 2 - Obtener top 10 libros mas descargados.                          |
                    | 3 - Buscar autor por nombre.                                        |
                    | 4 - Listar autores muertes por siglo                                |
                    |_____________________________________________________________________|
                    | 0 - Volver.                                                         |
                    +=====================================================================+
                    """);

            System.out.print("Haz seleccionado la opcion: ");
            opt = scanner.nextLine();
            switch (opt) {
                case "1" -> generateStatistics();
                case "2" -> searchTop1Books();
                case "3" -> searchAuthorByName();
                case "4" -> searchDeathAuthorsByCentury();
                case "0" -> System.out.println("Volviendo al menu principal...");
                default -> System.out.println("Haz ingresado una opcion no valida.");
            }
            System.out.println("Presione enter para continuar... ");
            scanner.nextLine();
        }
    }

    public void searchBookByTitle() {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        ResultsApiResponse result = bookApiService.getBookByTitle(title);

        if (result.count() == 0 || result.results().isEmpty()) {
            System.out.println("No ha sido posible encontrar el libro.");
            return;
        }
        BookApiResponse bookResponse = result.results().get(0);
        AuthorApiResponse authorApiResponse = bookResponse.authors().get(0);
        Author existingAuthor = authorService.findByNameAuthor(authorApiResponse.name());

        if (existingAuthor == null) {
            existingAuthor = Author
                    .create(authorApiResponse.name(), authorApiResponse.birthYear(), authorApiResponse.deathYear());
        }

        Book book = existingAuthor.addNewBook(
                bookResponse.id(), bookResponse.title(),
                bookResponse.languages(), bookResponse.downloadCount());

        if (book == null) {
            System.out.println("El libro ya se encuentra registrado.");
            return;
        }

        authorService.saveAuthor(existingAuthor);
        System.out.println(book);
    }

    public void listBooks(){
        System.out.println("**** Libros registrados ****");
        authorService.getAuthors()
                .stream()
                .map(Author::getBooks)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }

    private void listAuthors() {
        System.out.println("**** Autores registrados ****");
        authorService.getAuthors()
                .forEach(System.out::println);
    }
    private void searchBookByLanguage(BookLanguage bookLanguage) {
        authorService.getAuthors()
                .stream()
                .map(Author::getBooks)
                .flatMap(Collection::stream)
                .filter(b -> b.getLanguage() == bookLanguage)
                .forEach(System.out::println);
    }

    private void searchAuthorsByYear() {
        try {
            System.out.print("Ingrese el año: ");
            int year = Integer.parseInt(scanner.nextLine());
            authorService.getAuthors()
                    .stream()
                    .filter(a -> a.getDeathYear() == null || a.getDeathYear() > year)
                    .forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("El año ingresado no es valido.");
        }
    }

    private void searchTop1Books() {
        System.out.println("**** Top 10 libros mas descargados ****");
        authorService.getAuthors()
                .stream()
                .map(Author::getBooks)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Book::getDownloadCount).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    private void generateStatistics() {
        System.out.println("**** Estadisticas ****");
        List<Author> authors = authorService.getAuthors();
        var est = authors.stream()
                .map(Author::getBooks)
                .flatMap(Collection::stream).collect(Collectors.summarizingLong(Book::getDownloadCount));
        System.out.println("Total de libros registrados: " + est.getCount());
        System.out.println("Total de descargas: " + est.getSum());
        System.out.println("Promedio de descargas: " + est.getAverage());
        System.out.println("Maximo de descargas: " + est.getMax());
        System.out.println("Minimo de descargas: " + est.getMin());
        System.out.println();
    }

    private void searchAuthorByName() {
        System.out.print("Ingrese el nombre del autor: ");
        String name = scanner.nextLine();
        List<Author> author = authorService.findAuthorsByName(name);

        if (author == null) {
            System.out.println("No se ha encontrado ningun autor relacionado.");
            return;
        }
        System.out.println("*** Autores encontrados *** ");
        author.forEach(System.out::println);
    }

    private void searchDeathAuthorsByCentury() {
        try {
            System.out.print("Ingrese el siglo (Valor numerico): ");
            int century = Integer.parseInt(scanner.nextLine());
            if (century < 1 || century > 21) throw new NumberFormatException();
            authorService.getAuthors()
                    .stream()
                    .filter(a -> a.getDeathYear() > (century - 1) * 100 + 1 && a.getDeathYear() < century * 100)
                    .forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("El año ingresado no es valido.");
        }
    }
}
