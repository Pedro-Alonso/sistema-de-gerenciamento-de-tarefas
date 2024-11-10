package SistemaGerenciamentoTarefas.src.Classes.login;

import java.util.List;
import SistemaGerenciamentoTarefas.src.Classes.user.User;
import SistemaGerenciamentoTarefas.src.Classes.user.UserRepository;


public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public boolean authenticate(String usernameOrEmail, String password) {
        List<User> users = userRepository.loadUsers();
            return users.stream()
                    .anyMatch(u
                            -> u.getUsername().equals(usernameOrEmail)
                            || u.getUserEmail().equals(usernameOrEmail)
                            && u.getUserPassword().equals(password));
    }

    public void newUser(User newUser) {
        List<User> users = userRepository.loadUsers();
        users.add(newUser);
        userRepository.saveUsers(users);
    }
}
