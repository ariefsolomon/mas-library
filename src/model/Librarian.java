package model;

import repository.BookRepository;
import util.CsvUtils;

public class Librarian extends User {
    private BookRepository library;
    private BookRepository bookRepository;
    private final String libraryPath;

    public Librarian(String username, String email, String password, boolean isHashed, BookRepository bookRepository) {
        super(username, email, password, isHashed);
        this.libraryPath = "database" + "/" + "users_books" + "/" + username + "s_books" + ".csv";
        String[] initialData = {};
        CsvUtils.writeCSV(libraryPath, initialData, true);
        this.library = new BookRepository(libraryPath);
    }

    public BookRepository getLibrary() {
        return library;
    }

    public void addBook(Book book) {
        library.addBook(book);
        bookRepository.addBook(book);
    }

    public void removeBook(Book book) {
        library.removeBook(book);
    }

    public String getLibraryPath() {
        return libraryPath;
    }
}
