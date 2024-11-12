package Classes;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private String name;
    private final UUID id = UUID.randomUUID();
    private String email;
    private String password; // For now the password is not secure, but it will eventually;

    /**
     * Constructor for the Task class
     * @param name The name of the User -> {@link String}
     * @param email The description of the User -> {@link String}
     * @param password The deadline of the User -> {@link LocalDate}
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Method to log in the user by checking the provided email or name and password.
     * @param emailOrName The email or name used for logging in -> {@link String}
     * @param password The password used for logging in -> {@link String}
     * @return {@code boolean} | {@code true} if login is successful, {@code false} otherwise
     * @throws Exception if an error occurs while processing the login
     */
    public boolean logIn(String emailOrName, String password) {
        try {
            // Check if the provided email or name and password match the stored values
            return ((this.email.equals(emailOrName) || this.name.equals(emailOrName)) && this.password.equals(password));
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.out.println("Erro ao tentar fazer login: " + e.getMessage());
            return false;
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
        try {
            //Check if the old email matches
            if (this.email.equals(oldEmail)) {
                // Set the new email
                this.email = newEmail;
                return true;
            } else {
                // Old email does not match
                return false;
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.out.println("Erro ao alterar o email: " + e.getMessage());
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
        try {
            //Check if the old password matches
            if (this.password.equals(oldPassword)) {
                // Set the new password
                this.password = newPassword;
                return true;
            } else {
                // Old password does not match
                return false;
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.out.println("Erro ao alterar a senha: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method that returns a String with all the information about the current User object, except for the id
     * @return {@link String}
     */
    public String printUser() {
        return String.format("""
                Nome: %s
                Email: %s
                Password: %s
                """, name, email, password);
    }

    //setters & getters

    /**
     * {@return the name of the User as a {@link String}}
     */
    public String getUserName() {
        return name;
    }

    /**
     * Setter for the name of the User
     * @param userName The new name for the User -> {@link String}
     * @throws IllegalArgumentException if the name is blank
     */
    public void setUserName(String userName) {
        try {
            if (userName.isBlank()) throw new IllegalArgumentException(
                    "O nome do usuario não pode ser vazio"
            );
            this.name = userName;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email of the User
     * @param email The new email for the User -> {@link String}
     * @throws IllegalArgumentException if the name is blank
     */
    public void setEmail(String email) {
        try {
            if (email.isBlank()) throw new IllegalArgumentException(
                    "O email do usuario não pode ser vazio"
            );
            this.email = email;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * {@return the password of the User as a {@link String}}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password of the User
     * @param password The new emaiil for the User -> {@link String}
     * @throws IllegalArgumentException if the name is blank
     */
    public void setPassword(String password) {
        try {
            if (password.isBlank()) throw new IllegalArgumentException(
                    "A senha do usuario não pode ser vazia"
            );
            this.password = password;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


}
