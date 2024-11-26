package Classes.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

public class UserSession {
    private User user;
    private String token;

    /**
     * Constructor for the UserSession class.
     * @param user the user associated with the session
     */
    public UserSession(User user) {
        this.user = user;
        this.token = generateToken(user.getId());
    }

    /**
     * Generates a session token for the user.
     * @param userId the ID of the user
     * @return the generated session token
     */
    private String generateToken(UUID userId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String tokenData = userId.toString() + ":" + timestamp;
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }

    /**
     * Checks if the session is active.
     * @return true if the session is active, false otherwise
     */
    public boolean getStatus() {
        String decodedToken = new String(Base64.getDecoder().decode(token));
        String[] tokenParts = decodedToken.split(":");
        String timestampStr = tokenParts[1];
        LocalDateTime tokenTime = LocalDateTime.parse(timestampStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return !tokenTime.isBefore(LocalDateTime.now().minusHours(1));
    }

    /**
     * Refreshes the session token.
     */
    public void refreshSession() {
        this.token = generateToken(user.getId());
    }

    /**
     * Returns the user associated with the session.
     * @return the user if the session is active, null otherwise
     */
    public User getUser() {
        if (!getStatus()) {
            return null;
        }
        return user;
    }

    /**
     * Returns the session token.
     * @return the session token
     */
    public String getToken() {
        return token;
    }
}