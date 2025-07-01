package model;

import repository.BookRepository;
import util.CsvUtils;

public class Reader extends User {
    private BookRepository borrowedBooks;
    private BookRepository bookRepository;
    private final String borrowedBooksPath;

    public Reader(String username, String email, String password, boolean isHashed, BookRepository bookRepository) {
        super(username, email, password, isHashed);
        this.borrowedBooksPath = "database" + "/" + "users_books" + "/" + username + "s_br_books";
        String[] initialData = {};
        CsvUtils.writeCSV(borrowedBooksPath, initialData, true);
        this.borrowedBooks = new BookRepository(borrowedBooksPath);
    }

    public BookRepository getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getBorrowedBooksPath() {
        return borrowedBooksPath;
    }
}
