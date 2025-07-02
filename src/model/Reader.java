package model;

import repository.BookRepository;
import util.CsvUtils;

import java.io.File;

public class Reader extends User {
    private BookRepository borrowedBooks;
    private BookRepository bookRepository;
    private final String borrowedBooksPath;

    public Reader(String username, String email, String password, boolean isHashed, BookRepository bookRepository) {
        super(username, email, password, isHashed);
        this.borrowedBooksPath = "database" + "/" + "users_books" + "/" + username + "s_br_books" + ".csv";
        File csvFile = new File(borrowedBooksPath);
        if (!csvFile.exists()) {
            CsvUtils.writeCSV(borrowedBooksPath, new String[]{}, false);
        }
        this.borrowedBooks = new BookRepository(borrowedBooksPath);
        this.bookRepository = bookRepository;
    }

    public BookRepository getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getBorrowedBooksPath() {
        return borrowedBooksPath;
    }
}
