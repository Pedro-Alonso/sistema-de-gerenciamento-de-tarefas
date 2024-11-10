package SistemaGerenciamentoTarefas.src.Classes.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class User {

    private String username;
    private final UUID id;
    private String email;
    private String password; // For now the password is not secure, but it will eventually;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Constructor for the User class
     * @param username The name of the User -> {@link String}
     * @param email The email of the User -> {@link String}
     * @param password The password of the User -> {@link String}
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }


    //Login is now made into in login package

    //Changes are now made in UserRepository

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
                getUserId(),
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
    public UUID getUserId() {
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

    public String formatDate(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return time.format(formatter);
    }

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
            this.password = password;
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
