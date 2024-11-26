package Classes.login;

import java.util.List;

import Classes.user.User;

public class LoginService {
    private final UserDatabase userDatabase;

    public LoginService() {
        this.userDatabase = UserDatabase.getInstance();
    }

    /**
     * Authenticates a user based on username or email and password.
     * @param usernameOrEmail the username or email of the user
     * @param password the password of the user
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String usernameOrEmail, String password) {
        List<User> users = userDatabase.getUsers();
        return users.stream()
                .anyMatch(u -> (u.getUsername().equals(usernameOrEmail) || u.getUserEmail().equals(usernameOrEmail))
                        && u.getUserPassword().equals(password));
    }

    /**
     * Adds a new user to the database.
     * @param newUser the new user to be added
     */
    public void newUser(User newUser) {
        userDatabase.addUser(newUser);
    }
}
