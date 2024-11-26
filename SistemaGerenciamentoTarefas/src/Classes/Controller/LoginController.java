package Classes.Controller;

import Classes.Model.User;
import Classes.Persistency.UserDatabase;
import Classes.Service.LoginService;

public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Logs in a user based on username or email and password.
     * @param usernameOrEmail the username or email of the user
     * @param password the password of the user
     * @return true if login is successful, false otherwise
     */
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
        LoginService loginService = new LoginService();
        LoginController loginController = new LoginController(loginService);
        UserDatabase userDatabase = UserDatabase.getInstance();

        User newUser = new User("usuarioTeste", "usuario@teste.com", "senhaTeste");
        userDatabase.addUser(newUser);
        System.out.println("Novo usuario adicionado: " + newUser);

        System.out.println("\nTestando login com credenciais corretas:");
        loginController.logIn("usuarioTeste", "senhaTeste");

        System.out.println("\nTestando login com credenciais incorreta:");
        loginController.logIn("usuarioErrado", "senhaErrada");

        User existingUser = userDatabase.getUsers().get(0);

        if (userDatabase.updateUser(existingUser.getId(), "newUsername", "newEmail@example.com", "newPassword")) {
            System.out.println("Usuario atalizado com sucesso,");
        } else {
            System.out.println("Usuario nao encontrado.");
        }
        System.out.println("\nUsuario apos atualizacao: " + existingUser + "\n");

        System.out.println("Usuarios apos atualizacao:");
        System.out.println(userDatabase.toString());

        if (userDatabase.removeUser(existingUser.getId())) {
            System.out.println("Usuario removido com sucesso.");
        } else {
            System.out.println("Usuario nao foi removido.");
        }

        System.out.println("Usuarios apos remocao:");
        System.out.println(userDatabase.toString());

    }
}
