package SistemaGerenciamentoTarefas.src.Classes.login;

public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void LogIn(String usernameOrEmail, String password) {
        if (loginService.authenticate(usernameOrEmail, password)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Nome de usuario ou senha incorretos.");
        }
    }
}
