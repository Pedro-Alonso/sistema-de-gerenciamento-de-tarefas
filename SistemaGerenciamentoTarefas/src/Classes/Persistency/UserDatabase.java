package Classes.Persistency;

import java.util.UUID;
import java.util.Vector;

import Classes.Model.User;

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
     * Updates a user in the database by replacing the existing user with the new user.
     * @param newUser the new user to replace the existing user
     * @return true if the user was updated, false otherwise
     */
    public boolean updateUser(User newUser) {
        UUID id = newUser.getId();
        if (removeUser(id)) {
            addUser(newUser);
            return true;
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
