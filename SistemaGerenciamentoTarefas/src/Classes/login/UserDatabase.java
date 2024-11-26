package Classes.login;

import Classes.user.User;

import java.util.UUID;
import java.util.Vector;

public class UserDatabase {

    private static UserDatabase instance = null;

    private Vector<User> users;

    private UserDatabase() {
        users = new Vector<>();
    }

    /**
     * Returns the singleton instance of UserDatabase.
     * @return the singleton instance of UserDatabase
     */
    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    /**
     * Adds a user to the database.
     * @param user the user to be added
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Returns the list of users in the database.
     * @return the list of users
     */
    public Vector<User> getUsers() {
        return users;
    }

    /**
     * Updates the details of a user in the database.
     * @param id the ID of the user to be updated
     * @param newUsername the new username for the user
     * @param newEmail the new email for the user
     * @param newPassword the new password for the user
     * @return true if the user was updated, false otherwise
     */
    public boolean updateUser(UUID id, String newUsername, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                if (newUsername != null) user.setUsername(newUsername);
                if (newEmail != null) user.setUserEmail(newEmail);
                if (newPassword != null) user.setUserPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the users in the database.
     * @return a string representation of the users
     */
    @Override
    public String toString() {
        if (users.isEmpty()) {
            return "Nenhum usuario cadastrado.";
        }

        StringBuilder builder = new StringBuilder("Lista de Usuarios:\n");
        for (User user : users) {
            builder.append(user).append("\n");
        }
        return builder.toString();
    }

    /**
     * Removes a user from the database.
     * @param id the ID of the user to be removed
     * @return true if the user was removed, false otherwise
     */
    public boolean removeUser(UUID id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    /**
     * Clears all users from the database.
     */
    public void clearUsers() {
        users.clear();
    }
}
