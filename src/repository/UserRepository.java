package repository;

import com.opencsv.CSVWriter;
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

    public UserRepository() {
        userRepository = loadUsersFromCsv(true);
    }

    private List<User> loadUsersFromCsv(boolean header) {
        List<User> users = new ArrayList<>();
        List<String[]> data = CsvUtils.readCSV(SystemConstants.PATH_CSV_USER);
        for (String[] row : data) {
            if (header) {
                header = false;
                continue;
            }
            User user = new User(row[1], row[2], row[3]);
            user.setId(UUID.fromString(row[0]));
            user.setRole(Role.valueOf(row[4]));
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

    public void addUser(User user) {
        userRepository.add(user);
    }
}
