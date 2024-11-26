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

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Vector<User> getUsers() {
        return users;
    }

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

    public boolean removeUser(UUID id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    public void clearUsers() {
        users.clear();
    }
}
