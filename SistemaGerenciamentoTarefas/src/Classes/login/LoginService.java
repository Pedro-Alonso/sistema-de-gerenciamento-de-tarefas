package SistemaGerenciamentoTarefas.src.Classes.login;

import java.util.List;

import SistemaGerenciamentoTarefas.src.Classes.user.User;
import SistemaGerenciamentoTarefas.src.Classes.login.UserDatabase;


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
