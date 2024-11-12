package SistemaGerenciamentoTarefas.src.Classes.login;

import SistemaGerenciamentoTarefas.src.Classes.user.User;
import SistemaGerenciamentoTarefas.src.Classes.user.UserRepository;

import java.util.List;

public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public boolean logIn(String usernameOrEmail, String password) {
        if (loginService.authenticate(usernameOrEmail, password)) {
            System.out.println("Login bem-sucedido!");
            return true;
        } else {
            System.out.println("Nome de usuario ou senha incorretos.");
            return false;
        }
    }

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        LoginService loginService = new LoginService();
        LoginController loginController = new LoginController(loginService);

        User newUser = new User("usuarioTeste", "usuario@teste.com", "senhaTeste");
        List<User> users = userRepository.loadUsers();
        users.add(newUser);
        userRepository.saveUsers(users);
        System.out.println("Novo usuario adicionado: " + newUser);

        System.out.println("\nTestando login com credenciais corretas:");
        loginController.logIn("usuarioTeste", "senhaTeste");

        System.out.println("\nTestando login com credenciais incorreta:");
        loginController.logIn("usuarioErrado", "senhaErrada");

        if (userRepository.updateUser(newUser.getUserId(), "novoUsuario", "novo@teste.com", "novaSenha")) {
            System.out.println("\nUsuario atualizado: " + newUser.getUserId());
        } else {
            System.out.println("\nUsuario nao encontrado.");
        }

        users = userRepository.loadUsers();
        System.out.println("Usuarios apos atualizacao: " + users);
    }
}
