package SistemaGerenciamentoTarefas.src.Classes;

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


    /**
     * Method to log in the user by checking the provided email or username and password.
     * @param emailOrUsername The email or username used for logging in -> {@link String}
     * @param password The password used for logging in -> {@link String}
     * @return {@code boolean} | {@code true} if login is successful, {@code false} otherwise
     * @throws Exception if an error occurs while processing the login
     */
    public boolean logIn(String emailOrUsername, String password) {
            // Check if the provided email or name and password match the stored value
            if (!(emailOrUsername.equals(getUsername()) || emailOrUsername.equals(getUserEmail()))) {
                System.out.println("Email ou username incorreto");
                return false;
            } else if (!password.equals(getUserPassword())) {
                System.out.println("Senha incorreta.");
                return false;
            } else {
                System.out.println("Login bem sucedido");
                return true;
            }
    }

    /**
     * Method to change the current email of the User.
     * @param oldEmail The current email that needs to be changed -> {@link String}
     * @param newEmail The new email to set -> {@link String}
     * @return {@code boolean} | {@code true} if the email was changed successfully, {@code false} otherwise
     * @throws Exception if an error occurs while changing the email
     */
    public boolean changeEmail(String oldEmail, String newEmail) {
            //Check if the old email matches
            if (this.email.equals(oldEmail)) {
                // Set the new email
                setUserEmail(newEmail);
                setUpdatedAt();
                return true;
            } else {
                System.out.println("Erro: Email incorreto.");
                return false;
            }
    }

    /**
     * Method to change the current password of the User.
     * @param oldPassword The current password that needs to be changed -> {@link String}
     * @param newPassword The new password to set -> {@link String}
     * @return {@code boolean} | {@code true} if the password was changed successfully, {@code false} otherwise
     * @throws Exception if an error occurs while changing the password
     */
    public boolean changePassword(String oldPassword, String newPassword) {
            //Check if the old password matches
            if (this.password.equals(oldPassword)) {
                // Set the new password
                setUserPassword(newPassword);
                return true;
            } else {
                System.out.println("Erro: Senha incorreta.");
                setUpdatedAt();
                return false;
            }
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
                getUserId(),
                getUserEmail(),
                getUserPassword(),
                getFormattedDate(getCreatedAt(), "dd/MM/yyyy HH:mm:ss"),
                getFormattedDate(getUpdatedAt(), "dd/MM/yyyy HH:mm:ss"));
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

    public String getFormattedDate(LocalDateTime time, String format) {
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
