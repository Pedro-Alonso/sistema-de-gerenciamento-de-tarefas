package Classes.login;

import java.util.List;

import Classes.user.User;


public class LoginService {
    private final UserDatabase userDatabase;

    public LoginService() {
        this.userDatabase = UserDatabase.getInstance();
    }

    public boolean authenticate(String usernameOrEmail, String password) {
        List<User> users = userDatabase.getUsers();
            return users.stream()
                    .anyMatch(u
                            -> u.getUsername().equals(usernameOrEmail)
                            || u.getUserEmail().equals(usernameOrEmail)
                            && u.getUserPassword().equals(password));
    }

    public void newUser(User newUser) {
        userDatabase.addUser(newUser);
    }
}
