package model;

import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private UUID id;
    private String username;
    private final String email;
    private String password;
    private Role role;

    public User(String username, String email, String password, boolean isHashed) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = isHashed ? password : hashedPassword(password);
    }

    // ==================================================== Getter
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public Role getRole() { return role; }

    // ==================================================== Setter
    public void setId(UUID id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) { this.password = hashedPassword(newPassword); }

    // ==================================================== Other
    public boolean checkPassword(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.password);
    }

    private String hashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}