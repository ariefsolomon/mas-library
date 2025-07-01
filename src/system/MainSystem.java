package system;

import model.Librarian;
import model.Reader;
import model.Role;
import model.User;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

import repository.BookRepository;
import repository.UserRepository;

import util.*;

public class MainSystem {
    private BookRepository bookRepository = new BookRepository(SystemConstants.PATH_CSV_BOOKS);
    private UserRepository userRepository = new UserRepository(bookRepository);
    private User currentUser;

    public void start() {
        SystemHeader.showHeader("WELCOME TO MASLIBRARY", SystemConstants.WIDTH, 1);
        SystemHeader.showHeader("Main", SystemConstants.WIDTH, 0);
        mainLoop:
        while(true) {
            SystemHeader.showSubHeader("Main Menu", SystemConstants.WIDTH);
            System.out.println("1. Login" +
                    "\n2. Register" +
                    "\n3. Exit");
            System.out.print("Select: ");
            int chosen = InputHelper.getInputInterval(1, 3);
            if (chosen == -1) {
                System.out.println("\n" + SystemConstants.PREFIX_INVALID_INPUT + "Enter a number in 1-3!");
                continue;
            }
            switch (chosen) {
                case 1:
                    handleLogin();
                    continue;
                case 2:
                    handleRegister();
                    continue;
                case 3:
                    userRepository.saveUsersToCsv(SystemConstants.PATH_CSV_USER, SystemConstants.CSV_USER_HEADER);
                    SystemHeader.showHeader("Thank you!", SystemConstants.WIDTH, 1);
                    break mainLoop;
            }
        }
    }

    // ==================================================== HANDLER
    private void handleLogin() {
        SystemHeader.showSubHeader("Login", SystemConstants.WIDTH);
        // ------------------------------------------------ Username input
        System.out.print("Username: ");
        String username = InputHelper.getInputString(true);
        if (CsvUtils.isItemInCsv(SystemConstants.PATH_CSV_USER, username, 1)) {
            System.out.println(SystemConstants.PREFIX_SUCCEED + "Username found!");
            // -------------------------------------------- Password input
            System.out.print("Password: ");
            String password = InputHelper.getInputString(false);
            String passwordActual = CsvUtils.getItemByKeyCsv(SystemConstants.PATH_CSV_USER, username, 1, 3);
            if (BCrypt.checkpw(password, passwordActual)) {
                System.out.println(SystemConstants.PREFIX_SUCCEED + "Login success!");
                currentUser = userRepository.getUserByUsername(username);
                if (CsvUtils.getItemByKeyCsv(SystemConstants.PATH_CSV_USER, username, 1, 4).equals(Role.LIBRARIAN.name())) {
                    LibrarianSystem librarianService = new LibrarianSystem(currentUser, bookRepository);
                    librarianService.start();
                } else {
                    ReaderSystem readerService = new ReaderSystem(currentUser, bookRepository);
                    readerService.start();
                }
            } else {
                System.out.println(SystemConstants.PREFIX_FAILED + "Wrong password!");
            }
        } else {
            System.out.println(SystemConstants.PREFIX_FAILED + "Username not found!");
        }
    }

    private void handleRegister() {
        SystemHeader.showSubHeader("Registration", SystemConstants.WIDTH);
        // ------------------------------------------------ Username input
        String username;
        while (true) {
            System.out.print("Username: ");
            username = InputHelper.getInputString(true);
            if (!CsvUtils.isItemInCsv(SystemConstants.PATH_CSV_USER, username, 1)) {
                break;
            } else {
                System.out.println(SystemConstants.PREFIX_FAILED + "The username is taken!");
            }
        }
        // ------------------------------------------------ Email input
        String email;
        while(true) {
            System.out.print("Email: ");
            email = InputHelper.getInputString(true);
            if (!EmailValidator.emailValidator(email)) {
                System.out.println(SystemConstants.PREFIX_INVALID_INPUT + "Invalid email!");
            } else if (CsvUtils.isItemInCsv(SystemConstants.PATH_CSV_USER, email, 2)) {
                System.out.println(SystemConstants.PREFIX_FAILED + "The email is logged!");
            } else {
                break;
            }
        }
        // ------------------------------------------------ Password input
        System.out.print("Password: ");
        String password = InputHelper.getInputString(false);
        // ------------------------------------------------ Finalization
        User user;
        while (true) {
            System.out.println("Register as: " +
                    "\n1. Librarian" +
                    "\n2. Reader");
            System.out.print("Select: ");
            int inputRole = InputHelper.getInputInterval(1, 2);
            if (inputRole == -1) {
                System.out.println(SystemConstants.PREFIX_INVALID_INPUT + "Enter a number 1 or 2!");
                continue;
            }
            Role role = (inputRole == 1) ? Role.LIBRARIAN : Role.READER;
            user = (inputRole == 1) ? new Librarian(username, email, password, false, bookRepository) : new Reader(username, email, password, false, bookRepository);
            user.setRole(role);
            break;
        }
        String[] data = {
                user.getId().toString(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name() };
        userRepository.addUser(user);
        CsvUtils.writeCSV(SystemConstants.PATH_CSV_USER, data, true);
        System.out.println(SystemConstants.PREFIX_SUCCEED + "Registration success!");
    }
}
