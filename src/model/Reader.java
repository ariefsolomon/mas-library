package model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends User {
    private List<Book> borrowedBooks;

    public Reader(String username, String email, String password, boolean isHashed) {
        super(username, email, password, isHashed);
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
