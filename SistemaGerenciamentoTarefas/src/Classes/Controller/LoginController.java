package Classes.Controller;

import Classes.Service.LoginService;
import Classes.Model.UserSession;

public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Logs in a user based on username or email and password.
     * @param usernameOrEmail the username or email of the user
     * @param password the password of the user
     * @return UserSession if login is successful, null otherwise
     */
    public UserSession logIn(String usernameOrEmail, String password) {
        if (usernameOrEmail == null || password == null) {
            throw new IllegalArgumentException("Username/email and password must not be null.");
        }
        String encryptedPassword = loginService.encodePassword(password);
        UserSession userSession = loginService.authenticate(usernameOrEmail, encryptedPassword);
        if (userSession == null) {
            System.out.println("Invalid credentials.");
        }
        return userSession;
    }

    // public static void main(String[] args) {
    //     LoginService loginService = new LoginService();
    //     LoginController loginController = new LoginController(loginService);
    //     UserDatabase userDatabase = UserDatabase.getInstance();

    //     User newUser = new User("usuarioTeste", "usuario@teste.com", "senhaTeste");
    //     userDatabase.addUser(newUser);
    //     System.out.println("Novo usuario adicionado: " + newUser);

    //     System.out.println("\nTestando login com credenciais corretas:");
    //     loginController.logIn("usuarioTeste", "senhaTeste");

    //     System.out.println("\nTestando login com credenciais incorreta:");
    //     loginController.logIn("usuarioErrado", "senhaErrada");

    //     User existingUser = userDatabase.getUsers().get(0);

    //     if (userDatabase.updateUser(existingUser.getId(), "newUsername", "newEmail@example.com", "newPassword")) {
    //         System.out.println("Usuario atalizado com sucesso,");
    //     } else {
    //         System.out.println("Usuario nao encontrado.");
    //     }
    //     System.out.println("\nUsuario apos atualizacao: " + existingUser + "\n");

    //     System.out.println("Usuarios apos atualizacao:");
    //     System.out.println(userDatabase.toString());

    //     if (userDatabase.removeUser(existingUser.getId())) {
    //         System.out.println("Usuario removido com sucesso.");
    //     } else {
    //         System.out.println("Usuario nao foi removido.");
    //     }

    //     System.out.println("Usuarios apos remocao:");
    //     System.out.println(userDatabase.toString());

    // }
}
