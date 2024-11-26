package Classes.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.Base64;
import java.util.regex.Pattern;

public class User {

    private String username;
    private final UUID id;
    private String email;
    private String password;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Expressao regular para validar o formato do email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Constructor for the User class
     * @param username The name of the User -> {@link String}
     * @param email The email of the User -> {@link String}
     * @param password The password of the User -> {@link String}
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.id = UUID.randomUUID();
        setUserEmail(email);
        setUserPassword(password);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    /**
     * Method that encodes a password in Base 64
     * @param password The password that will be encoded -> {@link String}
     * @return {@link String}
     */
    private String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
    /**
     * Method that formats a date for the format inputted
     * @param time The time that will be encoded -> {@link String}
     * @param format The format that will be returned -> {@link String}
     * @return {@link String}
     */
    public String formatDate(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }

    /**
     * Method that returns a String with all the information about the current User object, except for the id
     * @return {@link String}
     */
    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                ID: %s
                Email: %s
                Password: %s
                Criado em: %s
                Atualizado em: %s
                """,
                getUsername(),
                getId(),
                getUserEmail(),
                getUserPassword(),
                formatDate(getCreatedAt(), "dd/MM/yyyy HH:mm:ss"),
                formatDate(getUpdatedAt(), "dd/MM/yyyy HH:mm:ss"));
    }

    //setters & getters

    /**
     * {@return the username of the User as a {@link String}}
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@return the id of the User as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * {@return the email of the User as a {@link String}}
     */
    public String getUserEmail() {
        return email;
    }

    /**
     * {@return the password of the User as a {@link String}}
     */
    public String getUserPassword() {
        return password;
    }

    /**
     * {@return the time the User account was created as a {@link LocalDateTime}}
     */
    public LocalDateTime getCreatedAt() { return createdAt; }

    /**
     * {@return the time the User account was last updated as a {@link LocalDateTime}}
     */
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    /**
     * Setter for the username of the User
     * @param username The new name for the User -> {@link String}
     * @throws IllegalArgumentException if the username is blank
     */
    public void setUsername(String username) {
        try {
            if (username.isBlank()) throw new IllegalArgumentException(
                    "O nome do usuario não pode ser vazio"
            );
            this.username = username;
            setUpdatedAt();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Setter for the email of the User
     * @param email The new email for the User -> {@link String}
     * @throws IllegalArgumentException if the new email is blank
     */
    public void setUserEmail(String email) {
        try {
            if (email.isBlank()) throw new IllegalArgumentException(
                    "O email do usuario não pode ser vazio"
            );
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                throw new IllegalArgumentException("O email fornecido nao e valido");
            }
            this.email = email;
            setUpdatedAt();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Setter for the password of the User
     * @param password The new password for the User -> {@link String}
     * @throws IllegalArgumentException if the new password is blank
     */
    public void setUserPassword(String password) {
        try {
            if (password.isBlank()) throw new IllegalArgumentException(
                    "A senha do usuario não pode ser vazia"
            );
            this.password = encodePassword(password);
            setUpdatedAt();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Setter for the time of the last update on the of the User attributes
     */
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
