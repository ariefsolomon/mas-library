package util;

import repository.UserRepository;

public class SystemConstants {
    public static final String PATH_CSV_USER = "database/user.csv";
    public static final String PATH_CSV_BOOKS = "database/books.csv";
    public static final String PATH_CSV_LIBRARIAN_BOOKS = "database/users_books.csv";

    public static final String[] CSV_USER_HEADER = {"id", "username", "email", "password", "role"};
    public static final String[] CSV_BOOK_HEADER = {"title", "author", "status_borrowed"};

    public static final String PREFIX_SUCCEED = "[SUCCEED] ";
    public static final String PREFIX_FAILED = "[FAILED] ";
    public static final String PREFIX_INVALID_INPUT = "[INVALID INPUT] ";
    public static final String PREFIX_ERROR = "[ERROR] ";

    public static final int WIDTH = 40;
}
