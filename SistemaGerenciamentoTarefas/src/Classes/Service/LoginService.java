package Classes.Service;

import java.util.List;

import Classes.Model.User;
import Classes.Model.UserSession;
import Classes.Persistency.UserDatabase;

public class LoginService {
    private final UserDatabase userDatabase;

    public LoginService() {
        this.userDatabase = UserDatabase.getInstance();
    }

    /**
     * Authenticates a user based on username or email and password.
     * @param usernameOrEmail the username or email of the user
     * @param password the password of the user
     * @return UserSession if authentication is successful, null otherwise
     */
    public UserSession authenticate(String usernameOrEmail, String password) {
        List<User> users = userDatabase.getUsers();
        for (User user : users) {
            if ((user.getUsername().equals(usernameOrEmail) || user.getUserEmail().equals(usernameOrEmail))
                    && user.getUserPassword().equals(password)) {
                return new UserSession(user);
            }
        }
        return null;
    }

    /**
     * Adds a new user to the database.
     * @param newUser the new user to be added
     */
    public void newUser(User newUser) {
        userDatabase.addUser(newUser);
    }
}
