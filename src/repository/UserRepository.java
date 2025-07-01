package repository;

import com.opencsv.CSVWriter;
import model.Librarian;
import model.Reader;
import model.Role;
import model.User;
import util.CsvUtils;
import util.SystemConstants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    private List<User> userRepository;
    private BookRepository bookRepository;

    public UserRepository(BookRepository bookRepository) {
        if (!new java.io.File(SystemConstants.PATH_CSV_USER).exists()) {
            CsvUtils.writeCSV(SystemConstants.PATH_CSV_USER, SystemConstants.CSV_USER_HEADER, false);
        }
        this.userRepository = loadUsersFromCsv(true);
        this.bookRepository = bookRepository;
    }

    private List<User> loadUsersFromCsv(boolean header) {
        List<User> users = new ArrayList<>();
        List<String[]> data = CsvUtils.readCSV(SystemConstants.PATH_CSV_USER);
        for (String[] row : data) {
            if (header) {
                header = false;
                continue;
            }
            Role role = Role.valueOf(row[4]);
            User user;
            if (role == Role.LIBRARIAN) {
                user = new Librarian(row[1], row[2], row[3], true, bookRepository);
            } else {
                user = new Reader(row[1], row[2], row[3], true, bookRepository);
            }
            user.setId(UUID.fromString(row[0]));
            user.setRole(role);
            users.add(user);
        }
        return users;
    }

    public void saveUsersToCsv(String path, String[] header) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeNext(header);
            for (User user : userRepository) {
                String[] data = {user.getId().toString(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().name()};
                writer.writeNext(data);
            }
        } catch (IOException e) {
            System.err.println(SystemConstants.PREFIX_FAILED + e.getMessage());
        }
    }

    public User getUserByUsername(String username) {
        for (User user : userRepository) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        userRepository.add(user);
    }
}
