package system;

import model.Librarian;
import model.Reader;
import model.Role;
import model.User;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

import repository.UserRepository;

import util.CsvUtils;
import util.InputHelper;
import util.SystemConstants;

public class MainSystem {
    UserRepository userRepository = new UserRepository();

    public void start() {
        greetingsHeader();
        mainLoop:
        while(true) {
            mainMenuHeader();
            System.out.println("Pilih menu: " +
                    "\n1. Login" +
                    "\n2. Register" +
                    "\n3. Exit");
            System.out.print("Pilihan: ");
            int chosen = InputHelper.getInputInterval(1, 3);
            if (chosen == -1) {
                System.out.println("\n" + SystemConstants.PREFIX_INVALID_INPUT + "Pilih dengan angka 1 sampai 3!");
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
                    exitHeader();
                    break mainLoop;
            }
        }
    }

    // ==================================================== HANDLER
    private void handleLogin() {
        loginHeader();
        // ------------------------------------------------ Username input
        System.out.print("Masukkan username Anda: ");
        String username = InputHelper.getInputString(true);
        if (CsvUtils.isItemInCsv(SystemConstants.PATH_CSV_USER, username, 1)) {
            System.out.println(SystemConstants.PREFIX_SUCCEED + "Username ditemukan!");
            // -------------------------------------------- Password input
            System.out.print("Masukkan password Anda: ");
            String password = InputHelper.getInputString(false);
            String passwordActual = CsvUtils.getItemByKeyCsv(SystemConstants.PATH_CSV_USER, username, 1, 3);
            if (BCrypt.checkpw(password, passwordActual)) {
                System.out.println(SystemConstants.PREFIX_SUCCEED + "Login berhasil!");
                if (CsvUtils.getItemByKeyCsv(SystemConstants.PATH_CSV_USER, username, 1, 4).equals(Role.LIBRARIAN.name())) {
                    LibrarianSystem librarianService = new LibrarianSystem();
                    librarianService.start();
                } else {
                    ReaderSystem readerService = new ReaderSystem();
                    readerService.start();
                }
            } else {
                System.out.println(SystemConstants.PREFIX_FAILED + "Password salah!");
            }
        } else {
            System.out.println(SystemConstants.PREFIX_FAILED + "Username tidak ditemukan!");
        }
    }

    private void handleRegister() {
        registerHeader();
        // ------------------------------------------------ Username input
        String username;
        while (true) {
            System.out.print("Masukkan username Anda: ");
            username = InputHelper.getInputString(true);
            if (!CsvUtils.isItemInCsv(SystemConstants.PATH_CSV_USER, username, 1)) {
                break;
            } else {
                System.out.println(SystemConstants.PREFIX_FAILED + SystemConstants.MESSAGE_USERNAME_TAKEN);
            }
        }
        // ------------------------------------------------ Email input
        String email;
        while(true) {
            System.out.print("Masukkan email Anda: ");
            email = InputHelper.getInputString(true);
            if (!emailValidator(email)) {
                System.out.println(SystemConstants.MESSAGE_EMAIL_INVALID);
            } else if (CsvUtils.isItemInCsv(SystemConstants.PATH_CSV_USER, email, 2)) {
                System.out.println(SystemConstants.MESSAGE_EMAIL_LOGGED);
            } else {
                break;
            }
        }
        // ------------------------------------------------ Password input
        System.out.print("Masukkan password Anda: ");
        String password = InputHelper.getInputString(false);
        // ------------------------------------------------ Finalization
        User user;
        while (true) {
            System.out.println("Daftar sebagai: " +
                    "\n1. Librarian" +
                    "\n2. Reader");
            System.out.print("Pilihan: ");
            int inputRole = InputHelper.getInputInterval(1, 2);
            if (inputRole == -1) {
                System.out.println(SystemConstants.PREFIX_INVALID_INPUT + "Pilih dengan angka 1 atau 2!");
                continue;
            }
            Role role = (inputRole == 1) ? Role.LIBRARIAN : Role.READER;
            user = (inputRole == 1) ? new Librarian(username, email, password) : new Reader(username, email, password);
            user.setRole(role);
            break;
        }
        String[] data = {
                user.getId().toString(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().name() };
        CsvUtils.writeCSV(SystemConstants.PATH_CSV_USER, data);
        userRepository.addUser(user);
        System.out.println(SystemConstants.PREFIX_SUCCEED + "Registrasi berhasil!");
    }
    
    // ==================================================== OTHER
    private boolean emailValidator(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    // ==================================================== HEADER
    private void greetingsHeader() {
        System.out.println("\n========================================");
        System.out.println("|     Selamat Datang di MasLibrary     |");
        System.out.println("========================================");
    }

    private void exitHeader() {
        System.out.println("\n========================================");
        System.out.println("|             Terima Kasih             |");
        System.out.println("========================================");
    }

    private void mainMenuHeader() {
        System.out.println("\n--------------- Main Menu --------------");
    }

    private void loginHeader() {
        System.out.println("\n----------------- Login ----------------");
    }

    private void registerHeader() { System.out.println("\n-------------- Registrasi --------------"); }
}
