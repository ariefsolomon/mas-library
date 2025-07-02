package model;

import repository.BookRepository;
import util.CsvUtils;
import java.io.File;
import java.util.List;

public class Librarian extends User {
    private BookRepository library;
    private BookRepository bookRepository;
    private final String libraryPath;

    public Librarian(String username, String email, String password, boolean isHashed, BookRepository bookRepository) {
        super(username, email, password, isHashed);
        this.libraryPath = "database" + "/" + "users_books" + "/" + username + "s_books" + ".csv";
        File csvFile = new File(libraryPath);
        if (!csvFile.exists()) {
            CsvUtils.writeCSV(libraryPath, new String[]{}, false);
        }
        this.library = new BookRepository(libraryPath);
        this.bookRepository = bookRepository;
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
