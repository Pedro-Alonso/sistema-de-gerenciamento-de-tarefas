package SistemaGerenciamentoTarefas.src.Classes.login;

public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public boolean LogIn(String usernameOrEmail, String password) {
        if (loginService.authenticate(usernameOrEmail, password)) {
            System.out.println("Login bem-sucedido!");
            return true;
        } else {
            System.out.println("Nome de usuario ou senha incorretos.");
            return false;
        }
    }
}
