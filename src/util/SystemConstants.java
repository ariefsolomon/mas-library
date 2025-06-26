package util;

import repository.UserRepository;

public class SystemConstants {
    public static final UserRepository USER_REPOSITORY = new UserRepository();

    public static final String PATH_CSV_USER = "database/user.csv";
    public static final String PATH_CSV_LIBRARIAN_BOOKS = "database/user.csv";

    public static final String[] CSV_USER_HEADER = {"id", "username", "email", "password", "role"};

    public static final String PREFIX_SUCCEED = "[SUCCEED] ";
    public static final String PREFIX_FAILED = "[FAILED] ";
    public static final String PREFIX_INVALID_INPUT = "[INVALID INPUT] ";

    public static final String MESSAGE_USERNAME_TAKEN = "Username sudah digunakan!";
    public static final String MESSAGE_EMAIL_INVALID = "Email tidak valid!";
    public static final String MESSAGE_EMAIL_LOGGED = "Email sudah terdaftar!";
    public static final String MESSAGE_IS_CONTINUE = "Ingin melanjutkan? (y/N): ";
}
