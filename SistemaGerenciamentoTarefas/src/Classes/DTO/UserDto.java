package Classes.DTO;

import java.util.UUID;

/**
 * Data Transfer Object for User.
 */
public class UserDto {
    private UUID id;
    private String username;
    private String email;

    /**
     * {@return the ID of the user as a {@link UUID}}
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the ID of the user.
     * @param id The new ID for the user -> {@link UUID}
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * {@return the username of the user as a {@link String}}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of the user.
     * @param username The new username for the user -> {@link String}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * {@return the email of the user as a {@link String}}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for the email of the user.
     * @param email The new email for the user -> {@link String}
     */
    public void setEmail(String email) {
        this.email = email;
    }
}