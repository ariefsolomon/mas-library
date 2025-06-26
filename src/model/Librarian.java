package model;

import java.util.ArrayList;
import java.util.List;

public class Librarian extends User {
    private List<Book> library;

    public Librarian(String username, String email, String password, boolean isHashed) {
        super(username, email, password, isHashed);
        this.library = new ArrayList<>();
    }

    public List<Book> getLibrary() {
        return library;
    }
}
